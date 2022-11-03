import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple string Parser class.
 */
public class Parser {
  /**
   * Method to perform string "^{1+3-2...}" (or "_{1+3-2...})"
   * to "^1^+^3^-..." (or "_1_+_3_-...") for the further parsing.
   *
   * @param string given string in format "^{..}" or "_{..}"
   * @return expanded string
   */
  private static String expandScript(String string) {
    char scriptIdentifier = string.charAt(0);
    string = string.replace("+", scriptIdentifier + "+");
    string = string.replace("-", scriptIdentifier + "-");
    for (int num = 0; num < 10; num++) {
      String strNum = Integer.toString(num);
      string = string.replaceAll(strNum, scriptIdentifier + strNum);
    }
    return string.substring(2, string.length() - 1);
  }

  /**
   * Method to expand all superscripts and subscripts in the string.
   *
   * @param string given string
   * @return string with all expanded superscripts and subscripts
   */
  private static String parseScript(String string) {
    Pattern pattern = Pattern.compile("[\\^, _]\\{[\\d+-]*}");
    Matcher matcher = pattern.matcher(string);

    string = matcher.replaceAll((match) -> expandScript(match.group()));
    return string;
  }

  /**
   * Method to parse given string.
   *
   * @param string string to be parsed
   * @return parsed string
   */
  // TODO: Optimize parsing process (for example, via Trie and Aho-Corasick)
  public static String parseString(String string) {
    string = parseScript(string);
    for (Parsable.Symbol symbol : Parsable.Symbol.values()) {
      string = string.replace(symbol.getAlias(), symbol.getCharacter());
    }
    return string;
  }

  /**
   * Method to return reference about all parsable symbols.
   *
   * @return string reference in HTML-style
   */
  public static String getReference() {
    String reference = "";
    for (Parsable.Symbol symbol : Parsable.Symbol.values()) {
      if (symbol.isHidden()) {
        continue;
      }
      reference = reference.concat(String.format(Text.REFERENCE_TEMPLATE,
              symbol.getName(), symbol.getCharacter(), symbol.getAlias()));
    }
    return reference;
  }
}

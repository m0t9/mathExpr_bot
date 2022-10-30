import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple string Parser class
 */
public class Parser {
    // TODO: Extend subscript and superscript on letters
    /**
     * Method to perform string "^{abcd...}" (or "_{abcd...})"
     * to "^a^b^c^d..." (or "_a_b_c_d...") for the further parsing
     *
     * @param string given string in format "^{..}" or "_{..}"
     * @return expanded string
     */
    private static String expandScript(String string) {
        for (int num = 0; num < 10; num++) {
            String strNum = Integer.toString(num), superNum = string.charAt(0) + strNum;
            string = string.replaceAll(strNum, superNum);
        }
        return string.substring(2, string.length() - 1); // cutting "^{" in the beginning and "}" in the end
    }

    /**
     * Method to expand all superscripts and subscripts in the string
     *
     * @param string given string
     * @return string with all expanded superscripts and subscripts
     */
    private static String parseScript(String string) {
        Pattern pattern = Pattern.compile("[\\^, _]\\{\\d+}");
        Matcher matcher = pattern.matcher(string);

        string = matcher.replaceAll((match) -> expandScript(match.group()));
        return string;
    }

    /**
     * Method to parse given string
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
     * Method to return reference about all parsable symbols
     *
     * @return string reference in HTML-style
     */
    public static String getReference() {
        String reference = "";
        for (Parsable.Symbol symbol : Parsable.Symbol.values()) {
            reference = reference.concat(String.format("%s (%s) is denoted by <i>%s</i>\n",
                    symbol.getName(), symbol.getCharacter(), symbol.getAlias()));
        }
        return reference;
    }
}

package tomathbot.parsing;

import tomathbot.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple string Parser class.
 */
public class Parser {
    private Parser() {

    }

    /**
     * Method to parse superscript and subscript.
     *
     * @param string given string in format "^{..}" or "_{..}"
     * @return expanded string
     */
    private static String expandScript(String string) {
        char scriptIdentifier = string.charAt(0);
        StringBuilder expandedString = new StringBuilder();
        for (int i = 2; i < string.length() - 1; i++) {
            if (string.charAt(i) != ' ') {
                expandedString.append(scriptIdentifier);
            }
            expandedString.append(string.charAt(i));
        }
        return expandedString.toString();
    }

    /**
     * Method to expand all superscripts and subscripts in the string.
     *
     * @param string given string
     * @return string with all expanded superscripts and subscripts
     */
    private static String parseScript(String string) {
        Pattern pattern = Pattern.compile("[\\^, _]\\{[()a-z0-9+\\-= ]*}");
        Matcher matcher = pattern.matcher(string);

        string = matcher.replaceAll(match -> expandScript(match.group()));
        return string;
    }

    /**
     * Method to parse given string.
     *
     * @param string string to be parsed
     * @return parsed string
     */
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
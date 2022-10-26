/**
 * Simple string Parser class
 */
public class Parser {
    private enum Symbol {
        /**
         * List of available symbols to parse
         */
        // TODO: Extend list of symbols
        FOR_ALL("Universal quantifier", "\\forall", "&#8704;", "∀"),
        EXISTS("Particular (existence) quantifier", "\\exists", "&#8707;", "∃"),
        BELONGS("Element belong to set", "\\in", "&#8712;", "∈"),
        NOT_BELONGS("Element does not belong to set", "\\nin", "&#8713;", "∉"),
        SUM("Sum", "\\sum", "&#8721;", "∑"),
        INFINITY("Infinity", "\\infty", "&#8734;", "∞"),
        AND("Logical AND", "\\and", "&#8743;", "∧"),
        OR("Logical OR", "\\or", "&#8744;", "∨"),
        INTERSECTION("Sets intersection", "\\cap", "&#8745;", "∩"),
        UNION("Sets union", "\\cup", "&#8746;", "∪"),
        EPSILON("Epsilon", "\\eps", "&#603;", "ε"),
        DELTA("Delta", "\\delta", "&#948;", "δ"),
        LESS("Less", "<", "&#60;", "<"),
        LESS_EQUAL("Less or equal", "\\le", "&#8804;", "≤"),
        GREATER_EQUAL("Greater or equal", "\\ge", "&#8805;", "≥"),
        EQUIVALENCE("Equivalence", "\\equiv", "&#8801;", "≡");

        /**
         * name - mathematical name of the symbol
         * <p>
         * alias - symbol notation for parsing
         * <p>
         * html - HTML code of the symbol
         * <p>
         * character - decoded HTML code
         */
        private final String name, alias, html, character;

        Symbol(String name, String alias, String html, String character) {
            this.name = name;
            this.alias = alias;
            this.html = html;
            this.character = character;
        }
    }

    /**
     * Parser Method to parse given string to HTML format
     *
     * @param string string to be parsed
     * @return parsed string
     */

    // TODO: Optimize parsing process
    // TODO: Set ParseMode for inline queries
    public static String parseString(String string) {
        for (Symbol symbol : Symbol.values()) {
            string = string.replace(symbol.alias, symbol.character);
        }
        return string;
    }

    /**
     * Parser Method to return reference about all parsable symbols
     *
     * @return string reference
     */
    public static String getParsableSymbolsReference() {
        String reference = "";
        for (Symbol symbol : Symbol.values()) {
            if (symbol == Symbol.LESS) { // For successfully parsed message
                continue;
            }
            reference = reference.concat(String.format("%s (%s) is denoted by <u>%s</u>\n",
                    symbol.name, symbol.html, symbol.alias));
        }
        return reference;
    }
}

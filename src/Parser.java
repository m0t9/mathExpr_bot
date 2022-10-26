/**
 * Simple string Parser class
 */
public class Parser {
    private enum Symbol {
        /**
         * List of available symbols to parse
         */
        // TODO: Extend list of symbols
        FOR_ALL("Universal quantifier", "\\forall", "∀"),
        EXISTS("Particular (existence) quantifier", "\\exists", "∃"),
        BELONGS("Element belong to set", "\\in", "∈"),
        NOT_BELONGS("Element does not belong to set", "\\notin", "∉"),
        SUM("Sum", "\\sum", "∑"),
        INFINITY("Infinity", "\\infty", "∞"),
        AND("Logical AND", "\\land",  "∧"),
        OR("Logical OR", "\\lor", "∨"),
        INTERSECTION("Sets intersection", "\\cap", "∩"),
        UNION("Sets union", "\\cup", "∪"),
        EPSILON("Epsilon", "\\varepsilon", "ε"),
        DELTA("Delta", "\\delta", "δ"),
        LESS("Less", "<", "<"),
        LESS_EQUAL("Less or equal", "\\leq",  "≤"),
        GREATER_EQUAL("Greater or equal", "\\geq", "≥"),
        EQUIVALENCE("Equivalence", "\\equiv",  "≡"),
        RIGHT_ARROW("Right arrow", "\\Rightarrow", "⇒"),
        LEFT_ARROW("Left arrow", "\\Leftarrow", "⇐"),
        LEFT_RIGHT_ARROW("Left right arrow", "\\Leftrightarrow", "⇔"),
        N("Set of Naturals", "\\mathbb{N}",  "ℕ"),
        Z("Set of Integers", "\\mathbb{Z}",  "ℤ");


        /**
         * name - mathematical name of the symbol
         * <p>
         * alias - symbol notation for parsing
         * <p>
         * character - decoded HTML code
         */
        private final String name;
        private final String alias;
        private final String character;

        Symbol(String name, String alias, String character) {
            this.name = name;
            this.alias = alias;
            this.character = character;
        }
    }

    /**
     * Parser Method to parse given string to HTML format
     *
     * @param string string to be parsed
     * @return parsed string
     */

    // TODO: Optimize parsing process (for example, via Trie and Aho-Corasick)
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
            if (symbol == Symbol.LESS) { // To avoid parsing errors
                continue;
            }
            reference = reference.concat(String.format("<i>%s</i> (%s) is denoted by <u>%s</u>\n",
                    symbol.name, symbol.character, symbol.alias));
        }
        return reference;
    }
}

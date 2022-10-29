/**
 * Simple string Parser class
 */
public class Parser {
    private enum Symbol {
        /**
         * List of available symbols to parse
         */
        // TODO: Support of upper indexes

        // Logic
        FOR_ALL("Universal quantifier", "\\forall", "∀"),
        EXISTS("Particular quantifier", "\\exists", "∃"),
        AND("Logical AND", "\\land", "∧"),
        OR("Logical OR", "\\lor", "∨"),
        IMPLIES("Implication", "\\implies", "⇒"),
        BIVALENCE("Bivalence", "\\iff", "⇔"),

        // Math, calculus
        SUM("Sum", "\\sum", "∑"),
        PRODUCT("Product", "\\prod", "∏"),
        INFINITY("Infinity", "\\infty", "∞"),
        NOT_EQUALS("Not equals", "\\ne", "≠"),
        LESS_EQUAL("Less or equal", "\\leq", "≤"),
        GREATER_EQUAL("Greater or equal", "\\geq", "≥"),
        EQUIVALENCE("Equivalence", "\\equiv", "≡"),
        PLUS_MINUS("Plus-minus sign", "\\pm", "±"),
        TIMES("Multiplication cross", "\\times", "×"),
        CDOT("Multiplication dot", "\\cdot", "⋅"),
        INTEGRAL("Integral", "\\int", "∫"),

        // Set theory
        BELONGS("Element belong to set", "\\in", "∈"),
        NOT_BELONGS("Element does not belong to set", "\\notin", "∉"),
        INTERSECTION("Sets intersection", "\\cap", "∩"),
        UNION("Sets union", "\\cup", "∪"),
        EMPTY_SET("Empty set", "\\emptyset", "Ø"),
        N("Set of Naturals", "\\mathbb{N}", "ℕ"),
        Z("Set of Integers", "\\mathbb{Z}", "ℤ"),
        Q("Set of Rationals", "\\mathbb{Q}", "ℚ"),
        R("Set of Reals", "\\mathbb{R}", "ℝ"),

        // Greek letters
        ALPHA("Alpha", "\\alpha", "α"),
        BETA("Beta", "\\beta", "β"),
        DELTA("Delta", "\\delta", "δ"),
        DELTA_CAPITAL("Capital delta", "\\Delta", "Δ"),
        EPSILON("Epsilon", "\\varepsilon", "ε"),
        LAMBDA("Lambda", "\\lambda", "λ"),
        PI("Pi", "\\pi", "π"),
        OMEGA("Omega", "\\omega", "ω"),
        OMEGA_CAPITAL("Capital omega", "\\Omega", "Ω"),
        SIGMA("Sigma", "\\sigma", "σ"),

        // Arrows
        LEFT_ARROW("Left arrow", "\\Leftarrow", "⇐"),
        LEFT_RIGHT_ARROW("Left right arrow", "\\Leftrightarrow", "⇔"),
        RIGHT_ARROW("Right arrow", "\\Rightarrow", "⇒");

        /**
         * name - mathematical name of the symbol
         * <p>
         * alias - symbol notation for parsing
         * <p>
         * character - decoded HTML code
         */
        private final String name, alias, character;

        Symbol(String name, String alias, String character) {
            this.name = name;
            this.alias = alias;
            this.character = character;
        }
    }

    /**
     * Method to parse given string
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
     * Method to return reference about all parsable symbols
     *
     * @return string reference in HTML-style
     */
    public static String getReference() {
        String reference = "";
        for (Symbol symbol : Symbol.values()) {
            reference = reference.concat(String.format("%s (%s) is denoted by <i>%s</i>\n",
                    symbol.name, symbol.character, symbol.alias));
        }
        return reference;
    }
}

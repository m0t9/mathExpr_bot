/**
 * Class for parsable text data.
 */
public class Parsable {
  /**
   * List of available symbols to parse.
   */
  public enum Symbol {
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
    BELONGS("Element belongs to set", "\\in", "∈"),
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
    RIGHT_ARROW("Right arrow", "\\Rightarrow", "⇒"),

    // Superscript
    SUP_0("Upper 0", "^0", "⁰", true),
    SUP_1("Upper 1", "^1", "¹", true),
    SUP_2("Upper 2", "^2", "²", true),
    SUP_3("Upper 3", "^3", "³", true),
    SUP_4("Upper 4", "^4", "⁴", true),
    SUP_5("Upper 5", "^5", "⁵", true),
    SUP_6("Upper 6", "^6", "⁶", true),
    SUP_7("Upper 7", "^7", "⁷", true),
    SUP_8("Upper 8", "^8", "⁸", true),
    SUP_9("Upper 9", "^9", "⁹", true),
    SUP_PLUS("Upper +", "^+", "⁺", true),
    SUP_MINUS("Upper -", "^-", "⁻", true),

    // Subscript
    SUB_0("Lower 0", "_0", "₀", true),
    SUB_1("Lower 1", "_1", "₁", true),
    SUB_2("Lower 2", "_2", "₂", true),
    SUB_3("Lower 3", "_3", "₃", true),
    SUB_4("Lower 4", "_4", "₄", true),
    SUB_5("Lower 5", "_5", "₅", true),
    SUB_6("Lower 6", "_6", "₆", true),
    SUB_7("Lower 7", "_7", "₇", true),
    SUB_8("Lower 8", "_8", "₈", true),
    SUB_9("Lower 9", "_9", "₉", true),
    SUB_PLUS("Lower +", "_+", "₊", true),
    SUB_MINUS("Lower -", "_-", "₋", true);

    /**
     * name - mathematical name of the symbol
     *
     * <p>alias - symbol notation for parsing
     *
     * <p>character - decoded HTML code
     *
     * <p>hidden - is character visible in reference.
     */
    private final String name;
    private final String alias;
    private final String character;
    private final boolean hidden;

    Symbol(String name, String alias, String character) {
      this.name = name;
      this.alias = alias;
      this.character = character;
      this.hidden = false;
    }

    Symbol(String name, String alias, String character, boolean hidden) {
      this.name = name;
      this.alias = alias;
      this.character = character;
      this.hidden = hidden;
    }

    /**
     * Getter for symbol name.
     */
    public String getName() {
      return name;
    }

    /**
     * Getter for symbol alias.
     */
    public String getAlias() {
      return alias;
    }

    /**
     * Getter for symbol character.
     */
    public String getCharacter() {
      return character;
    }

    /**
     * Getter for symbol visibility.
     */
    public boolean isHidden() {
      return hidden;
    }
  }
}

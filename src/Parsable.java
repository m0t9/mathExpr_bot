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
    NOT("Logical NOT", "\\neg", "¬"),

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
    DIV("Division sign", "\\div", "÷"),
    INTEGRAL("Integral", "\\int", "∫"),
    ROOT("Root", "\\sqrt", "√"),

    // Set theory
    BELONGS("Element belongs to set", "\\in", "∈"),
    NOT_BELONGS("Element does not belong to set", "\\notin", "∉"),
    INTERSECTION("Sets intersection", "\\cap", "∩"),
    UNION("Sets union", "\\cup", "∪"),
    SUBSET("Subset", "\\subset", "⊂"),
    SUPERSET("Superset", "\\supset", "⊃"),
    SUBSET_OR_EQUAL("Subset or equal", "\\subseteq", "⊆"),
    EMPTY_SET("Empty set", "\\emptyset", "Ø"),
    N("Set of Naturals", "\\mathbb{N}", "ℕ"),
    Z("Set of Integers", "\\mathbb{Z}", "ℤ"),
    Q("Set of Rationals", "\\mathbb{Q}", "ℚ"),
    R("Set of Reals", "\\mathbb{R}", "ℝ"),
    C("Set of Complex numbers", "\\mathbb{C}", "ℂ"),

    // Greek letters
    ALPHA("Alpha", "\\alpha", "α"),
    BETA("Beta", "\\beta", "β"),
    GAMMA("Gamma", "\\gamma", "γ"),
    DELTA("Delta", "\\delta", "δ"),
    DELTA_CAPITAL("Capital delta", "\\Delta", "Δ"),
    EPSILON("Epsilon", "\\varepsilon", "ε"),
    LAMBDA("Lambda", "\\lambda", "λ"),
    PI("Pi", "\\pi", "π"),
    OMEGA("Omega", "\\omega", "ω"),
    OMEGA_CAPITAL("Capital omega", "\\Omega", "Ω"),
    SIGMA("Sigma", "\\sigma", "σ"),
    PHI("Phi", "\\phi", "φ"),
    PHI_CAPITAL("Capital phi", "\\Phi", "Φ"),
    PSI("Psi", "\\psi", "ψ"),
    PSI_CAPITAL("Capital psi", "\\Psi", "Ψ"),
    THETA("Theta", "\\theta", "θ"),
    THETA_CAPITAL("Capital theta", "\\Theta", "Θ"),
    XI("Xi", "\\xi", "ξ"),

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
    SUP_EQUALS("Upper =", "^=", "⁼", true),
    SUP_LEFT_PARENTHESIS("Upper (", "^(", "⁽", true),
    SUP_RIGHT_PARENTHESIS("Upper )", "^)", "⁾", true),
    SUP_A("Upper a", "^a", "ᵃ", true),
    SUP_B("Upper b", "^b", "ᵇ", true),
    SUP_C("Upper c", "^c", "ᶜ", true),
    SUP_D("Upper d", "^d", "ᵈ", true),
    SUP_E("Upper e", "^e", "ᵉ", true),
    SUP_F("Upper f", "^f", "ᶠ", true),
    SUP_G("Upper g", "^g", "ᵍ", true),
    SUP_H("Upper h", "^h", "ʰ", true),
    SUP_I_2("Upper i", "^i", "ⁱ", true),
    SUP_J("Upper j", "^j", "ʲ", true),
    SUP_K("Upper k", "^k", "ᵏ", true),
    SUP_L("Upper l", "^l", "ˡ", true),
    SUP_M("Upper m", "^m", "ᵐ", true),
    SUP_N("Upper n", "^n", "ⁿ", true),
    SUP_O("Upper o", "^o", "ᵒ", true),
    SUP_P("Upper p", "^p", "ᵖ", true),
    SUP_R("Upper r", "^r", "ʳ", true),
    SUP_S("Upper s", "^s", "ˢ", true),
    SUP_T("Upper t", "^t", "ᵗ", true),
    SUP_U("Upper u", "^u", "ᵘ", true),
    SUP_V("Upper v", "^v", "ᵛ", true),
    SUP_W("Upper w", "^w", "ʷ", true),
    SUP_X("Upper x", "^x", "ˣ", true),
    SUP_Y("Upper y", "^y", "ʸ", true),
    SUP_Z("Upper z", "^z", "ᶻ", true),

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
    SUB_MINUS("Lower -", "_-", "₋", true),
    SUB_EQUALS("Lower =", "_=", "₌", true),
    SUB_LEFT_PARENTHESIS("Lower (", "_(", "₍", true),
    SUB_RIGHT_PARENTHESIS("Lower )", "_)", "₎", true),
    SUB_I("Lower i", "_i", "ᵢ", true),
    SUB_J("Lower j", "_j", "ⱼ", true),
    SUB_K("Lower k", "_k", "ₖ", true),
    SUB_L("Lower l", "_l", "ₗ", true),
    SUB_M("Lower m", "_m", "ₘ", true),
    SUB_N("Lower n", "_n", "ₙ", true),
    SUB_O("Lower o", "_o", "ₒ", true),
    SUB_P("Lower p", "_p", "ₚ", true),
    SUB_R("Lower r", "_r", "ᵣ", true),
    SUB_S("Lower s", "_s", "ₛ", true),
    SUB_T("Lower t", "_t", "ₜ", true),
    SUB_U("Lower u", "_u", "ᵤ", true),
    SUB_V("Lower v", "_v", "ᵥ", true),
    SUB_X("Lower x", "_x", "ₓ", true);

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

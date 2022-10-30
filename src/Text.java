/**
 * Class for text constants
 */
public class Text {

    /**
     * Constants for message type recognition
     */
    public static final String GREET_QUERY = "/start", HELP_QUERY = "/help";

    /**
     * Constants for incoming messages answering
     */
    public static final String
            GREETING_MESSAGE = """
            Hi! I am inline bot that can help you to parse messages with LaTeX-like formulas in a pretty style! ✨
                        
            Use /help to get all parsable symbols list.
            Type @tomathbot in the beginning of the message to involve me to parse the rest of the message.
            """,
            HELP_MESSAGE = String.format("<b>List of available symbols to parse</b>\n\n%s", Parser.getReference()),
            DEFAULT_MESSAGE = "This bot supports only /help and /start commands.";
}

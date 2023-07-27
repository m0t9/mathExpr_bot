package tomathbot;

import tomathbot.parsing.Parser;

/**
 * Class for text constants.
 */
public class Text {
    /**
     * Constants for message type recognition.
     */
    public static final String GREET_QUERY = "/start";
    public static final String HELP_QUERY = "/help";
    /**
     * Constants for incoming messages answering.
     */
    public static final String
            GREETING_MESSAGE = String.format("""
            Hi! I am inline bot that can help you to parse messages
            with LᴬTᴇX-like formulas in a pretty style! ✨
                        
            Use %s to get all parsable symbols list.
            Type @tomathbot in the beginning of the message
            to involve me to parse the rest of the message.
            """, HELP_QUERY);
    public static final String
            DEFAULT_MESSAGE =
            String.format("This bot supports only %s and %s commands.", GREET_QUERY, HELP_QUERY);
    public static final String
            REFERENCE_TEMPLATE = "%s (%s) is denoted by <i>%s</i>\n";
    public static final String
            HELP_MESSAGE =
            String.format("<b>List of available symbols to parse</b>%n%n%s",
                    Parser.getReference())
                    + "\n<b>Subscript and superscript for numbers, operations, "
                    + "and several letters are supported.</b>";
    /**
     * Constants for internal bot processes.
     */
    public static final String
            LOGO_LINK = "https://i.imgur.com/kra2lHx.jpg";

    private Text() {

    }
}
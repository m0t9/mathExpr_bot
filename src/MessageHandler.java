import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageHandler {
    /**
     * Method trigger messages
     */
    private static final String HELP = "/help", GREET = "/start";

    /**
     * Constant messages in HTML format
     */
    private static final String GREETING_MESSAGE = """
            Hi! This bot is intended to render Your mathematical expression Telegram messages to make them pretty!

            For all mathematical symbols information - /help""";
    private static final String REFERENCE_MESSAGE = "<b>Full list of parsable mathematical symbols</b>\n\n" +
            Parser.getParsableSymbolsReference();

    /**
     * Method to construct greeting message (triggers by /start)
     * @param chatId caller chat id
     * @return SendMessage method to send Greeting Message
     */
    private SendMessage greet(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(GREETING_MESSAGE);
        return message;
    }

    /**
     * Method to construct help message (triggers by /help)
     * @param chatId caller chat id
     * @return SendMessage method to send help message
     */
    private SendMessage help(String chatId) {
        SendMessage message = new SendMessage();
        message.enableHtml(true);
        message.setChatId(chatId);
        message.setText(REFERENCE_MESSAGE);
        return message;
    }

    /**
     * Method to construct parsed message (triggers by any non-command message)
     * @param chatId caller chat id
     * @param messageText sender message text content
     * @return SendMessage method to send parsed message
     */
    private SendMessage parseMessage(String chatId, String messageText) {
        String parsedText = Parser.parseString(messageText);
        SendMessage message = new SendMessage();
        message.enableHtml(true);
        message.setText(parsedText);
        message.setChatId(chatId);
        return message;
    }

    /**
     * Method to handle given non-empty message by other implemented methods
     * @param message message that bot received
     * @return SendMessage method to answer on message
     */
    public SendMessage handleMessage(Message message) {
        String text = message.getText().strip();
        String chatId = message.getChatId().toString();
        switch (text) {
            case HELP -> {
                return help(chatId);
            }
            case GREET -> {
                return greet(chatId);
            }
            default -> {
                return parseMessage(chatId, text);
            }
        }
    }
}

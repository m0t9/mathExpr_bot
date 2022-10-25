import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// TODO: Make this bot inline

/**
 * Bot description
 */
public class Bot extends TelegramLongPollingBot {
    String BOT_NAME, BOT_TOKEN;
    MessageHandler messageHandler;

    /**
     * Bot constructor
     *
     * @param bot_name  Telegram bot username
     * @param bot_token Telegram bot token
     */
    private Bot(String bot_name, String bot_token) {
        BOT_NAME = bot_name;
        BOT_TOKEN = bot_token;
        messageHandler = new MessageHandler();
    }

    /**
     * Method to authenticate bot (by name)
     *
     * @return bot name
     */
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    /**
     * Method to authenticate bot (by token)
     *
     * @return bot token
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    /**
     * Method to receive and process updates
     * @param update occurred update
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                execute(messageHandler.handleMessage(update.getMessage()));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Bot startup
     *
     * @param args args[0] - bot name, args[1] - bot token
     */
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(args[0], args[1]));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

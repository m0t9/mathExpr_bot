import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.jetbrains.annotations.NotNull;

// TODO: Write readme.md

public class Bot {
    /** Constants for message type recognition */
    private final String HELP = "/help";

    private final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    // TODO: Process inline queries and messages separately

    /**
     * Method to process incoming updates
     *
     * @param update received update
     */
    private void process(@NotNull Update update) {
        if (update.inlineQuery() != null) {
            processInlineQuery(update.inlineQuery());
        } else if (update.message() != null) {
            processMessage(update.message());
        }
    }

    /**
     * Method to process given inline query
     *
     * @param query given query
     */
    private void processInlineQuery(@NotNull InlineQuery query) {
        String queryText = query.query();
        String parsedText = Parser.parseString(queryText);

        InlineQueryResultArticle article = new InlineQueryResultArticle("rendered", parsedText, parsedText);
        BaseRequest<AnswerInlineQuery, BaseResponse> request = new AnswerInlineQuery(query.id(), article).cacheTime(1);

        bot.execute(request);
    }

    /**
     * Method to process given message
     * @param message given personal message
     */
    private void processMessage(@NotNull Message message) {
        String chatId = message.chat().id().toString(), text = message.text(), response;

        switch (text) {
            case HELP -> response = "<b>List of available symbols to parse</b>\n\n" + Parser.getParsableSymbolsReference();
            default -> response = "This bot supports only command /help and inline queries.";
        }

        SendMessage sendMessage = new SendMessage(chatId, response);
        sendMessage.parseMode(ParseMode.HTML);
        bot.execute(sendMessage);
    }

    /**
     * Method to start bot
     */
    public void serve() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
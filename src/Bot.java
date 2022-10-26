import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.model.request.InputMessageContent;
import com.pengrad.telegrambot.model.request.InputTextMessageContent;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import org.jetbrains.annotations.NotNull;

// TODO: Write readme.md

public class Bot {
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
        InputMessageContent content = new InputTextMessageContent(parsedText).parseMode(ParseMode.HTML);

        InlineQueryResultArticle article = new InlineQueryResultArticle("rendered", "Render", content);
        BaseRequest<AnswerInlineQuery, BaseResponse> request = new AnswerInlineQuery(query.id(), article).cacheTime(1);

        bot.execute(request);
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
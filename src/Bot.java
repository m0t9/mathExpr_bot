import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import com.pengrad.telegrambot.request.BaseRequest;

public class Bot {
    private final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    // TODO: Process inline queries and messages separately

    /**
     * Method to process incoming updates
     *
     * @param update received update
     */
    private void process(Update update) {
        InlineQuery query = update.inlineQuery();
        BaseRequest request = null;
        if (query != null) {
            String queryText = query.query();
            String parsedText = Parser.parseString(queryText);

            InlineQueryResultArticle article = buildInlineButton("rendered", "Rendered: " + parsedText, parsedText);
            request = new AnswerInlineQuery(query.id(), article).cacheTime(1);
        }
        if (request != null) {
            bot.execute(request);
        }
    }

    /**
     * Method to create Article button for inline query
     *
     * @param id    button ID
     * @param title button visible title
     * @param data  button text content
     * @return query result article
     */
    private InlineQueryResultArticle buildInlineButton(String id, String title, String data) {
        return new InlineQueryResultArticle(id, title, data);
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
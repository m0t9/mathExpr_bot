import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;

/**
 * Bot class description.
 */
public class Bot {
  private final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

  /**
   * Method to process incoming updates.
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
   * Method to process given inline query.
   *
   * @param query given query
   */
  private void processInlineQuery(@NotNull InlineQuery query) {
    String queryText = query.query();
    String parsedText = Parser.parseString(queryText);

    var article = new InlineQueryResultArticle("rendered", "Parsed text", parsedText)
                    .description(parsedText).thumbUrl(Text.LOGO_LINK);
    var request = new AnswerInlineQuery(query.id(), article).cacheTime(0);

    bot.execute(request);
  }

  /**
   * Method to process given message.
   *
   * @param message given personal message
   */
  private void processMessage(@NotNull Message message) {
    String chatId = message.chat().id().toString();
    String text = message.text();
    String response;

    switch (text) {
      case Text.GREET_QUERY -> response = Text.GREETING_MESSAGE;
      case Text.HELP_QUERY -> response = Text.HELP_MESSAGE;
      default -> response = Text.DEFAULT_MESSAGE;
    }

    var sendMessage = new SendMessage(chatId, response);
    sendMessage.parseMode(ParseMode.HTML);
    bot.execute(sendMessage);
  }

  /**
   * Method to start bot listening.
   */
  public void serve() {
    bot.setUpdatesListener(updates -> {
      updates.forEach(this::process);
      return UpdatesListener.CONFIRMED_UPDATES_ALL;
    });
  }
}

package tomathbot.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import org.m0t9_.stategram.filters.OnInlineQuery;
import org.m0t9_.stategram.handlers.Action;
import org.m0t9_.stategram.handlers.BotResponse;
import tomathbot.Text;
import tomathbot.parsing.Parser;

@OnInlineQuery
public class ParserHandler extends org.m0t9_.stategram.handlers.Handler {
    @Override
    public BotResponse<Object> handle(Update update) {
        var query = update.inlineQuery();
        var queryText = query.query();
        var parsed = Parser.parseString(queryText);
        var article = new InlineQueryResultArticle("rendered", "Parsed text", parsed)
                .description(parsed).thumbnailUrl(Text.LOGO_LINK);
        var answer = new AnswerInlineQuery(query.id(), article).cacheTime(0);
        return new BotResponse<>(Action.PRESERVE, answer);
    }
}

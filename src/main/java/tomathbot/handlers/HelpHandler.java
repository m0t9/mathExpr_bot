package tomathbot.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.m0t9_.stategram.filters.OnMessage;
import org.m0t9_.stategram.handlers.Action;
import org.m0t9_.stategram.handlers.BotResponse;
import org.m0t9_.stategram.tools.UpdateTools;
import tomathbot.Text;

/**
 * Send message with reference. Triggers by '/help'.
 */
@OnMessage(pattern = Text.HELP_QUERY)
public class HelpHandler extends org.m0t9_.stategram.handlers.Handler {
    @Override
    public BotResponse<Object> handle(Update update) {
        var usr = UpdateTools.extractSender(update);
        var msg = new SendMessage(usr, Text.HELP_MESSAGE).parseMode(ParseMode.HTML);
        return new BotResponse<>(Action.PRESERVE, msg);
    }
}

package tomathbot;

import org.m0t9_.stategram.bots.LongPollingBot;
import tomathbot.handlers.DefaultHandler;
import tomathbot.handlers.GreetingHandler;
import tomathbot.handlers.HelpHandler;
import tomathbot.handlers.ParserHandler;

public class Main {
    public static void main(String[] args) {
        var bot = new LongPollingBot(System.getenv("BOT_TOKEN"));
        bot.skipUpdates(true);
        bot.addPrimaryHandler(new ParserHandler());
        bot.addPrimaryHandler(new GreetingHandler());
        bot.addPrimaryHandler(new HelpHandler());
        bot.addPrimaryHandler(new DefaultHandler());
        bot.listen();
    }
}

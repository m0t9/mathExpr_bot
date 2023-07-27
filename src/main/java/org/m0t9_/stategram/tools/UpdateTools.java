package org.m0t9_.stategram.tools;

import com.pengrad.telegrambot.model.Update;

/**
 * Toolkit to work with incoming Telegram updates.
 */
public class UpdateTools {
    private UpdateTools() {

    }

    /**
     * Extract ID of user created update.
     *
     * @param update incoming update.
     * @return null if update is not user-created, id - otherwise.
     */
    public static Long extractSender(Update update) {
        if (update.message() != null) {
            return update.message().from().id();
        } else if (update.callbackQuery() != null) {
            return update.callbackQuery().from().id();
        } else if (update.shippingQuery() != null) {
            return update.shippingQuery().from().id();
        } else if (update.channelPost() != null) {
            return update.channelPost().from().id();
        } else if (update.chatJoinRequest() != null) {
            return update.chatJoinRequest().from().id();
        } else if (update.chatMember() != null) {
            return update.chatMember().from().id();
        } else if (update.chosenInlineResult() != null) {
            return update.chosenInlineResult().from().id();
        } else if (update.editedChannelPost() != null) {
            return update.editedChannelPost().from().id();
        } else if (update.editedMessage() != null) {
            return update.editedMessage().from().id();
        } else if (update.inlineQuery() != null) {
            return update.inlineQuery().from().id();
        } else if (update.myChatMember() != null) {
            return update.myChatMember().from().id();
        } else if (update.preCheckoutQuery() != null) {
            return update.preCheckoutQuery().from().id();
        }
        return null;
    }

    /**
     * Check whether incoming update created by user.
     *
     * @param update incoming update.
     * @return true if update is user-created, false - otherwise.
     */
    public static boolean isUserUpdate(Update update) {
        return extractSender(update) != null;
    }
}

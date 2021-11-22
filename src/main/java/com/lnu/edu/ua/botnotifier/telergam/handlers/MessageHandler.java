package com.lnu.edu.ua.botnotifier.telergam.handlers;

import com.lnu.edu.ua.botnotifier.telergam.cache.Cache;
import com.lnu.edu.ua.botnotifier.telergam.constants.BotMessageConstants;
import com.lnu.edu.ua.botnotifier.telergam.domain.BotUser;
import com.lnu.edu.ua.botnotifier.telergam.messagesender.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import static com.lnu.edu.ua.botnotifier.telergam.constants.UserConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MessageHandler implements Handler<Message> {

    private final MessageSender messageSender;
    private final Cache<BotUser> cache;

    public MessageHandler(MessageSender messageSender, Cache<BotUser> cache) {
        this.messageSender = messageSender;
        this.cache = cache;
    }

    @Override
    public void choose(Message message) {
        if(message.hasText()) {
            SendMessage sendMessage = new SendMessage();
            if (message.getText().equals("/start")) {
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setText(BotMessageConstants.START_MESSAGE.getMessage());
                sendMessage.setParseMode("HTML");
                ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboardRows = new ArrayList<>();
                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                KeyboardRow row3 = new KeyboardRow();
                row1.add("Розклад занять");
                row2.add("Розклад викладачів");
                row3.add("Зв'язок з деканатом");
                keyboardRows.add(row1);
                keyboardRows.add(row2);
                keyboardRows.add(row3);
                markup.setKeyboard(keyboardRows);
                markup.setResizeKeyboard(true);
                sendMessage.setReplyMarkup(markup);
                messageSender.sendMessage(sendMessage);
            } else if (message.getText().equals("Розклад занять")) {
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setText("Ти вибрав розклад занять");
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(
                        Collections.singletonList(
                                InlineKeyboardButton.builder()
                                        .text("ФЕІ-42")
                                        .callbackData(MONDAY)
                                        .build()));
                inlineKeyboardMarkup.setKeyboard(keyboard);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                messageSender.sendMessage(sendMessage);
            }
        }
    }
}

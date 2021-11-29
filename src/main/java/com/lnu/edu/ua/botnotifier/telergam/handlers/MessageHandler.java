package com.lnu.edu.ua.botnotifier.telergam.handlers;

import com.lnu.edu.ua.botnotifier.api.constants.BotMessageConstants;
import com.lnu.edu.ua.botnotifier.api.constants.SubGroupConstants;
import com.lnu.edu.ua.botnotifier.api.constants.TypeOfWeekConstants;
import com.lnu.edu.ua.botnotifier.api.constants.WeekdayConstants;
import com.lnu.edu.ua.botnotifier.api.dataobjects.User;
import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.mappers.UserMapper;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;
import com.lnu.edu.ua.botnotifier.telergam.messagesender.IMessageSender;
import com.lnu.edu.ua.botnotifier.utilities.TelegramUserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MessageHandler implements IHandler<Message> {

    private final IMessageSender messageSender;
    
    private IUserService userService;
    

    public MessageHandler(IMessageSender messageSender) {
        this.messageSender = messageSender;
    }
    
    private UserDbi setDefaultUserValues(UserDbi user) {
        user.setSubGroup(SubGroupConstants.FIRST);
        user.setTypeOfWeek(TypeOfWeekConstants.NUMERATOR);
        user.setWeekDay(WeekdayConstants.MONDAY);
        return user;
    }

    @Override
    public void choose(Message message) {
    	User user = UserMapper.mapFromDbi(userService.findById(message.getFrom().getId()));
    	if(user == null) {
    		UserDbi userDbi = TelegramUserUtils.mapToDbi(message.getFrom());
    		userDbi = setDefaultUserValues(userDbi);
    		userService.save(userDbi);
    		user = UserMapper.mapFromDbi(userDbi);
    	}
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
                                        .callbackData(WeekdayConstants.MONDAY)
                                        .build()));
                inlineKeyboardMarkup.setKeyboard(keyboard);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                messageSender.sendMessage(sendMessage);
            }
        }
    }
    
    @Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}

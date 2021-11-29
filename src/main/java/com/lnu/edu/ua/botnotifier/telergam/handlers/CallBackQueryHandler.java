package com.lnu.edu.ua.botnotifier.telergam.handlers;

import com.lnu.edu.ua.botnotifier.api.constants.SubGroupConstants;
import com.lnu.edu.ua.botnotifier.api.constants.TypeOfWeekConstants;
import com.lnu.edu.ua.botnotifier.api.constants.WeekdayConstants;
import com.lnu.edu.ua.botnotifier.api.dataobjects.User;
import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.mappers.UserMapper;
import com.lnu.edu.ua.botnotifier.api.services.IPairService;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;
import com.lnu.edu.ua.botnotifier.telergam.messagesender.IMessageSender;
import com.lnu.edu.ua.botnotifier.telergam.messagesender.MessageSender;
import com.lnu.edu.ua.botnotifier.utilities.TelegramUserUtils;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallBackQueryHandler implements IHandler<CallbackQuery> {

    private IPairService pairService;
    private IMessageSender messageSender;
    
    private IUserService userService;
    

    public CallBackQueryHandler(IMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    private UserDbi setDefaultUserValues(UserDbi user) {
        user.setSubGroup(SubGroupConstants.FIRST);
        user.setTypeOfWeek(TypeOfWeekConstants.NUMERATOR);
        user.setWeekDay(WeekdayConstants.MONDAY);
        return user;
    }

    @Autowired
    public void setPairService(IPairService pairService) {
        this.pairService = pairService;
    }

    @Override
    public void choose(CallbackQuery callbackQuery) {
    	User user = UserMapper.mapFromDbi(userService.findById(callbackQuery.getFrom().getId()));
    	if(user == null) {
    		UserDbi userDbi = TelegramUserUtils.mapToDbi(callbackQuery.getFrom());
    		userDbi = setDefaultUserValues(userDbi);
    		userService.save(userDbi);
    		user = UserMapper.mapFromDbi(userDbi);
    	}
    	
    	changeUserData(callbackQuery.getData(), user);
        List<PairDbi> pairs = pairService.findAllByUserData("ФЕІ-42", user.getWeekDay(), user.getTypeOfWeek(), user.getSubGroup());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(generateWeekDayButtons());
        List<InlineKeyboardButton> weekTypeButtonsRow2 = new ArrayList<>();
        weekTypeButtonsRow2.add(InlineKeyboardButton.builder()
                .text("Чисельник")
                .callbackData(TypeOfWeekConstants.NUMERATOR)
                .build());
        weekTypeButtonsRow2.add(InlineKeyboardButton.builder()
                .text("Знаменник")
                .callbackData(TypeOfWeekConstants.DENOMINATOR)
                .build());
        List<InlineKeyboardButton> subgroupNumberButtonsRow3 = new ArrayList<>();
        subgroupNumberButtonsRow3.add(InlineKeyboardButton.builder()
                .text("Перша підгрупа")
                .callbackData(SubGroupConstants.FIRST)
                .build());
        subgroupNumberButtonsRow3.add(InlineKeyboardButton.builder()
                .text("Друга підгрупа")
                .callbackData(SubGroupConstants.SECOND)
                .build());
        keyboard.add(weekTypeButtonsRow2);
        keyboard.add(subgroupNumberButtonsRow3);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        messageSender.sendEditMessage(
                EditMessageText.builder()
                        .text(generateSchedule(pairs, user))
                        .parseMode("HTML")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .replyMarkup(inlineKeyboardMarkup)
                        .build()
        );

    }

    private void changeUserData(String callbackQueryData, User user) {
        switch (callbackQueryData) {
            case WeekdayConstants.MONDAY:
            	user.setWeekDay(WeekdayConstants.MONDAY);
                break;
            case WeekdayConstants.TUESDAY:
            	user.setWeekDay(WeekdayConstants.TUESDAY);
                break;
            case WeekdayConstants.WEDNESDAY:
            	user.setWeekDay(WeekdayConstants.WEDNESDAY);
                break;
            case WeekdayConstants.THURSDAY:
            	user.setWeekDay(WeekdayConstants.THURSDAY);
                break;
            case WeekdayConstants.FRIDAY:
            	user.setWeekDay(WeekdayConstants.FRIDAY);
                break;
            case TypeOfWeekConstants.NUMERATOR:
            	user.setTypeOfWeek(TypeOfWeekConstants.NUMERATOR);
                break;
            case TypeOfWeekConstants.DENOMINATOR:
            	user.setTypeOfWeek(TypeOfWeekConstants.DENOMINATOR);
                break;
            case SubGroupConstants.FIRST:
            	user.setSubGroup(SubGroupConstants.FIRST);
                break;
            case SubGroupConstants.SECOND:
            	user.setSubGroup(SubGroupConstants.SECOND);
                break;
        }
        userService.save(UserMapper.mapToDbi(user));
    }

    private List<InlineKeyboardButton> generateWeekDayButtons() {
        List<InlineKeyboardButton> weekDayButtonsRow1 = new ArrayList<>();
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Пн")
                .callbackData(WeekdayConstants.MONDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Вт")
                .callbackData(WeekdayConstants.TUESDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Ср")
                .callbackData(WeekdayConstants.WEDNESDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Чт")
                .callbackData(WeekdayConstants.THURSDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Пт")
                .callbackData(WeekdayConstants.FRIDAY)
                .build());
        return weekDayButtonsRow1;
    }

    private String generateSchedule(List<PairDbi> pairs, User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("Розклад групи: ФеІ-42")
        .append("\nТиждень: ").append(user.getTypeOfWeek())
        .append("\nДень: ").append(user.getWeekDay())
        .append("\nВибрана підгрупа: ").append(user.getSubGroup()).append("\n");
        for (PairDbi pair : pairs) {
            sb.append("\n<b>Номер: </b>").append(pair.getPairNumber()).append(" ")
                    .append(pair.getSubjectName())
                    .append("\n<b>Викладач: </b>").append(pair.getTeacher().getLastName())
                    .append("\n<b>Аудиторія: </b>").append(pair.getClassroom())
                    .append("\n<b>Вид пари: </b>").append(pair.getSubjectType()).append("\n")
                    .append(StringUtils.repeat(EmojiParser.parseToUnicode(":heavy_minus_sign:"),7));
        }
        return sb.toString();
    }

    @Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
    
}

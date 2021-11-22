package com.lnu.edu.ua.botnotifier.telergam.handlers;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.services.IPairService;
import com.lnu.edu.ua.botnotifier.telergam.cache.Cache;
import com.lnu.edu.ua.botnotifier.telergam.constants.UserConstants;
import com.lnu.edu.ua.botnotifier.telergam.domain.BotUser;
import com.lnu.edu.ua.botnotifier.telergam.messagesender.MessageSender;
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

import static com.lnu.edu.ua.botnotifier.telergam.constants.UserConstants.*;

@Component
public class CallBackQueryHandler implements Handler<CallbackQuery> {

    private IPairService pairService;
    private MessageSender messageSender;
    private final Cache<BotUser> userCache;

    public CallBackQueryHandler(MessageSender messageSender, Cache<BotUser> userCache) {
        this.messageSender = messageSender;
        this.userCache = userCache;
    }

    private BotUser generateUserCacheFromMessage(CallbackQuery callbackQuery) {
        BotUser user = new BotUser();
        user.setUsername(callbackQuery.getMessage().getFrom().getUserName());
        user.setId(callbackQuery.getMessage().getChatId());
        user.setFavoriteGroups(new ArrayList<>());
        user.setSubGroup(FIRST);
        user.setTypeOfWeek(NUMERATOR);
        user.setWeekDay(MONDAY);
        return user;
    }

    @Autowired
    public void setPairService(IPairService pairService) {
        this.pairService = pairService;
    }

    @Override
    public void choose(CallbackQuery callbackQuery) {
        BotUser botUser = userCache.findById(callbackQuery.getMessage().getChatId());
        if (botUser == null) {
            botUser = generateUserCacheFromMessage(callbackQuery);
            userCache.add(botUser);
        }
        checkCallbackQueryData(callbackQuery.getData(), botUser);
        List<PairDbi> pairs = pairService.findAllByGroupCodeAndDayNameAndWeekType("ФЕІ-42", botUser.getWeekDay(),
                botUser.getTypeOfWeek());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(generateWeekDayButtons());
        List<InlineKeyboardButton> weekTypeButtonsRow2 = new ArrayList<>();
        weekTypeButtonsRow2.add(InlineKeyboardButton.builder()
                .text("Чисельник")
                .callbackData(NUMERATOR)
                .build());
        weekTypeButtonsRow2.add(InlineKeyboardButton.builder()
                .text("Знаменник")
                .callbackData(DENOMINATOR)
                .build());
        keyboard.add(weekTypeButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        messageSender.sendEditMessage(
                EditMessageText.builder()
                        .text(generateSchedule(pairs, botUser))
                        .parseMode("HTML")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .replyMarkup(inlineKeyboardMarkup)
                        .build()
        );

    }

    private void checkCallbackQueryData(String callbackQueryData, BotUser botUser) {
        switch (callbackQueryData) {
            case MONDAY:
                botUser.setWeekDay(MONDAY);
                break;
            case TUESDAY:
                botUser.setWeekDay(TUESDAY);
                break;
            case WEDNESDAY:
                botUser.setWeekDay(WEDNESDAY);
                break;
            case THURSDAY:
                botUser.setWeekDay(THURSDAY);
                break;
            case FRIDAY:
                botUser.setWeekDay(FRIDAY);
                break;
            case NUMERATOR:
                botUser.setTypeOfWeek(NUMERATOR);
                break;
            case DENOMINATOR:
                botUser.setTypeOfWeek(DENOMINATOR);
                break;
        }
    }

    private List<InlineKeyboardButton> generateWeekDayButtons() {
        List<InlineKeyboardButton> weekDayButtonsRow1 = new ArrayList<>();
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Пн")
                .callbackData(MONDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Вт")
                .callbackData(TUESDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Ср")
                .callbackData(WEDNESDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Чт")
                .callbackData(THURSDAY)
                .build());
        weekDayButtonsRow1.add(InlineKeyboardButton.builder()
                .text("Пт")
                .callbackData(FRIDAY)
                .build());
        return weekDayButtonsRow1;
    }

    private String generateSchedule(List<PairDbi> pairs, BotUser botUser) {
        StringBuilder sb = new StringBuilder();
        sb.append("Розклад групи: ФеІ-42\n").append("Тиждень: ").append(botUser.getTypeOfWeek())
                .append("\nВибрана підгрупа: Загальна\n");
        for (PairDbi pair : pairs) {
            sb.append("\n<b>Номер: </b>").append(pair.getPairNumber()).append(" ")
                    .append(pair.getSubjectName())
                    .append("\n<b>Викладач: </b>").append(pair.getTeacher().getLastName())
                    .append("\n<b>Аудиторія: </b>").append(pair.getClassroom())
                    .append("\n<b>Вид пари: </b>").append(pair.getSubjectType())
                    .append("\n<b>Час: </b>").append(pair.getUpdatingTime()).append("\n")
                    .append(StringUtils.repeat(EmojiParser.parseToUnicode(":heavy_minus_sign:"),7));
        }
        return sb.toString();
    }
}

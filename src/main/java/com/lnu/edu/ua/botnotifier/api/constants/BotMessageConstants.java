package com.lnu.edu.ua.botnotifier.api.constants;

import com.vdurmont.emoji.EmojiParser;

public enum BotMessageConstants {
    START_MESSAGE("<b>Вітаю тебе, мій друже! Чим можу тобі допомогти?</b>" +
            "\n :notebook: Доступні функції:\n" +
            "- розклад студентів\n" +
            "- розклад викладачів\n" +
            "- зв'язок з деканатом\n" +
            "- актуальні IT-курси\n" +
            "- вакансії в ІТ-компаніях"),
    INITIALEMOJI("Here is a smile emoji: :smile:\n\n Here is alien emoji: :alien:");
//    MONDAY("Понеділок"),
//    TUESDAY("Вівторок"),
//    WEDNESDAY("Середа"),
//    THURSDAY("Четвер"),
//    FRIDAY("Пятниця"),
//    NUMERATOR("Чисельник"),
//    DENOMINATOR("Знаменник");


    private String message;

    BotMessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return EmojiParser.parseToUnicode(message);
    }
}

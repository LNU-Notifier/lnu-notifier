package com.lnu.edu.ua.botnotifier.telergam.messagesender;

import com.lnu.edu.ua.botnotifier.telergam.UniversityBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageSenderImpl implements MessageSender {

    private UniversityBot universityBot;

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            universityBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEditMessage(EditMessageText editMessageText) {
        try {
            universityBot.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setUniversityBot(UniversityBot universityBot) {
        this.universityBot = universityBot;
    }
}

package com.lnu.edu.ua.botnotifier.telergam.messagesender;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface IMessageSender {

    void sendMessage(SendMessage sendMessage);

    void sendEditMessage(EditMessageText editMessageText);
}

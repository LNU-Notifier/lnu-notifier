package com.lnu.edu.ua.botnotifier.telergam.processors;

import com.lnu.edu.ua.botnotifier.telergam.handlers.CallBackQueryHandler;
import com.lnu.edu.ua.botnotifier.telergam.handlers.MessageHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class DefaultProcessor implements Processor {

    private CallBackQueryHandler callBackQueryHandler;
    private MessageHandler messageHandler;

    public DefaultProcessor(@Lazy CallBackQueryHandler callBackQueryHandler, @Lazy MessageHandler messageHandler) {
        this.callBackQueryHandler = callBackQueryHandler;
        this.messageHandler = messageHandler;
    }

    @Override
    public void executeMessage(Message message) {
        messageHandler.choose(message);
    }

    @Override
    public void executeCallBackQuery(CallbackQuery callbackQuery) {
        callBackQueryHandler.choose(callbackQuery);
    }
}

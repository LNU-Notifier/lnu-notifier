package com.lnu.edu.ua.botnotifier.telergam.cache;

import com.lnu.edu.ua.botnotifier.telergam.domain.BotUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserBotCache implements Cache<BotUser> {
    private HashMap<Long, BotUser> users;

    public UserBotCache() {
        users = new HashMap<>();
    }

    @Override
    public void add(BotUser botUser) {
        if (botUser.getId() != null) {
            users.put(botUser.getId(), botUser);
        }
    }

    @Override
    public void remove(BotUser botUser) {
        users.remove(botUser.getId());
    }

    @Override
    public BotUser findById(Long id) {
        return users.get(id);
    }

    @Override
    public List<BotUser> getAll() {
        return new ArrayList<>(users.values());
    }
}

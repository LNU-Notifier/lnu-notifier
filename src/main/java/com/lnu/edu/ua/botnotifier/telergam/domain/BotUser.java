package com.lnu.edu.ua.botnotifier.telergam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotUser {
    private Long id;
    private String username;
    private String subGroup;
    private String typeOfWeek;
    private List<String> favoriteGroups;
    private String weekDay;
}

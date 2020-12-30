package com.atec.samuraiinventory;

import com.atec.samuraiinventory.jira.settings.utils.QueriesSetting;
import com.atec.samuraiinventory.jira.settings.utils.QueriesSettingObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        QueriesSettingObject result = new QueriesSettingObject();
        try {
            result = objectMapper.readValue(QueriesSetting.config, QueriesSettingObject.class);
        } catch (IOException e) {
         e.printStackTrace();
        }
        System.out.println(result.toString());
    }
}

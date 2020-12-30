package com.atec.samuraiinventory.jira.settings.utils;

import com.atec.samuraiinventory.jira.dto.Hardware;
import com.atec.samuraiinventory.jira.settings.utils.pojo.HardwareField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;

public class QueriesSettingObject {
    @JsonProperty("hardware")
    HardwareField[] hardware;

    public HardwareField[] getHardware() {
        return hardware;
    }

    public void setHardware(HardwareField[] hardware) {
        this.hardware = hardware;
    }

    @Override
    public String toString() {
        return "QueriesSettingObject{" +
                "hardware=" + Arrays.toString(hardware) +
                '}';
    }
}

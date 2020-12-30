package com.atec.samuraiinventory.dao;

import com.atec.samuraiinventory.jira.dto.Hardware;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HardwareDAO {
    private static final Set<Hardware> hardwares = new HashSet<>();

    public static void addItem(Hardware hardware){
        hardwares.add(hardware);
    }

    public static Set<Hardware> getHardwares() {
        return hardwares;
    }
}

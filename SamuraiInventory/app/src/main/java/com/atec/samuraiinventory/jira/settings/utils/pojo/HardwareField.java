package com.atec.samuraiinventory.jira.settings.utils.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class HardwareField {
    @JsonProperty("name")
    String name;
    @JsonProperty("objectTypeId")
    String objectTypeId;
    @JsonProperty("attributesToDisplayIds")
    AttributesToDisplayIds[] attributesToDisplayIds;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public AttributesToDisplayIds[] getAttributesToDisplayIds() {
        return attributesToDisplayIds;
    }

    public void setAttributesToDisplayIds(AttributesToDisplayIds[] attributesToDisplayIds) {
        this.attributesToDisplayIds = attributesToDisplayIds;
    }

    @Override
    public String toString() {
        return "HardwareField{" +
                "name='" + name + '\'' +
                ", objectTypeId='" + objectTypeId + '\'' +
                ", attributesToDisplayIds=" + Arrays.toString(attributesToDisplayIds) +
                '}';
    }
}

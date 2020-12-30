package com.atec.samuraiinventory.jira.settings.utils.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class AttributesToDisplayIds {
    @JsonProperty("title")
    private String title;

    @JsonProperty("object_name")
    private String object_name;

    @JsonProperty("id")
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AttributesToDisplayIds{" +
                "title='" + title + '\'' +
                ", object_name='" + object_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

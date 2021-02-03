package com.atec.samuraiinventory.dao;

import com.atec.samuraiinventory.jira.JiraInsightService;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HardwareDAO {
    private JiraInsightService jiraInsightService;
    private JSONArray hardwares;
    private JSONObject settings;

    public HardwareDAO(JSONObject settings) {
        this.settings = settings;
        hardwares = new JSONArray();
        jiraInsightService = new JiraInsightService(settings);
    }

    public void update() {
        boolean isLogin = jiraInsightService.login();
        if (isLogin) {
            try {
                JSONArray hardwareSettings = settings.getJSONArray("hardware");
                List<String> attributesToDisplayNames = new ArrayList<>();

                for (int k = 0; k < hardwareSettings.length(); k++) {
                    JSONObject hardwareFieldsIDs = hardwareSettings.getJSONObject(k);
                    JSONArray attributesToDisplayIdsOnSetting = hardwareFieldsIDs.getJSONArray("attributesToDisplayIds");

                    JSONArray attributesToDisplayIds = new JSONArray();
                    for (int m = 0; m < attributesToDisplayIdsOnSetting.length(); m++) {
                        attributesToDisplayNames.add(attributesToDisplayIdsOnSetting.getJSONObject(m).getString("fieldName"));
                        attributesToDisplayIds.put(attributesToDisplayIdsOnSetting.getJSONObject(m).getString("fieldID"));
                    }

                    System.out.println(attributesToDisplayNames);

                    String objectTypeId = hardwareFieldsIDs.getString("objectTypeId");
                    String hardwaresResponse = jiraInsightService.getHardwares(objectTypeId, attributesToDisplayIds.toString());
                    JSONObject responseJSON = new JSONObject(hardwaresResponse);
                    JSONArray objectEntries = responseJSON.getJSONArray("objectEntries");
                    for (int i = 0; i < objectEntries.length(); i++) {
                        JSONObject hardware = objectEntries.getJSONObject(i);
                        JSONArray attributes = hardware.getJSONArray("attributes");
                        JSONObject newHardware = new JSONObject();
                        for (int j = 0; j < attributes.length(); j++) {
                            JSONArray objectAttributeValues = attributes.getJSONObject(j).getJSONArray("objectAttributeValues");
                            String fieldName = attributesToDisplayNames.get(j);

                            Pattern pattern = Pattern.compile("\\{(.*?)\\}");
                            Matcher matcher = pattern.matcher(fieldName);

                            String objectName = "";
                            while (matcher.find()){
                                objectName = matcher.group();
                                fieldName = fieldName.replace(objectName, "");
                                objectName = objectName.replace("{","").replace("}","");
                            }

                            if (objectAttributeValues.length() != 0){
                                String value = "";
                                if (objectName.isEmpty()){
                                    value = objectAttributeValues.getJSONObject(0).getString("value");
                                }else {
                                    value = objectAttributeValues.getJSONObject(0).getJSONObject(objectName).getString("name");
                                }

                                newHardware.put(fieldName, value);
                            }
                        }

                        System.out.println(newHardware);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONArray getHardwares() {
        return hardwares;
    }
}

package com.atec.samuraiinventory.jira;

import com.atec.samuraiinventory.dao.HardwareDAO;
import com.atec.samuraiinventory.jira.dto.Hardware;
import com.atec.samuraiinventory.jira.settings.utils.QueriesSetting;
import com.atec.samuraiinventory.jira.settings.utils.QueriesSettingObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class Jira {
    private static String cookie;
    private static HttpURLConnection connection;

    public static void login(String userName, String password) {
        try {
            URL url = new URL("http://atlassian2.ff.local:8081/login.jsp");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("os_username", userName);
            connection.addRequestProperty("os_password", password);
            System.out.println("code");
            int code = connection.getResponseCode();
            System.out.println(code);
            if (code == HttpURLConnection.HTTP_OK) {
                List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
                for (String e : cookies) {
                    setCookie(e);
                }
            }

            connection.disconnect();
        } catch (IOException ignored) {

        } finally {
            connection.disconnect();
        }
    }


    public static void getHardware() {
        try {
            URL url = new URL("http://atlassian2.ff.local:8081/rest/insight/1.0/object/navlist/iql");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic YnJvdGhlcnRlYW06S2g1enlSYUtjdA==");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cookie", cookie);
            connection.setDoOutput(true);

            System.out.println(getQueriesSetting());

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String json = "{\n" +
                    "\"asc\": 1,\n" +
                    "\"attributesToDisplay\": {\"attributesToDisplayIds\": [\"49\", \"47\", \"84\", \"48\", \"85\", \"83\", \"80\", \"78\", \"46\", \"81\", \"82\", \"79\"]},\n" +
                    "\"includeAttributes\": true,\n" +
                    "\"iql\": \"\",\n" +
                    // "\"objectId\": \"6487\",\n" +
                    "\"objectSchemaId\": \"1\",\n" +
                    "\"objectTypeId\": \"6\",\n" +
                    "\"orderByTypeAttrId\": \"79\",\n" +
                    "\"page\": 1,\n" +
                    "\"resultsPerPage\": 9999\n" +
                    "}";

            out.write(json.getBytes(StandardCharsets.UTF_8), 0, json.length());
            out.flush();
            connection.connect();
            int code = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    response.append(line);
                }
            }

            JSONObject jsonObject = new JSONObject(response.toString());
            System.out.println("JSON");
            JSONArray objectEntries = jsonObject.getJSONArray("objectEntries");
            for (int i = 0; i < objectEntries.length(); i++) {
                JSONObject harndware = objectEntries.getJSONObject(i);
                JSONArray attributes = harndware.getJSONArray("attributes");
                JSONObject item = new JSONObject();
                Hardware hardware = new Hardware();
                for (int j = 0; j < attributes.length(); j++) {
                    JSONArray objectAttributeValues = attributes.getJSONObject(j).getJSONArray("objectAttributeValues");
                    String value;
                    switch (j) {
                        case 0:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("key", value);
                            hardware.setKey(value);
                            break;
                        case 1:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("name", value);
                            hardware.setName(value);
                            break;
                        case 2:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("details", value);
                            hardware.setDetails(value);
                            break;
                        case 3:
                            value = objectAttributeValues.getJSONObject(0).getJSONObject("user").getString("name");
                            item.put("responsible", value);
                            hardware.setResponsible(value);
                            break;
                        case 4:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("department", value);
                            hardware.setDepartment(value);
                            break;
                        case 5:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("placement", value);
                            hardware.setPlacement(value);
                            break;
                        case 6:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("price", value);
                            hardware.setPrice(value);
                            break;
                        case 7:
                            value = objectAttributeValues.getJSONObject(0).getJSONObject("status").getString("name");
                            item.put("status", value);
                            hardware.setStatus(value);
                            break;
                        case 8:
                            value = objectAttributeValues.getJSONObject(0).getString("value");
                            item.put("comment", value);
                            hardware.setComment(value);
                            break;
                    }

                }
                //HardwareDAO.addItem(hardware);
               // System.out.println();
            }

            connection.disconnect();
        } catch (IOException | JSONException ignored) {
        } finally {
            connection.disconnect();
        }
    }

    private static void setCookie(String s) {
        System.out.println(s);
        if (s.contains("JSESSIONID")) {
            cookie = s;
        }
    }

    private static QueriesSettingObject getQueriesSetting() {
        ObjectMapper objectMapper = new ObjectMapper();
        QueriesSettingObject result = new QueriesSettingObject();
        try {
            result = objectMapper.readValue(QueriesSetting.config, QueriesSettingObject.class);
        } catch (IOException e) {

        }

        return result;

    }


}
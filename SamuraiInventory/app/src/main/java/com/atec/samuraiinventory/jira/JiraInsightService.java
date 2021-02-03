package com.atec.samuraiinventory.jira;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class JiraInsightService {
    private String cookie;
    private HttpURLConnection connection;
    private final JSONObject settings;

    public JiraInsightService(JSONObject settings) {
        this.settings = settings;
    }

    public boolean login() {
        try {
            String loginURL = settings.getJSONObject("JiraInsight").getString("loginURL");
            String userName = settings.getJSONObject("JiraInsight").getString("username");
            String password = settings.getJSONObject("JiraInsight").getString("password");

            URL url = new URL(loginURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("os_username", userName);
            connection.addRequestProperty("os_password", password);

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
                for (String e : cookies) {
                    System.out.println(e);
                    setCookie(e);
                }

                return true;
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return false;
    }

    public String getHardwares(String objectTypeId, String attributesToDisplayIds) {
        StringBuilder response = new StringBuilder();
        try {
            JSONArray hardwareSettings = settings.getJSONArray("hardware");
            for (int k = 0; k < hardwareSettings.length(); k++) {
                String navlistURLValue = settings.getJSONObject("JiraInsight").getString("navlistURL");
                URL navlistURL = new URL(navlistURLValue);
                connection = (HttpURLConnection) navlistURL.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Basic YnJvdGhlcnRlYW06S2g1enlSYUtjdA==");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Cookie", cookie);
                connection.setDoOutput(true);

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                String requestBody = "{\n" +
                        "\"asc\": 1,\n" +
                        "\"attributesToDisplay\": {\"attributesToDisplayIds\": " + attributesToDisplayIds + "},\n" +
                        "\"includeAttributes\": true,\n" +
                        "\"iql\": \"\",\n" +
                        "\"objectSchemaId\": \"1\",\n" +
                        "\"objectTypeId\": \"" + objectTypeId + "\",\n" +
                        "\"page\": 1,\n" +
                        "\"resultsPerPage\": 9999\n" +
                        "}";

                out.write(requestBody.getBytes(StandardCharsets.UTF_8), 0, requestBody.length());
                out.flush();
                connection.connect();

                StringBuilder responseStringBuilder = new StringBuilder();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        responseStringBuilder.append(line);
                    }
                }

                response.append(responseStringBuilder.toString());
            }
        } catch (IOException | JSONException ignored) {
        } finally {
            connection.disconnect();
        }

        return response.toString();
    }

    private void setCookie(String s) {
        System.out.println(s);
        if (s.contains("JSESSIONID")) {
            cookie = s;
        }
    }
}
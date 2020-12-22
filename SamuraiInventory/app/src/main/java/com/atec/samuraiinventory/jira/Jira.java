package com.atec.samuraiinventory.jira;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class Jira {
    private static String cookie;
    private static HttpURLConnection connection;

    public static String getCookie() {
        return cookie;
    }

    public static void login(String userName, String password) {
        try {
            URL url = new URL("https://jira.marvin-it.com/login.jsp");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("os_username", userName);
            connection.addRequestProperty("os_password", password);

            int code = connection.getResponseCode();
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


    public static void getJiraObjects() {
        try {
            URL url = new URL("https://jira.marvin-it.com/rest/insight/1.0/object/navlist/iql");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic YnJvdGhlcnRlYW06S2g1enlSYUtjdA==");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cookie", cookie);
            connection.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String json = "{\n" +
                    "\"asc\": 1,\n" +
                    "\"attributesToDisplay\": {\"attributesToDisplayIds\": [\"589\", \"502\", \"503\", \"505\", \"504\", \"590\"]},\n" +
                    "\"includeAttributes\": true,\n" +
                    "\"iql\": \"\",\n" +
                    "\"objectId\": \"5421\",\n" +
                    "\"objectSchemaId\": \"1\",\n" +
                    "\"objectTypeId\": \"55\",\n" +
                    "\"page\": 1,\n" +
                    "\"resultsPerPage\": 25\n" +
                    "}";

            out.write(json.getBytes(StandardCharsets.UTF_8), 0, json.length());
            out.flush();
            connection.connect();
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            connection.disconnect();
        } catch (IOException ignored) {
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
}
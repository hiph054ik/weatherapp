package ru.ashmaev.weather;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloadJson {
    private static String api;
    private String choiceMeasurements;

    public void setChoiceMeasurements(String choiceMeasurements) {
        this.choiceMeasurements = choiceMeasurements;
    }

    public String downloadWebPage(String city) throws IOException {
        System.out.println(choiceMeasurements);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units="+ choiceMeasurements + "&appid=4e0bc824ff29005f6bb5cf8d86e7dff5";

        StringBuilder result = new StringBuilder();
        String line;

        URLConnection urlConnection = new URL(url).openConnection();

        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

        }

        return api = String.valueOf(result);


    }
    public String getMeasurementsSymbol(){
        if (choiceMeasurements.equals("metric"))
            return  "℃";
        if (choiceMeasurements.equals("imperial"))
            return  "℉";
        return null;
    }

    public static String getApi() {
        return api;

    }
}

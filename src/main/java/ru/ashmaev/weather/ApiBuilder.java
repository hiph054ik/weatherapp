package ru.ashmaev.weather;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class ApiBuilder {
    private static int temp_api;
    private static int speed_api;
    private static int pressure_api;
    private static String date_api;
    private static String time_api;

    private static String description_api;



    public  void getApi() throws IOException {




        String api = DownloadJson.getApi();


        FileWriter fl = new FileWriter(new File("api.txt"));
        fl.write(api);
        fl.close();
        Date date = new Date();


        JSONObject object = new JSONObject(api);
        temp_api = (int) object.getJSONObject("main").getDouble("temp");
        speed_api = (int) object.getJSONObject("wind").getDouble("speed");
        pressure_api = (int) object.getJSONObject("main").getDouble("pressure");
        JSONArray weather = object.getJSONArray("weather");
        description_api = weather.getJSONObject(0).getString("main");

        Date now = Calendar.getInstance().getTime();
        date_api  = DateFormat.getDateInstance().format(now);
        String time = date.toString();
        String timeCut = time.substring(11,19);
        System.out.println(timeCut);
        time_api = timeCut;

        System.out.println(description_api);



    }
    public byte weatherCondition(){
        if (description_api.equals("Rain") || description_api.equals("Drizzle"))
            return 0;
        if (description_api.equals("Snow"))
            return 1;
        if (description_api.equals("Thunderstorm"))
            return 2;
        if (description_api.equals("Clear"))
            return 3;
        if (description_api.equals("Clouds"))
            return 4;
        return -1;
    }



    public int getTemp_api() {
        return temp_api;
    }

    public int getSpeed_api() {
        return speed_api;
    }

    public long getPressure_api() {
        return pressure_api;
    }

    public String getDate_api() {
        return date_api;
    }

    public String getTime_api() {
        return time_api;
    }
}

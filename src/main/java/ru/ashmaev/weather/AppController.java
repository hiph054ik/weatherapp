package ru.ashmaev.weather;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AppController {

    @FXML
    private TextField city_user;

    @FXML
    private Button select_button;
    @FXML
    private Text date_api;

    @FXML
    private Text pressure_api;

    @FXML
    private Text speed_api;

    @FXML
    private Text temp_api;

    @FXML
    private Text time_api;
    @FXML
    private ImageView weatherСonditionsIcon;
    @FXML
    private Text weatherCondition;

    @FXML
    private Label error;
    @FXML
    private ChoiceBox<String> unit_selection;
    ApiBuilder apiBuilder = new ApiBuilder();

    @FXML
    void initialize() {
        DownloadJson downloadJson = new DownloadJson();
        downloadJson.setChoiceMeasurements("metric");
        String[] measurements = {"Цельсий", "Фаренгейт"};
        unit_selection.setValue("Цельсий");

        unit_selection.getItems().addAll(measurements);
        unit_selection.setOnAction(event -> {
//            System.out.println(unit_selection.getValue());
            if (unit_selection.getValue().equals("Фаренгейт"))
                downloadJson.setChoiceMeasurements("imperial");
            if (unit_selection.getValue().equals("Цельсий"))
                downloadJson.setChoiceMeasurements("metric");
        });




        select_button.setOnAction(event -> {


            String city = city_user.getText();
            if (!city.isEmpty()) {

                try {
                    downloadJson.downloadWebPage(city);
                    apiBuilder.getApi();
                } catch (IOException e) {
                    error.setText("Не правильно введен город");
                }
                temp_api.setText(String.valueOf(apiBuilder.getTemp_api()) + downloadJson.getMeasurementsSymbol());
                speed_api.setText(String.valueOf(apiBuilder.getSpeed_api()));
                pressure_api.setText(String.valueOf(apiBuilder.getPressure_api()));
                date_api.setText(apiBuilder.getDate_api());
                time_api.setText((apiBuilder.getTime_api()));
            } else {
                error.setText("Введите город!");
            }
            // состояние погоды в изображениях
            if (apiBuilder.weatherCondition() == 0) {
                Image image = new Image(String.valueOf(MainGUI.class.getResource("pictures/rain.png")));
                weatherСonditionsIcon.setImage(image);
            }
            if (apiBuilder.weatherCondition() == 1) {
                Image image = new Image(String.valueOf(MainGUI.class.getResource("pictures/snow.png")));
                weatherСonditionsIcon.setImage(image);
            }
            if (apiBuilder.weatherCondition() == 2) {
                Image image = new Image(String.valueOf(MainGUI.class.getResource("pictures/storm")));
                weatherСonditionsIcon.setImage(image);
            }
            if (apiBuilder.weatherCondition() == 3) {
                Image image = new Image(String.valueOf(MainGUI.class.getResource("pictures/sun.png")));
                weatherСonditionsIcon.setImage(image);
            }
            if (apiBuilder.weatherCondition() == 4) {
                Image image = new Image(String.valueOf(MainGUI.class.getResource("pictures/sun-cloud.png")));
                weatherСonditionsIcon.setImage(image);
            }
            //Текстовое состояние погоды
            if (apiBuilder.weatherCondition() == 0) {
                weatherCondition.setText("Дождь");
            }
            if (apiBuilder.weatherCondition() == 1) {
                weatherCondition.setText("Снег");
            }
            if (apiBuilder.weatherCondition() == 2) {
                weatherCondition.setText("Гроза");
            }
            if (apiBuilder.weatherCondition() == 3) {
                weatherCondition.setText("Солнечно");
            }
            if (apiBuilder.weatherCondition() == 4) {
                weatherCondition.setText("Облачно");
            }
        });

    }


}

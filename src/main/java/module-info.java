module ru.ashmaev.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;



    opens ru.ashmaev.weather to javafx.fxml;
    exports ru.ashmaev.weather;
}
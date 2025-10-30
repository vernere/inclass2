package com.example;

import java.io.IOException;
import java.time.ZoneId;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class PrimaryController {
    Locale local;
    ResourceBundle r;
    double bmiStatus;

    @FXML
    private Label lblWeight;

    @FXML
    private TextField tfWeight;

    @FXML
    private Label lblHeight;

    @FXML
    private TextField tfHeight;

    @FXML
    private Label lblResult;

    @FXML
    private Label lblLocalTime;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;
    @FXML
    private Button btnCalculate;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void onCalculateClick() {
        try {
            double weight = Double.parseDouble(tfWeight.getText());
            double height = Double.parseDouble(tfHeight.getText());
            height = height / 100;
            double bmi = weight / (height * height);
            bmiStatus = bmi;
            lblResult.setText(String.format("%.2f", bmi));
        } catch (NumberFormatException e) {
            lblResult.setText("Please enter valid numbers");
        }
    }

    @FXML
    private void onENClick() {
        local = new Locale("en", "US");
        changeLang(local);

    }

    @FXML
    private void onFRClick() {
        local = new Locale("fr", "FR");
        changeLang(local);

    }

    @FXML
    private void onURClick() {
        local = new Locale("ur", "PK");
        changeLang(local);

    }

    @FXML
    private void onVIClick() {
        local = new Locale("vi", "VN");
        changeLang(local);
    }

    private void changeLang(Locale local) {
        r = ResourceBundle.getBundle("MessagesBundle", local);
        switch (local.getCountry()) {
            case "us":
                ZoneId zi = ZoneId.of("America/New_York");
                lblLocalTime.setText(r.getString("CurrentTime") + java.time.LocalTime.now(zi));
                break;
            case "FR":
                ZoneId ziFR = ZoneId.of("Europe/Paris");
                lblLocalTime.setText(r.getString("CurrentTime") + java.time.LocalTime.now(ziFR));
                break;
            case "PK":
                ZoneId ziPK = ZoneId.of("Asia/Karachi");
                lblLocalTime.setText(r.getString("CurrentTime") + java.time.LocalTime.now(ziPK));
                break;
            case "VN":
                ZoneId ziVN = ZoneId.of("Asia/Ho_Chi_Minh");
                lblLocalTime.setText(r.getString("CurrentTime") + java.time.LocalTime.now(ziVN));
                break;
            default:
                break;
        }
        lblWeight.setText(r.getString("lblWeight.text"));
        lblHeight.setText(r.getString("lblHeight.text"));
        lblResult.setText(r.getString("lblResult.text") + String.format("%.2f", bmiStatus));
        btnCalculate.setText(r.getString("btnCalculate.text"));

        button1.setText(r.getString("Button1"));
        button2.setText(r.getString("Button2"));
        button3.setText(r.getString("Button3"));
        button4.setText(r.getString("Button4"));
    }
}

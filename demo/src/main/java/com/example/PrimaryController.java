package com.example;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class PrimaryController {
    Locale currentLocale;
    Map<String, String> localizedStrings;
    double lastBmi;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");

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
    private void initialize() {
        currentLocale = new Locale("en", "US");
        setLanguage(currentLocale);
    }

    private void displayLocalTime(Locale locale) {
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("hh:mm:ss");

        String zoneId;
        switch (locale.getCountry()) {
            case "US":
                zoneId = "America/New_York";
                break;
            case "FR":
                zoneId = "Europe/Paris";
                break;
            case "PK":
                zoneId = "Asia/Karachi";
                break;
            case "VN":
                zoneId = "Asia/Ho_Chi_Minh";
                break;
            default:
                zoneId = "UTC";
                break;
        }
        String currentTime = java.time.LocalTime.now(ZoneId.of(zoneId)).format(dtf);
        String label = localizedStrings.getOrDefault("currentTime", "Current time: ");
        lblLocalTime.setText(label + currentTime);
    }

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        currentLocale = locale;
        localizedStrings = LocalizationService.getLocalizedStrings(locale);
        lblWeight.setText(localizedStrings.getOrDefault("weight", "Weight"));
        lblHeight.setText(localizedStrings.getOrDefault("height", "Height"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate", "Calculate"));
        button1.setText(localizedStrings.getOrDefault("button1", "EN"));
        button2.setText(localizedStrings.getOrDefault("button2", "FR"));
        button3.setText(localizedStrings.getOrDefault("button3", "UR"));
        button4.setText(localizedStrings.getOrDefault("button4", "VI"));
        displayLocalTime(locale);
    }

    @FXML
    private void onENClick() {
        setLanguage(new Locale("en", "US"));
    }

    @FXML
    private void onFRClick() {
        setLanguage(new Locale("fr", "FR"));
    }

    @FXML
    private void onURClick() {
        setLanguage(new Locale("ur", "PK"));
    }

    @FXML
    private void onVIClick() {
        setLanguage(new Locale("vi", "VN"));
    }

    @FXML
    public void onCalculateClick(ActionEvent actionEvent) {
        try {
            double weight = Double.parseDouble(tfWeight.getText());
            double height = Double.parseDouble(tfHeight.getText()) / 100.0;
            double bmi = weight / (height * height);
            lastBmi = bmi;
            DecimalFormat df = new DecimalFormat("#0.00");
            lblResult.setText(localizedStrings.getOrDefault("result", "Your BMI is") + " " + df.format(bmi));
            String language = currentLocale.getLanguage();

            BMIResultService.saveResult(weight, height * 100, bmi, language);
        } catch (NumberFormatException e) {
            lblResult.setText(localizedStrings.getOrDefault("invalid", "Invalid input"));
        }
    }
}

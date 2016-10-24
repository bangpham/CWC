package controller;

import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Account;
import model.Database;
import model.WaterCondition;
import model.WaterSourceReport;
import model.WaterType;
import java.sql.Timestamp;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Sheng on 9/19/16.
 * A controller for the app view
 */
public class ReportViewController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Label username;
    @FXML
    private AnchorPane reportForm;

    @FXML
    private Label reportNumber;
    @FXML
    private Label reporter;
    @FXML
    private Label longitude;
    @FXML
    private Label latitude;
    @FXML
    private Label waterType;
    @FXML
    private Label waterCondition;
    @FXML
    private Label dateCreated;


    private Main application;
    private boolean reportExpand = true;
    private static boolean isSourceReport = false;
    private static Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Locale.setDefault(Locale.US);

        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

    /**
     * Sets up the up view
     * @param application the main application of th app view
     */
    public void setApp(Main application){
        this.application = application;

        username.setText("" + application.getLoggedAccount());
    }

    /**
     * Setter of report numner
     */
    public void setReportNumber(int number) {
        reportNumber.setText("" + number);
    }

    /**
     * Setter of reporter.
     */
    public void setReporter(String name) {
        reporter.setText(name);
    }

    /**
     * Setter of date created.
     */
    public void setDateCreated(Timestamp date) {
        dateCreated.setText("" + date);
    }

    /**
     * Setter of report longitude location.
     */
    public void setLongitude(double longi) {
        longitude.setText("" + longi);
    }


    /**
     * Setter of latitude location.
     */
    public void setLatitude(double lati) {
        latitude.setText("" + lati);
    }

    /**
     * Setter of water type in report.
     */
    public void setWaterType(WaterType type) {
        waterType.setText("" + type);
    }

    /**
     * Setter of report water condition in report.
     */
    public void setWaterCondition(WaterCondition condition) {
        waterCondition.setText("" + condition);
    }

    @FXML
    private void menuPressed(){
    }

    /**
     * Go to application when pressed.
     */
    @FXML
    private void backToAppPressed() {
        application.gotoApp();
    }


}

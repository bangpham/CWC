package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import fxapp.Main;
import javafx.stage.Stage;
import model.Database;


/**
 * Controls the functionality of the login page
 */
public class LoginController extends AnchorPane implements Initializable {
    private Stage dialogStage;

    private boolean confirmLogin;
    @FXML
    private TextField accountId;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;

    private Main application;

    /**
     * Sets up the login page
     * @param application the main application of the login page
     */
    public void setApp(Main application) { this.application = application;}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     * Becomes active when is cancel is clicked at the login page
     */
    @FXML
    private void loginCancelPressed() {
        dialogStage.close();
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return  true if the user clicked the OK button
     */
    public boolean confirmLoginClicked() {
        return confirmLogin;
    }


    /**
     * Activate OKpressed() method when enter key is pressed.
     * @param event OK pressed.
     */
    @FXML
    public void enterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            OKPressed();
        }
    }

    /**
     * Log in user and pull user from database.
     * if user cannot log in, error message will be displayed.
     */
    @FXML
    private void OKPressed() {

        if (Database.login(accountId.getText(), password.getText())) {
            application.accountLogging(accountId.getText());
            confirmLogin = true;
            dialogStage.close();
        } else {
            errorMessage.setVisible(true);
        }
    }
}
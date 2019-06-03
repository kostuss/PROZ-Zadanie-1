package org.openjfx;

import org.openjfx.model.Calculations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;



/**
 * Main controller class, it describes the outlook of interface, controls input, 
 * handles interaction with computing module. 
 * 
 * @author Damian Koss
 *
 */
public class FXMLController implements Initializable {

    private boolean isComa;
    private Calculations calculations; 

    @FXML
    private Button zero;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button coma;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button multiply;
    @FXML
    private Button divide;
    @FXML
    private TextField fieldBig;
    @FXML
    private Button clear;
    @FXML
    private Button square;
    @FXML
    private Button back;
    @FXML
    private Button sign;
    @FXML
    private Button equals;
    @FXML
    private Button factorial;

    
    /**
     *Main initialization function of the controller. It handles buttons interaction with the use 
     *of lambda expressions.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	calculations = new Calculations();
    	fieldBig.setText("");

        Button[] numeric_buttons = {zero, one, two, three, four, five, six, seven, eight, nine};

        for (Button button : numeric_buttons) {
            button.setOnAction(event -> {
                fieldBig.setText(fieldBig.getText() + button.getText());
            });
        }
        coma.setOnAction(event -> {
            fieldBig.setText(fieldBig.getText() + coma.getText());
            isComa = true;
        });

        Button[] operations_buttons = {plus, minus, divide, multiply};

        for (Button button : operations_buttons) {
            button.setOnAction(event -> {
                if (!isComa) {
                    fieldBig.setText(fieldBig.getText() + ".0");
                    isComa = false;
                }
                fieldBig.setText(fieldBig.getText() + button.getText());
                isComa = false;
            });
        }

        clear.setOnAction(event -> {
            fieldBig.setText("");
            isComa = false;
        });
        square.setOnAction(event -> {
            fieldBig.setText(calculations.square(fieldBig.getText()));
            isComa = true;
        });
        back.setOnAction(event -> {
            try {
                char temp1 = fieldBig.getText().charAt(fieldBig.getText().length() - 1);
                fieldBig.setText(fieldBig.getText().substring(0, fieldBig.getText().length() - 1));

                if (temp1 == '-' || temp1 == '+' || temp1 == '*' || temp1 == '/') isComa = true;
                temp1 = fieldBig.getText().charAt(fieldBig.getText().length() - 1);
                if (temp1 == '.') {
                    fieldBig.setText(fieldBig.getText().substring(0, fieldBig.getText().length() - 1));
                    isComa = false;
                }

            } catch (StringIndexOutOfBoundsException e) {
                //using backspace with empty string
            }
        });
        sign.setOnAction(event -> {
        	try {
            fieldBig.setText(calculations.negate(fieldBig.getText()));
            isComa = true;
        	}catch(StringIndexOutOfBoundsException e) {isComa=false;}
        });
        equals.setOnAction(event -> {
            fieldBig.setText(calculations.calculate(fieldBig.getText()));
            isComa = true;
        });
        factorial.setOnAction(event -> {
        	try {
            fieldBig.setText(calculations.factorial(fieldBig.getText()));
            isComa = true;
        	}catch (NumberFormatException e) {isComa=false;}
        });
    }
}

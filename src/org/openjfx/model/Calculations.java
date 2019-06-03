package org.openjfx.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;
import java.util.List;
import java.util.Optional;


/**
 * Class which uses API JShell for computing used in calculator 
 * 
 * @author Damian Koss
 *
 */
public class Calculations {

        private JShell js;
        private String input;
        private Alert alert;
        private Float result;
        
       
        /**
         * Constructor of the class which initializes JShell API and alert handler. 
         */
        public Calculations(){
            js= JShell.create();
            input="";
            alert= new Alert(Alert.AlertType.ERROR);
        }
        
        /**
         * Function sets the input for JShell API.
         * @param var1  parameter to which input is set
         */
        private void setInput(String var1){
            input=var1;
        }
        
        /**
         * Function returns the current expression with the result
         * @return  the value returned by JShell API
         */
        private String getInput(){
            return input;
        }
        
        /**
         * Function handling interaction with JShell API 
         * @param var1  input string with the equation
         * @return - result of the computing
         */
        public String calculate(String var1){
            setInput(var1);
            List<SnippetEvent> events = js.eval(input);

            for (SnippetEvent e : events) {
                if (e.causeSnippet() == null && e.status() == Snippet.Status.VALID && e.value() != null) {
                    result= Float.parseFloat(e.value());
                    input=Float.toString(result);
                    if (input=="Infinity"){
                        alert.setTitle(Alert.AlertType.ERROR.name());
                        alert.setContentText("Przekroczono limit");
                        Optional<ButtonType> result = alert.showAndWait();
                        result.ifPresent(res -> System.out.println(res.getText()));
                        input="";
                        }
                }else {
                    alert.setTitle(Alert.AlertType.ERROR.name());
                    alert.setContentText("Blad skladni");
                    Optional<ButtonType> result = alert.showAndWait();
                    result.ifPresent(res -> System.out.println(res.getText()));
                    input="";
                }
            }
            return getInput();
        }
        
        /**
         * Function that calculates square
         * @param var1  expression of which we want to calculate square
         * @return - result 
         */
        public String square(String var1){
        	
            if(var1.equals("")) {input=var1; return input;}
           
        	String temp= calculate(var1);
            return calculate(temp+"*"+temp);
            
        }
        
        /**
         * Function that calculates factorial
         * @param var1  expression of which we want to calculate factorial
         * @return - result 
         */
        public String factorial(String var1){

        	result= Float.parseFloat(calculate(var1));
            int temp1=Math.round(result) ;
            input="1";
            for (int i=temp1; i>0; i--) {
                if (input==""){break;}
              input=calculate(input+"*"+i);
            }
            return input;
        }
        
        
        /**
         * Function that negates the input value
         * @param var1 - expression which we want to negate 
         * @return - result 
         */
        public String negate(String var1) {
            String temp= calculate(var1);
            if (temp.charAt(0)!='-')
                return "-"+temp;
            else return temp.substring(1);
        }
}
package com.mycompany.sampleproject;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carter Rows
 */
public class Application {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        try {
            System.out.println("1420 Project:\n");
            ScenarioProcessor.ParseAndExecute("json file path here");
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

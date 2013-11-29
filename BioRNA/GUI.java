import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class GUI extends Application implements Initializable{
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
	
	//Button to press for calculations
	@FXML
	private Button calcButton; //fx:id=calcButton
	
	@FXML
	private TextArea rnaTextArea; //fx:id=rnaTextArea
	
	@FXML
	private Label numCharslbl, linearlbl;
	
	@FXML
	private Label atomiclbl;
	
	@FXML
	private Label aminolbl; //fx:id=numCharslbl,atomiclbl,aminolbl
	
	
	//Bio to run calculations
	Bio bioCalc;
	
	@Override
	public void start(Stage primaryStage){
		String filename = "GUIScene.fxml";
		try{

			Pane page = (Pane)FXMLLoader.load(GUI.class.getResource(filename));
			Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("RNA Calculator (CPSC 452 F13)");
            primaryStage.show();
		}catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//Handler for Button[fx:id=calcButton] onAction
	@FXML
	private void calcPressed(ActionEvent event){
		if(event.getTarget() instanceof Button){
			runCalculation();		
			event.consume();
		}
	}
	
	@FXML  // This method is called by the FXMLLoader when initialization is complete
	void initialize(){
		System.out.println("Initialize");
		assert calcButton != null : "fx:id=\"calcButton\" was not injected";
		assert rnaTextArea != null : "fx:id=\"runTextArea\" was not injected";
		assert numCharslbl != null : "fx:id=\"numCharslbl\" was not injected";
		assert atomiclbl != null : "fx:id=\"atomiclbl\" was not injected";
		assert aminolbl != null : "fx:id=\"aminolbl\" was not injected";
		
		// initialize your logic here: all @FXML variables will have been injected
        // reset visibility and opacity of nodes - useful if you left your FXML in a 'bad' states 
		//resetVisibility();
	}
	
	void runCalculation(){
		//Instantiate bioCalc if it doesn't already exist
		if(bioCalc == null)
			bioCalc = new Bio();
		
		String rnaString = rnaTextArea.getText();
		atomiclbl.setText(bioCalc.getDNAmass(rnaString));
		aminolbl.setText(bioCalc.translate(rnaString));
		linearlbl.setText(bioCalc.getRNALinearLength(rnaString));
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		calcButton.setDisable(true);
		rnaTextArea.textProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldString, String newString) {
				//Angstroms
			    DecimalFormat f = new DecimalFormat("##");
			    f.setGroupingSize(3);
			    f.setGroupingUsed(true);
				numCharslbl.setText(f.format(newString.length()) + "");
				if(newString.toUpperCase().matches("([ACGT]+|[ACGU]+)")){
					calcButton.setDisable(false);
				}else{
					calcButton.setDisable(true);
				}
				
			}
		});	
		
	}
	
}

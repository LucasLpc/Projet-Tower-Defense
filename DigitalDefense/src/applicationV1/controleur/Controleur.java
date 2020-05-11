package applicationV1.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import applicationV1.modele.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controleur implements Initializable {
	private Environnement envTest;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		this.envTest = new Environnement(300,300);
		
	}
	
}
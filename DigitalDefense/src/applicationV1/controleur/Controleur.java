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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Controleur implements Initializable {
	private Timeline gameLoop;
	private Environnement map;
	private int nbmanche;
	//quand on aura fait le système d'argent et si on fait un système de points alors
	/*
	private int banque; <- venant des ennemis tués
	private int point;
	*/
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//this.envTest = new Environnement(300,300);
		initAnimation();
		gameLoop.play();
		
		
	}
	private void initAnimation() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame fps = new KeyFrame(Duration.seconds(0.01), 
				(eventEnding -> {
					if(Environnement.base.getHP == 0) {
						gameLoop.stop();
					}
				})
				
				);
				
		
	}
	
}
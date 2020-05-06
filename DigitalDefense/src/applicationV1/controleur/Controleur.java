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

	@FXML
	void ajouter(ActionEvent event) {
		if(nbEntité.getText().isEmpty()) {
			throw new Error("Veuillez entrer un nombre d'Animaux.");
		}
		int nbAnimaux = Integer.parseInt(nbEntité.getText());
		if(toggleMouton.isSelected())
			for(int i=0; i<nbAnimaux;i++) {
				Mouton m = new Mouton(envTest);
				this.envTest.ajouter(m);
				creerSprite(m);
				System.out.println("Ajout d'un mouton.");
			}
		else if(toggleLoup.isSelected())
			for(int i=0; i<nbAnimaux;i++) {
				Loup l = new Loup(envTest);
				this.envTest.ajouter(l);
				creerSprite(l);
				System.out.println("Ajout d'un loup.");
			}
		else throw new Error("Veuillez selectionner un type d'acteur.");
	}

	@FXML
	void unTour(ActionEvent event) {
		this.envTest.unTour();
		System.out.println(Mouton.getReproduction()+","+Loup.getReproduction());
//		refresh();
	}

	@FXML
	void faireDesTours(ActionEvent event) {
		int nbtours = Integer.parseInt(this.nbTours.getText());
		for (int i=0; i<nbtours; i++) {
			this.envTest.unTour();
//			refresh();
		}
	}
	void creerSprite(Ennemi a) {
		Circle r;
		if(a instanceof Mouton) {
			r = new Circle(2);
			r.setFill(Color.WHITE);
		}
		else {
			r = new Circle(3);
			r.setFill(Color.RED);
		}
		r.setOnMouseClicked((e) -> System.out.println(a));
		r.translateXProperty().bind(a.getXProperty());
		r.translateYProperty().bind(a.getYProperty());
		midPane.getChildren().add(r);
	}

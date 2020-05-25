package applicationV1.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import applicationV1.modele.*;
import applicationV1.modele.EnnemiType.*;

public class Controleur implements Initializable{

	private Environnement env;
	private Timeline gameloop;

	@FXML
	private Pane spritePane;
	@FXML
	private ToggleGroup EnnemiToggle;
	@FXML
	private TilePane tileMap;
	@FXML
	void ajouterActeur(ActionEvent event) {
		RadioButton bouttonChoisi = (RadioButton)EnnemiToggle.getSelectedToggle();
		String nomBouton = bouttonChoisi.getText();
		Ennemi ennemi = null;
		if(nomBouton.equals("Coyote"))
			this.env.ajouterEnnemi(ennemi = new EnnemiCoyote(env));
		if(nomBouton.equals("Hyena"))
			this.env.ajouterEnnemi(ennemi = new EnnemiHyena(env));
		if(nomBouton.equals("Bear"))
			this.env.ajouterEnnemi(ennemi = new EnnemiBear(env));
		if(nomBouton.equals("Lion"))
			this.env.ajouterEnnemi(ennemi = new EnnemiLion(env));
	} 
	void initTiles(){
		for(int i = 0; i < this.env.getTerrain().length; i++) {
			for(int j = 0; j < this.env.getTerrain()[0].length; j++) {
				this.tileMap.getChildren().add(obtenirImage(this.env.getTerrain()[i][j]));
			}
		}
	}    
	void creerSprite(Acteur a) {
		ImageView r = null;
		if(a instanceof Ennemi) {
			if(a instanceof EnnemiCoyote) 
				r = new ImageView("ressources/coyote.png");
			if(a instanceof EnnemiHyena)
				r = new ImageView("ressources/hyena.png");
			if(a instanceof EnnemiBear)
				r = new ImageView("ressources/bear.png");
			if(a instanceof EnnemiLion)
				r = new ImageView("ressources/lion.png");
		}
		else {
			r = new ImageView("ressources/tourelle_1.png");
		}
		r.setOnMouseClicked((e) -> System.out.println(a));
		r.translateXProperty().bind(a.getX64Property());
		r.translateYProperty().bind(a.getY64Property());
		this.spritePane.getChildren().add(r);
	}  
	public ImageView obtenirImage(int n) {
		ImageView tile;
		switch (n) {
		case 'v':
			tile = new ImageView("ressources/brick.png");
			return tile;
		case 'c':
			tile = new ImageView("ressources/wood.png");
			return tile;
		case 't':
			tile = new ImageView("ressources/tilePlacementTest.png");
			tile.setOnMouseClicked((e) -> {
				Tourelle t = new Tourelle((int)tile.getLayoutX()/64,(int)tile.getLayoutY()/64,1,100,this.env);
				System.out.println(tile.getLayoutX());
				this.env.ajouterTourelle(t);
			});
			return tile;
		default:
			return null;
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("initialisation");
		this.env = new Environnement();
		initTiles();
		initTour();
		gameloop.play();
		this.env.getEnnemis().addListener(new ObservateurEnnemis(this.spritePane));
		this.env.getTourelles().addListener(new ObservateurTourelles(this.spritePane));
	}
	public void initTour() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.05),(event->{
			this.env.unTour();
		}));
		gameloop.getKeyFrames().add(kf);
	}

}
package applicationV1.controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import applicationV1.modele.*;
import applicationV1.modele.EnnemiType.*;
import applicationV1.modele.TourelleType.TourelleBasique;
import applicationV1.modele.TourelleType.TourelleLanceGrenade;
import applicationV1.modele.TourelleType.TourelleMinigun;
import applicationV1.modele.TourelleType.TourelleShotgun;
import applicationV1.modele.TourelleType.TourelleSniper;

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
    private Label labelNbManche;
	@FXML
    private ToggleGroup TourelleToggle;

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
	@FXML
    void nvlManche(ActionEvent event){
		this.env.nouvelleManche();
		
    }
	void initTiles(){
		for(int i = 0; i < this.env.getTerrain().length; i++) {
			for(int j = 0; j < this.env.getTerrain()[0].length; j++) {
				this.tileMap.getChildren().add(obtenirImage(this.env.getTerrain()[i][j]));
			}
		}
	}   
	void initSelection(ToggleGroup t) {		
		((RadioButton)t.getToggles().get(0)).setGraphic(new ImageView("ressources/Tourelles/TourelleBasique.png"));
		((RadioButton)t.getToggles().get(1)).setGraphic(new ImageView("ressources/Tourelles/TourelleMinigun.png"));
		((RadioButton)t.getToggles().get(2)).setGraphic(new ImageView("ressources/Tourelles/TourelleShotgun.png"));
		((RadioButton)t.getToggles().get(3)).setGraphic(new ImageView("ressources/Tourelles/TourelleSniper.png"));
		((RadioButton)t.getToggles().get(4)).setGraphic(new ImageView("ressources/Tourelles/TourelleLanceGrenade.png"));
		
		for(Toggle to : t.getToggles()) {
			RadioButton rb = (RadioButton)to;
			rb.setTextFill(Color.TRANSPARENT);
//			rb.getStyleClass().remove("radio-button");
			
		}
		System.out.println(t.getSelectedToggle());
		if(t.getSelectedToggle() != null) {
			System.out.println(t.getSelectedToggle());
			((StackPane)((RadioButton)t.getSelectedToggle()).getParent()).getChildren().get(0).setVisible(true);
		}
	}
	void prout() {
		spritePane.setOnMouseDragged(e->{
			spritePane.setTranslateX(e.getX());
		});;
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
			tile = new EmpTourelle();
			tile.setOnMouseClicked((e) -> {
				Tourelle t = null;
				String s = ((RadioButton)TourelleToggle.getSelectedToggle()).getText();
				if(s.contentEquals("tBasique"))
					t = new TourelleBasique((int)tile.getLayoutX()/64,(int)tile.getLayoutY()/64,this.env);
				if(s.contentEquals("tMinigun"))
					t = new TourelleMinigun((int)tile.getLayoutX()/64,(int)tile.getLayoutY()/64,this.env);
				if(s.contentEquals("tShotgun"))
					t = new TourelleShotgun((int)tile.getLayoutX()/64,(int)tile.getLayoutY()/64,this.env);
				if(s.contentEquals("tSniper"))
					t = new TourelleSniper((int)tile.getLayoutX()/64,(int)tile.getLayoutY()/64,this.env);
				if(s.contentEquals("tLG"))
					t = new TourelleLanceGrenade((int)tile.getLayoutX()/64,(int)tile.getLayoutY()/64,this.env);
				this.env.ajouterTourelle(t);
				tile.setMouseTransparent(true);
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
		labelNbManche.textProperty().bind(this.env.getCptMancheGlobaleProperty().asString());
		initTiles();
		initTour();
		gameloop.play();
		this.env.getEnnemis().addListener(new ObservateurEnnemis(this.spritePane));
		this.env.getTourelles().addListener(new ObservateurTourelles(this.spritePane));
		this.env.getTirs().addListener(new ObservateurTirs(this.spritePane));
		initSelection(TourelleToggle);
	}
	public void initTour() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.002),(event->{
			this.env.unTour();
		}));
		gameloop.getKeyFrames().add(kf);
	}
}
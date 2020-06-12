package applicationV1.controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import applicationV1.modele.*;
import applicationV1.modele.EffetsTypes.*;
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
	private ToggleGroup EffetToggle;
	@FXML
	private ToggleGroup EnnemiToggle;
	@FXML
	private TilePane tileMap;	 
	@FXML
    private Label labelNbManche;
	@FXML
    private ToggleGroup TourelleToggle;
    @FXML
    private Label labelBourse;
    @FXML
    private Label lblText;

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
    void nvlManche(ActionEvent event) throws IOException{
		this.env.nouvelleManche();	
    }
	@FXML
	void killAllAction(ActionEvent event) {
		for(Ennemi e : this.env.getEnnemis()) e.mourrir();
	}
	void initTiles(){
		for(int i = 0; i < this.env.getTerrain().length; i++) {
			for(int j = 0; j < this.env.getTerrain()[0].length; j++) {
				this.tileMap.getChildren().add(obtenirImage(this.env.getTerrain()[i][j]));
			}
		}
	}   
	void initSelectionTourelle() {		
		((RadioButton)TourelleToggle.getToggles().get(0)).setGraphic(new ImageView("ressources/Tourelles/TourelleBasique.png"));
		((RadioButton)TourelleToggle.getToggles().get(1)).setGraphic(new ImageView("ressources/Tourelles/TourelleMinigun.png"));
		((RadioButton)TourelleToggle.getToggles().get(2)).setGraphic(new ImageView("ressources/Tourelles/TourelleShotgun.png"));
		((RadioButton)TourelleToggle.getToggles().get(3)).setGraphic(new ImageView("ressources/Tourelles/TourelleSniper.png"));
		((RadioButton)TourelleToggle.getToggles().get(4)).setGraphic(new ImageView("ressources/Tourelles/TourelleLanceGrenade.png"));
		
		for(Toggle to : TourelleToggle.getToggles()) {
			RadioButton rb = (RadioButton)to;
			rb.setTextFill(Color.TRANSPARENT);		
		}
	}
	public ImageView obtenirImage(int n) {
		ImageView tile;
		switch (n) {
		case 'V':
			tile = new ImageView("");
			return tile;
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
				String s1 = ((RadioButton)EffetToggle.getSelectedToggle()).getText();
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
				
				if(s1.contentEquals("eNul"))
					t.setEffet(null);
				if(s1.contentEquals("eChoc"))
					t.setEffet(new EffetChoc());
				if(s1.contentEquals("eBrulure"))
					t.setEffet(new EffetFeu());
				if(s1.contentEquals("eGaz"))
					t.setEffet(new EffetGaz());
				if(s1.contentEquals("eGlace"))
					t.setEffet(new EffetGlace());
				if(this.env.getBanque().achetable(t.getPrix())) {
					this.env.getBanque().acheter(t.getPrix());
					this.env.ajouterTourelle(t);
					afficher("Vous avez acheté :" + "\n" + t.getType());
				}
				else afficher("Vous n'avez pas assez d'Okanes " + "\n" + "pour acheter cette " + t.getType());
				
			});
			return tile;
		default:
			return null;
		}
	}
	public void afficher(String s) {
		lblText.setText(s);
	}
	public void initBinds() {
		labelNbManche.textProperty().bind(this.env.getCptMancheGlobaleProperty().asString());
		labelBourse.textProperty().bind(this.env.getBanque().getBourseProperty().asString());
	}
	public void initSelectionEffet() {
		((RadioButton)EffetToggle.getToggles().get(0)).setGraphic(new ImageView("ressources/Effets/EffetVide.png"));
		((RadioButton)EffetToggle.getToggles().get(1)).setGraphic(new ImageView("ressources/Effets/EffetChoc.png"));
		((RadioButton)EffetToggle.getToggles().get(2)).setGraphic(new ImageView("ressources/Effets/EffetFeu.png"));
		((RadioButton)EffetToggle.getToggles().get(3)).setGraphic(new ImageView("ressources/Effets/EffetGlace.png"));
		((RadioButton)EffetToggle.getToggles().get(4)).setGraphic(new ImageView("ressources/Effets/EffetGaz.png"));
		
		for(Toggle to : EffetToggle.getToggles()) {
			RadioButton rb = (RadioButton)to;
			rb.setTextFill(Color.TRANSPARENT);	
		}	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("initialisation");
		this.env = new Environnement();
		initBinds();
		initTiles();
		initTour();
		gameloop.play();
		this.env.getEnnemis().addListener(new ObservateurEnnemis(this.spritePane));
		this.env.getTourelles().addListener(new ObservateurTourelles(this.spritePane));
		this.env.getTirs().addListener(new ObservateurTirs(this.spritePane));
		initSelectionTourelle();
		initSelectionEffet();
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
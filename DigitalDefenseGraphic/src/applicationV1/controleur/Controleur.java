package applicationV1.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import applicationV1.modele.*;

public class Controleur implements Initializable{
	
	private Environnement env;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TilePane tileMap;
    
    @FXML
    private Pane spritePane;
    
    @FXML
    private CheckBox checkBox_nbEnnemiAAjouter;

    @FXML
    private CheckBox checkBox_nbTourelleAAjouter;

    @FXML
    private TextField txt_nbActeurAAjouter;
      
    @FXML
    void ajouterActeur(ActionEvent event) {
    	if(checkBox_nbEnnemiAAjouter.isSelected()) {
    		for(int i=0;i<Integer.parseInt(txt_nbActeurAAjouter.getText());i++) {
    			Ennemi e = new Ennemi(10, 10, this.env);
    			this.env.ajouterEnnemi(e);
    			creerSprite(e);
    			System.out.println(this.env.getCase(0, 1));
    			System.out.println(this.env.getCase(0, 0));
    			System.out.println(this.env.getCase(1, 1));
    		}
    	}   	
    	if(checkBox_nbTourelleAAjouter.isSelected()) {
    		for(int i=0;i<Integer.parseInt(txt_nbActeurAAjouter.getText());i++) {
    			Tourelle t = new Tourelle(1,100,this.env);
    			this.env.ajouterTourelle(t);
    			creerSprite(t);
    		}
    	}
    }
    @FXML
    void runModel(ActionEvent event) {
    	this.env.unTour();
    }    
    void initTiles(){
    	for(int i = 0; i < this.env.getTerrain().length; i++) {
    		for(int j = 0; j < this.env.getTerrain()[0].length; j++) {
    			this.tileMap.getChildren().add(obtenirImage(this.env.getTerrain()[i][j]));
    		}
    	}
    }    
    void creerSprite(Acteur a) {
        ImageView r;
        if(a instanceof Ennemi) {
            r = new ImageView("ressources/coyote.png");
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
				creerSprite(t);
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
	}

}
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
    	for(int i = 0; i < env.getTerrain().length; i++) {
    		for(int j = 0; j < env.getTerrain()[0].length; j++) {
    			this.tileMap.getChildren().add(obtenirImage(env.getTerrain()[i][j]));
    		}
    	}
    }    
    void creerSprite(Acteur a) {
        Shape r;
        if(a instanceof Ennemi) {
            r = new Rectangle(10, 10);
            r.setFill(Color.RED);
        }
        else {
            r = new Circle(6);
            r.setFill(Color.BLUE);
        }
        r.setOnMouseClicked((e) -> System.out.println(a));
        r.translateXProperty().bind(a.getX64Property());
        r.translateYProperty().bind(a.getY64Property());
        this.spritePane.getChildren().add(r);
    }  
	public ImageView obtenirImage(int n) {
		switch (n) {
		case 'v':
			ImageView tuile0 = new ImageView("ressources/brick.png");
			return tuile0;
		case 'c':
			ImageView tuile1 = new ImageView("ressources/wood.png");
			return tuile1;
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
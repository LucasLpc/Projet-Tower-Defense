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
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    private CheckBox checkBox_nbEnnemiAAjouter;

    @FXML
    private CheckBox checkBox_nbTourelleAAjouter;

    @FXML
    private TextField txt_nbActeurAAjouter;
    
    @FXML
    void ajouterActeur(ActionEvent event) {
    	if(checkBox_nbEnnemiAAjouter.isSelected()) {
			Ennemi e = new Ennemi(10, 10, this.env);
    		this.env.ajouterEnnemi(e);
    		System.out.println("ajout ennemi");
    	}
    	
    	if(checkBox_nbTourelleAAjouter.isSelected()) {
			Tourelle t = new Tourelle(10,10,this.env);
    		this.env.ajouterTourelle(t);
    		System.out.println("ajout tourelle");
    	}
    }

    @FXML
    void runModel(ActionEvent event) {
    	this.env.unTour();
    }
    
    void créerSprite(){
    	for(int i = 0; i < env.getTerrain().length; i++) {
    		for(int j = 0; j < env.getTerrain()[0].length; j++) {
    			this.tileMap.getChildren().add(imageDe(env.getTerrain()[i][j]));
    		}
    	}
    }
    
   public ImageView imageDe(int n){
        switch (n){
            case 0 :
                ImageView tuile0 = new ImageView("ressources/brick.png");
                return tuile0;
            case 1 :
                ImageView tuile1 = new ImageView("ressources/wood.png");
                return tuile1;
            case 2 :
            	return null;
            case 3 :
            	return null;
            default :
            	return null;
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("initialisation");
		this.env = new Environnement(10,10);
		this.env.initTerrain();
		créerSprite();
	}

}
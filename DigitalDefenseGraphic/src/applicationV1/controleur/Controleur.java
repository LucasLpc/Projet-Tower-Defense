package applicationV1.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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
    
    private  void  creerSprite (int terrain[][]){
    	Rectangle  r ;
    	for(int i = 0; i < terrain.length; i++) {
    		for(int j = 0; j < terrain[0].length; j++) {
    			if (terrain[i][j] == 0){
    	    		r = new Rectangle(50, 50);
    	    		r . setFill (Color.BLACK);
    	    		r . setTranslateX(i);
                	r . setTranslateY(j);
                	tileMap.getChildren().add(r);
    	    	}
    			else if (terrain[i][j] == 1) {
    				r = new Rectangle(50, 50);
    	    		r . setFill (Color.WHITE);
    	    		r . setTranslateX(i);
                	r . setTranslateY(j);
                	tileMap.getChildren().add(r);
    			}
    			else if (terrain[i][j] == 2) {
    				r = new Rectangle(50, 50);
    	    		r . setFill (Color.RED);
    	    		r . setTranslateX(i);
                	r . setTranslateY(j);
                	tileMap.getChildren().add(r);
    			}
    			else if (terrain[i][j] == 3) {
    				r = new Rectangle(50, 50);
    	    		r . setFill (Color.GREEN);
    	    		r . setTranslateX(i);
                	r . setTranslateY(j);
                	tileMap.getChildren().add(r);
    			}
    		}
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("initialisation");
		this.env = new Environnement(10,10);
		this.env.initTerrain();
		creerSprite(this.env.getTerrain());
	}

}
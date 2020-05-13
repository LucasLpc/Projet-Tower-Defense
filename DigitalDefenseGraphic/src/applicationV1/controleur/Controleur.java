package applicationV1.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    private Label label_nbEnnemis;
    @FXML
    private URL location;
    @FXML
    private Label label_nbTourelles;
    @FXML
    private Pane pane_map;
    @FXML
    private TilePane tile_map;
    @FXML
    private CheckBox checkBox_nbEnnemiAAjouter;
    @FXML
    private CheckBox checkBox_nbTourelleAAjouter;
    @FXML
    private TextField txt_nbActeurAAjouter; 
    @FXML
    void ajouterActeur(ActionEvent event) {
    	if(checkBox_nbEnnemiAAjouter.isSelected()) {
    		for(int i=0;i<Integer.parseInt(txt_nbActeurAAjouter.getText());i++){
    			Ennemi e = new Ennemi(10, 10, this.env);
        		this.env.ajouterEnnemi(e);
        		creerSprite(e);
        		System.out.println("ajout ennemi");
    		}

    	}
    	
    	if(checkBox_nbTourelleAAjouter.isSelected()) {
    		for(int i=0;i<Integer.parseInt(txt_nbActeurAAjouter.getText());i++){
    			Tourelle t = new Tourelle(10,10,this.env);
    			this.env.ajouterTourelle(t);
    			creerSprite(t);
    			System.out.println("ajout tourelle");
    		}
    	}
    }
    public ImageView imageDe(char c){
        switch (c){
            case 'v' :
                ImageView tilebrick = new ImageView("ressources/brick.png");
                return tilebrick;
            case 'c' :
                ImageView tilevide = new ImageView("ressources/vide.png");
                return tilevide;
            default : return null;
        }
    }
    @FXML
    void runModel(ActionEvent event) {
    	this.env.unTour();
    }
    public void creerSprite(Acteur a) {
		Shape r;
		if(a instanceof Ennemi) {
			r = new Rectangle();
			r.setFill(Color.RED);
		}
		else {
			r = new Circle(3);
			r.setFill(Color.BLUE);
		}
		r.setOnMouseClicked((e) -> System.out.println(a));
		r.translateXProperty().bind(a.getXProperty());
		r.translateYProperty().bind(a.getYProperty());
		pane_map.getChildren().add(r);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("initialisation");
		this.env = new Environnement(50,30);
		this.env.initTerrain();
		
	}

}
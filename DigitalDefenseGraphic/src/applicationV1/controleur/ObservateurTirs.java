package applicationV1.controleur;

import java.awt.Color;

import applicationV1.modele.Acteur;
import applicationV1.modele.Tir;
import applicationV1.modele.TirType.TirBasique;
import applicationV1.modele.TirType.TirLanceGrenade;
import applicationV1.modele.TirType.TirShotGun;
import applicationV1.modele.TirType.TirSniper;
import javafx.animation.Animation;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ObservateurTirs implements ListChangeListener<Tir>{
	
	@FXML
	private Pane spritePane;
	
	public ObservateurTirs(Pane spritePane){
		super();
		this.spritePane = spritePane;
	}
	
	public void creerSprite(Tir t) {
		ImageView r = null;
		if(t instanceof TirSniper) {
			r = new ImageView("ressources/Tirs/tir.png");
			r.translateXProperty().bind(t.getX64Property());
			r.translateYProperty().bind(t.getY64Property());
			r.setId(t.getId());
		}
		if(t instanceof TirLanceGrenade) {
			r = new ImageView("ressources/Tirs/tirLanceGrenade.png");
			r.translateXProperty().bind(t.getX64Property());
			r.translateYProperty().bind(t.getY64Property());
			r.setId(t.getId());
		}
		if(t instanceof TirBasique) {
			r = new ImageView("ressources/Tirs/tir.png");
			r.translateXProperty().bind(t.getX64Property());
			r.translateYProperty().bind(t.getY64Property());
			r.setId(t.getId());
		}
		if(t instanceof TirShotGun) {
			r = new ImageView("ressources/Tirs/tirShotGun.png");
			r.translateXProperty().bind(t.getX64Property());
			r.translateYProperty().bind(t.getY64Property());
			r.rotateProperty().bind(t.getAngleProperty());
			r.setId(t.getId());
		}
		this.spritePane.getChildren().add(r);
	}
	
	public void enleverSprite(Tir mort) {
		this.spritePane.getChildren().remove(this.spritePane.lookup("#" + mort.getId()));
	}
	
	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Tir> c) {
		while(c.next()){
			// gestion des nouveaux n Ì�es
			// on cree leur sprite .
			for(Tir nouveau: c.getAddedSubList()){
				creerSprite(nouveau);
			}
			// gestion des morts
			// on enleve leur sprite .
			for(Tir mort: c.getRemoved()){
				enleverSprite(mort);
			}
		}
		
	}
}
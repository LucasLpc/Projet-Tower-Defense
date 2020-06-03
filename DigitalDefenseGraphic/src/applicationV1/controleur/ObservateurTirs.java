package applicationV1.controleur;

import java.awt.Color;

import applicationV1.modele.Acteur;
import applicationV1.modele.Tir;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
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
		Circle c = new Circle(4);
		c.translateXProperty().bind(t.getX64Property());
		c.translateYProperty().bind(t.getY64Property());
		c.setId(t.getId());
		this.spritePane.getChildren().add(c);
	}
	
	public void enleverSprite(Tir mort) {
		System.out.println(mort.getId());
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
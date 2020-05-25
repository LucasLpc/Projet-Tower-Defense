package applicationV1.controleur;

import applicationV1.modele.Acteur;
import applicationV1.modele.Tourelle;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObservateurTourelles implements ListChangeListener<Tourelle>{
	
	@FXML
	private Pane spritePane;
	
	public ObservateurTourelles(Pane spritePane){
		super();
		this.spritePane = spritePane;
	}
	
	public void creerSprite(Tourelle nouveau) {
		ImageView r = new ImageView("ressources/tourelle_1.png");
		r.setOnMouseClicked((e) -> System.out.println(nouveau));
		r.translateXProperty().bind(nouveau.getX64Property());
		r.translateYProperty().bind(nouveau.getY64Property());
		r.setId(nouveau.getId());
		this.spritePane.getChildren().add(r);
	}
	
	public void enleverSprite(Tourelle mort) {
		System.out.println(mort.getId());
		this.spritePane.getChildren().remove(this.spritePane.lookup("#" + mort.getId()));
	}
	
	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Tourelle> c) {
		while(c.next()){
			// gestion des nouveaux n ́es
			// on cree leur sprite .
			for(Tourelle nouveau: c.getAddedSubList()){
				creerSprite(nouveau);
			}
			// gestion des morts
			// on enleve leur sprite .
			for(Tourelle mort: c.getRemoved()){
				enleverSprite(mort);
			}
		}
		
	}
}
package applicationV1.controleur;

import applicationV1.modele.Ennemi;
import applicationV1.modele.EnnemiType.EnnemiBear;
import applicationV1.modele.EnnemiType.EnnemiCoyote;
import applicationV1.modele.EnnemiType.EnnemiHyena;
import applicationV1.modele.EnnemiType.EnnemiLion;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObservateurEnnemis implements ListChangeListener<Ennemi>{
	
	@FXML
	private Pane spritePane;
	
	public ObservateurEnnemis(Pane spritePane){
		super();
		this.spritePane = spritePane;
	}
	
	public void creerSprite(Ennemi e) {
		ImageView r = null;
		if(e instanceof EnnemiCoyote) 
			r = new ImageView("ressources/Ennemis/coyote.png");
		if(e instanceof EnnemiHyena)
			r = new ImageView("ressources/Ennemis/hyena.png");
		if(e instanceof EnnemiBear)
			r = new ImageView("ressources/Ennemis/bear.png");
		if(e instanceof EnnemiLion)
			r = new ImageView("ressources/Ennemis/lion.png");
		r.setOnMouseClicked((env) -> System.out.println(e));
		r.translateXProperty().bind(e.getX64Property());
		r.translateYProperty().bind(e.getY64Property());
		r.setId(e.getId());
		this.spritePane.getChildren().add(r);
	}
	
	public void enleverSprite(Ennemi mort) {
		System.out.println(mort.getId());
		this.spritePane.getChildren().remove(this.spritePane.lookup("#" + mort.getId()));
	}
	
	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Ennemi> c) {
		while(c.next()){
			// gestion des nouveaux n Ì�es
			// on cree leur sprite .
			for(Ennemi nouveau: c.getAddedSubList()){
				creerSprite(nouveau);
			}
			// gestion des morts
			// on enleve leur sprite .
			for(Ennemi mort: c.getRemoved()){
				enleverSprite(mort);
			}
		}
		
	}
}
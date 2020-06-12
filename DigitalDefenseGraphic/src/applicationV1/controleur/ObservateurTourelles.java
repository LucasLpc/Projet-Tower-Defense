package applicationV1.controleur;

import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;
import applicationV1.modele.EnnemiType.EnnemiBear;
import applicationV1.modele.EnnemiType.EnnemiCoyote;
import applicationV1.modele.EnnemiType.EnnemiHyena;
import applicationV1.modele.EnnemiType.EnnemiLion;
import applicationV1.modele.TourelleType.*;
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
	
	public void creerSprite(Tourelle t) {
		ImageView r = null;
		if(t instanceof TourelleBasique) 
			r = new ImageView("ressources/Tourelles/tourelleBasique.png");
		if(t instanceof TourelleMinigun)
			r = new ImageView("ressources/Tourelles/tourelleMinigun.png");
		if(t instanceof TourelleShotgun)
			r = new ImageView("ressources/Tourelles/tourelleShotgun.png");
		if(t instanceof TourelleSniper)
			r = new ImageView("ressources/Tourelles/tourelleSniper.png");
		if(t instanceof TourelleLanceGrenade)
			r = new ImageView("ressources/Tourelles/tourelleLanceGrenade.png");
		//à modifier
		r.translateXProperty().bind(t.getX64Property());
		r.translateYProperty().bind(t.getY64Property());
		r.setOnMouseClicked(e->{
			t.getEnv().getTourelles().remove(t);
		});
		r.rotateProperty().bind(t.getAngleProperty());
		System.out.println();
		r.setId(t.getId());
		this.spritePane.getChildren().add(r);
	}
	
	public void enleverSprite(Tourelle mort) {
		System.out.println(mort.getId());
		this.spritePane.getChildren().remove(this.spritePane.lookup("#" + mort.getId()));
	}
	
	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Tourelle> c) {
		while(c.next()){
			// gestion des nouveaux n Ì�es
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
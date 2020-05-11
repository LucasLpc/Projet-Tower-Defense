package applicationV1.modele;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {
	private IntegerProperty x;
	private IntegerProperty y;
	protected Environnement env;
	public static int compteur=0;
	private String id;
	
	public Acteur(int x, int y, Environnement env) {
		this.x.setValue(x);
		this.y.setValue(y);
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}

	public Acteur(Environnement env) {
		// Méthothe permetant de générer un acteur avec une position aléatoire
		Random random=new Random();
		this.x = new SimpleIntegerProperty();
		this.y = new SimpleIntegerProperty();
		this.x.setValue(random.nextInt(env.getLargeur()));
		this.y.setValue(random.nextInt(env.getHauteur()));
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}
	
	public void nouvellePosition() {
		// Methode permettant de reroll la position aléatoire dans le cas ou la précédante n'est pas voulue (case deja occupée ou inaccesible).
		Random random=new Random();
		this.x.setValue(random.nextInt(env.getLargeur()));
		this.y.setValue(random.nextInt(env.getHauteur()));
	}
	
	public final int getX() {
		return this.x.getValue();
	}
	
	public final int getY() {
		return this.y.getValue();
	}
	
	public final void setX(int x) {
		this.x.setValue(x);
	}
	
	public final void setY(int y) {
		this.y.setValue(y);
	}
	
	public final IntegerProperty xProperty() {
		return this.x;
	}
	
	public final IntegerProperty yProperty() {
		return this.y;
	}
	
	@Override
	public String toString() {
		return "Acteur [x=" + x + ", y=" + y + ", env=" + env + ", id=" + id + "]";
	}
}
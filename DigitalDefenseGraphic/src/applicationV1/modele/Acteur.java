package applicationV1.modele;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {
	private IntegerProperty x;
	private IntegerProperty y;
	private IntegerProperty x64;
	private IntegerProperty y64;
	protected Environnement env;
	public static int compteur=0;
	private String id;

	public Acteur(int x, int y, Environnement env) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.x64 = new SimpleIntegerProperty(x*64+32);
		this.y64 = new SimpleIntegerProperty(y*64+32);
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}

	public Acteur(Environnement env) {
		// Méthothe permetant de générer un acteur avec une position aléatoire
		Random random=new Random();
		int xRandom = random.nextInt(env.getLargeur());
		int yRandom = random.nextInt(env.getHauteur());
		this.x = new SimpleIntegerProperty(xRandom);
		this.y = new SimpleIntegerProperty(yRandom);
		this.x64 = new SimpleIntegerProperty(xRandom*64+32);
		this.y64 = new SimpleIntegerProperty(yRandom*64+32);
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
		this.x64.setValue(x*64+32);
	}
	
	public final void setY(int y) {
		this.y.setValue(y);
		this.y64.setValue(y*64+32);
	}
	
	public String getId() {
		return this.id;
	}
	
	public final IntegerProperty getXProperty() {
		return this.x;
	}
	
	public final IntegerProperty getYProperty() {
		return this.y;
	}
	
	public final IntegerProperty getX64Property() {
		return this.x64;
	}
	
	public final IntegerProperty getY64Property() {
		return this.y64;
	}
	
	@Override
	public String toString() {
		return "Acteur [x=" + x + ", y=" + y + ", env=" + env + ", id=" + id + "]";
	}
}
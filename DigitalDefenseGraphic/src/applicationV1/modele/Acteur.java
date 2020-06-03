package applicationV1.modele;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {
	private IntegerProperty x;
	private IntegerProperty y;
	private IntegerProperty x64;
	private IntegerProperty y64;
	protected Environnement env;
	public static int compteur=0;
	protected String id;

	public Acteur(int x, int y, int x64, int y64, Environnement env) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.x64 = new SimpleIntegerProperty(x64);
		this.y64 = new SimpleIntegerProperty(y64);
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}

	public Acteur(Environnement env) {
		// Methothe permetant de generer un acteur avec une position aleatoire
		Random random=new Random();
		int xRandom = random.nextInt(env.getLargeur());
		int yRandom = random.nextInt(env.getHauteur());
		this.x = new SimpleIntegerProperty(xRandom);
		this.y = new SimpleIntegerProperty(yRandom);
		this.x64 = new SimpleIntegerProperty(xRandom*64);
		this.y64 = new SimpleIntegerProperty(yRandom*64);
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}

	public void nouvellePosition() {
		// Methode permettant de reroll la position aleatoire dans le cas ou la precedante n'est pas voulue (case deja occupee ou inaccesible).
		Random random=new Random();
		this.x.setValue(random.nextInt(env.getLargeur()));
		this.y.setValue(random.nextInt(env.getHauteur()));
		this.x64.setValue(64*this.getX());
		this.y64.setValue(64*this.getY());
	}
	
	public abstract void agir();
	
	public String getId() {
		return this.id;
	}
	
	public final int getX() {
		return this.x.getValue();
	}
	
	public final int getY() {
		return this.y.getValue();
	}
	
	public final int getX64() {
		return this.x64.getValue();
	}
	
	public final int getY64() {
		return this.y64.getValue();
	}
	
	public final void setX(int x) {
		this.x.setValue(x);
		this.x64.setValue(x*64);
	}
	
	public final void setY(int y) {
		this.y.setValue(y);
		this.y64.setValue(y*64);
	}
	
	public final void setX64(int x64) {
		this.x64.setValue(x64);
		if(x64%64 > 0) {
			this.setX(x64/64 +1);
		}
		else {
			this.setX(x64/64);
		}
	}
	
	public final void setY64(int y64) {
		this.y64.setValue(y64);
		if(y64%64 > 0) {
			this.setY(y64/64 +1);
		}
		else {
			this.setY(y64/64);
		}
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
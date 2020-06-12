package applicationV1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tourelle extends Acteur{
	private int degats;
	private int portee;
	private DoubleProperty angle;
	private int prix;
	private Effet effet;

	public Tourelle(int x, int y, int deg, int port, Environnement env, int prix) {
		// Creation d'une tourelle sur une position donnee.
		super(x, y, x*64, y*64, env);
		this.degats = deg;
		this.portee = port;
		this.angle = new SimpleDoubleProperty(0);
		this.prix = prix;
		this.effet = null;
	}
	
	public abstract String getType();

	public void agir() {
		int j = 0;
		if(this.env.getNbEnnemis() > 0) {
			do {
				if (this.aPortee(this.env.getEnnemis().get(j))) {
					this.regarder(this.env.getEnnemis().get(j));
					this.attaquer(this.env.getEnnemis().get(j));
					j = this.env.getNbEnnemis();
				}
				j++;
			} while (j < this.env.getNbEnnemis());
		}
	}
	public int getPrix() {
		return this.prix;
	}
	public double getAngle() {
		return this.angle.getValue();
	}
	
	public void setAngle(double angle) {
		this.angle.setValue(angle);
	}
	
	public DoubleProperty getAngleProperty() {
		return angle;
	}
	
	public boolean aPortee(Ennemi e) {
		// La methode sert à savoir si une tourelle a un ennemi donne à portee
		if(e.getX() < getX() + this.portee && e.getY() < getY() + this.portee && e.getX() > getX() - this.portee && e.getY() > getY() - portee) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void attaquer(Ennemi e) {
		if(this.env.getNbTours() % 100 == 0) {
			Tir t = new Tir(this.getX(), this.getY(), this.env, this.degats, e, this.effet);
			this.env.ajouterTir(t);
		}
	}
	
	public void regarder(Ennemi e) {
		double xDist = e.getX64() - this.getX64();
		double yDist = e.getY64() - this.getY64();
		this.setAngle((int)Math.toDegrees(-Math.atan2(xDist, yDist)));
	}
	
	public Effet getEffet() {
		return effet;
	}

	public void setEffet(Effet effet) {
		this.effet = effet;
	}

	@Override
	public String toString() {
		return "Tourelle [degats=" + degats + ", portee=" + portee + ", Angle : " + angle +  " " + super.toString() + "]";
	}

}
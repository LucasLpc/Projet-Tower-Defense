package applicationV1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tourelle extends Acteur{
	private int degats;
	private int portee;
	private DoubleProperty angle;

	public Tourelle(int x, int y, int deg, int port, Environnement env) {
		// Creation d'une tourelle sur une position donnee.
		super(x, y, x*64, y*64, env);
		this.degats = deg;
		this.portee = port;
		this.angle = new SimpleDoubleProperty(0);
	}
	
	public void agir() {
		int j = 0;
		do {
			if(this.env.getNbEnnemis() > 0) {
				if (this.aPortee(this.env.getEnnemis().get(j))) {
					this.regarder(this.env.getEnnemis().get(j));
					this.attaquer(this.env.getEnnemis().get(j));
					j = this.env.getNbEnnemis();
				}
			}
			j++;
		}while(j < this.env.getNbEnnemis());
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
		e.perdreHp(this.degats);
		Tir t = new Tir(this.getX64()+32, this.getY64()+32, this.env, this.degats, e);
		this.env.ajouterTir(t);
		System.out.println();
		System.out.println(e + " a perdu " + this.degats + " HP");
	}
	public void regarder(Ennemi e) {
		double xDist = e.getX64() - this.getX64();
		double yDist = e.getY64() - this.getY64();
		this.setAngle((int)Math.toDegrees(-Math.atan2(xDist, yDist)));
	};
	@Override
	public String toString() {
		return "Tourelle [degats=" + degats + ", portee=" + portee + ", Angle : " + angle +  " " + super.toString() + "]";
	}

}
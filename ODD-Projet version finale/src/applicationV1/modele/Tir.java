package applicationV1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;

public abstract class Tir extends Acteur{
	
	private int dmg;
	private int cote;
	private Ennemi cible;
	private Effet effet;
	
	public Tir(int cote, int x, int y, Environnement env, int dmg, Ennemi cible, Effet effet) {
		super(x, y, x*64+32-(cote/2), y*64+32-(cote/2), env);
		this.cote = cote;
		this.dmg=dmg;
		this.cible=cible;
		this.effet = effet;
	}
	
	@Override
	public abstract void agir();
	
	public void seDeplacer() {
		double deltaY64 = this.cible.getY64()+32-(cote/2) - this.getY64();
		double deltaX64 = this.cible.getX64()+32-(cote/2) - this.getX64();
		
		// Je veux que le projectile se deplace d'une distance de 1 par deplacement, il s'agit donc de l'intersection de la droite de trajectoire 
		// et d'un cercle de rayon 1 autour de la position actuelle du projectile
		// -------------------------------------------------------------
		// y = ax + b : equation de droite
		// (x - xo)� + (y - yo)� = 1 : equation de cercle
		// -----------------------------
		// (x - xo)� + (ax + (b - yo))� = 1
		// x� -2*xo*x + xo� + (ax)� - 2*(b - yo)*(ax) + (b - yo)� = 1
		// x� -2*xo*x + xo� + a�*x� - 2*(b - yo)*(ax) + (b - yo)� = 1
		// x� -2*xo*x + xo� + a�*x� - 2*(b - yo)*(ax) + (b - yo)� -1 = 0
		// x� (1 + a�) + x ((-2*xo*x) + (-2*(b-yo)*a)) + 1 (xo� + (b-yo)�) = 0
		// ---------------------------------------------------------------------
		// On a donc une equation du type ax�+bx+c = 0
		// discriminant = b� - 4ac
		// discriminant = ((-2*xo*x) + (-2*(b-yo)*a))� - 4 * (1 + a�) * (xo� + (b-yo)�)
		// ---------------------------------------------------------------------
		// racine1 = (-b + Racine(discriminant)) / 2a)
		//		   = (-((-2*xo*x) + (-2*(b-yo)*a)) + Racine(discriminant)) / 2 * (1 + a�)) 
		// racine2 = (-b - Racine(discriminant)) / 2a)
		//		   = (-((-2*xo*x) + (-2*(b-yo)*a)) - Racine(discriminant)) / 2 * (1 + a�))

		double xo = this.getX64();
		double yo = this.getY64();
		double a = deltaY64 / deltaX64;
		double b = yo - a*xo;
		double discriminant = Math.pow(((-2 * xo) + (2 * a * (b - yo))), 2) - 4 * (1 + Math.pow(a, 2)) * (Math.pow(xo, 2) + Math.pow(b-yo, 2) - 1);
		double racine1 = (-((-2 * xo) + (2 * a * (b - yo))) + Math.sqrt(discriminant)) / (2 * (1 + Math.pow(a, 2)));
		double racine2 = (-((-2 * xo) + (2 * a * (b - yo))) - Math.sqrt(discriminant)) / (2 * (1 + Math.pow(a, 2)));
		if(deltaX64 > 0) {
			this.setX64(racine1);
			this.setY64(this.getY64() + (racine1 - xo) * a);
		}
		else if (deltaX64 < 0) {
			this.setX64(racine2);
			this.setY64(this.getY64() + (racine2 - xo) * a);
		}
		else {
			if (deltaY64 > 0) {
				this.setY64(this.getY64() + 1);
			}
			else if (deltaY64 < 0) {
				this.setY64(this.getY64() - 1);
			}
		}
	}
	
	public boolean cibleAtteinte() {
		if(Math.sqrt(Math.pow(this.cible.getX64()+32-(cote/2) - this.getX64(), 2) + Math.pow(this.cible.getY64()+32-(cote/2) - this.getY64(), 2)) <= 1) {
			return true;
		}
		return false;
	}
	
	public void setCible(Ennemi e) {
		this.cible = e;
	}
	
	public Ennemi getCible() {
		return this.cible;
	}
	
	public int getDmg() {
		return this.dmg;
	}
	
	public int getCote() {
		return this.cote;
	}
	
	public Effet getEffet() {
		return effet;
	}

	public abstract ObservableValue<? extends Number> getAngleProperty();
}

package applicationV1.modele.TirType;

import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tir;
import applicationV1.modele.Tourelle;
import javafx.beans.property.DoubleProperty;

public class TirShotGun extends Tir{
	
	private DoubleProperty angle;

	public TirShotGun(int x, int y, Environnement env, int dmg, Ennemi cible, Effet e, Tourelle t) {
		super(264, x, y, env, dmg, cible, e);
		angle = t.getAngleProperty();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agir() {
		if(this.getEnv().getNbTours() % 200 == 0) {
		for(int i = 0; i < ennemisDansZone().size(); i++) {
			ennemisDansZone().get(i).perdreHp(getDmg());
			System.out.println(ennemisDansZone().get(i) + " perds " + getDmg() + " pv.");
		}
			getEnv().delTir(getId());
		}
	}
	
	public ArrayList<Ennemi> ennemisDansZone(){
		ArrayList<Ennemi> ennemisDansZone = new ArrayList<Ennemi>();
		for(int i = 0; i < this.getEnv().getNbEnnemis(); i++) {
			if(aPortee(this.getEnv().getEnnemis().get(i).getX64(), this.getEnv().getEnnemis().get(i).getY64(), 128)){
				if(dansZone(this.getEnv().getEnnemis().get(i))) {
					ennemisDansZone.add(this.getEnv().getEnnemis().get(i));
				}
			}
		}
		return ennemisDansZone;
	}
	
	public boolean dansZone(Ennemi e) {
		// La zone est un cone. Le probl�me peut se r�sumer � comparer l'angle que forme le droite passant par l'ennemi et l'origine avec l'angle passant par la 
		// cible et l'origine.
		// A(e.getX64(), e.getY64()), B(e.getX64, this.getY64()), C(this.getCible().getX64(), this.getCible().getY64()), 
		// D(this.getCible().getX64(), this.getY64()), Origine(this.getX64(), this.getY64())
		// angle = arcos(Adjacent / Hypotenuse)
		// -----------------------------
		// AdjacentEnnemi = distance(A, this)
		// HypotenuseEnnemi = distance(B, this)
		// -----------------------------
		// AdjacentCible = distance(A, this)
		// HypotenuseCible = distance(B, this)
		
		double ax = e.getX64();
		double ay = e.getY64();
		double bx = e.getX64();
		double by = this.getY64()-(getCote()/2);
		double cx = this.getCible().getX64();
		double cy = this.getCible().getY64();
		double dx = this.getCible().getX64();
		double dy = this.getY64()-(getCote()/2);
		double ox = this.getX64()-(getCote()/2);
		double oy = this.getY64()-(getCote()/2);
		
		double oa = getDistance(ox, oy, ax, ay);
		double ob = getDistance(ox, oy, bx, by);
		double oc = getDistance(ox, oy, cx, cy);
		double od = getDistance(ox, oy, dx, dy);
		
		double angleBOA = Math.acos(ob / oa);
		double angleDOC = Math.acos(od / oc);
		
		if(angleBOA - angleDOC <= 20) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getDistance(double ax, double ay, double bx, double by) {
		return Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
	}
	
	public boolean aPortee(double bx, double by, int distance) {
		if(Math.sqrt(Math.pow(bx - this.getX64()-(getCote()/2), 2) + Math.pow(by - this.getY64()-(getCote()/2), 2)) <= distance) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public DoubleProperty getAngleProperty(){
		return this.angle;
	}

}

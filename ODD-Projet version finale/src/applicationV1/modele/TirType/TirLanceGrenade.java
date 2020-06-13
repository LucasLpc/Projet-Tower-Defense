package applicationV1.modele.TirType;

import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tir;
import applicationV1.modele.Tourelle;
import javafx.beans.property.DoubleProperty;

public class TirLanceGrenade extends Tir{
	
	private int zoneExplosion;
	private DoubleProperty angle;

	public TirLanceGrenade(int x, int y, Environnement env, int dmg, Ennemi cible, Effet e, Tourelle t) {
		super(128, x, y, env, dmg, cible, e);
		this.zoneExplosion = 64;
		this.angle = t.getAngleProperty();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agir() {
		seDeplacer();
		if(cibleAtteinte()) {
			getEnv().delTir(getId());
			for(int i = 0; i < getEnnemisAPortee(getX64(), getY64()).size(); i ++) {
				getEnnemisAPortee(getX64(), getY64()).get(i).perdreHp(getDmg());
				getEnnemisAPortee(getX64(), getY64()).get(i).ajouterEffet(getEffet());
				System.out.println(getEnnemisAPortee(getX64(), getY64()).get(i));
			}
		}
	}
	
	public ArrayList<Ennemi> getEnnemisAPortee(double ax, double ay){
		ArrayList<Ennemi> ennemisAPortee = new ArrayList<Ennemi>();
		if(getEnv().getNbEnnemis() > 0) {
			for(int i = 0; i < getEnv().getNbEnnemis(); i++) {
				if(aPortee(ax, ay, getEnv().getEnnemis().get(i).getX64(), getEnv().getEnnemis().get(i).getY64(), this.zoneExplosion)) {
					ennemisAPortee.add(getEnv().getEnnemis().get(i));
				}
			}
		}
		return ennemisAPortee;
	}
	
	public boolean aPortee(double ax, double ay, double bx, double by, int distance) {
		if(Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2)) <= distance) {
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

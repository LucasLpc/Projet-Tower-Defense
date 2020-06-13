package applicationV1.modele.TirType;

import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tir;
import applicationV1.modele.Tourelle;
import javafx.beans.property.DoubleProperty;

public class TirSniper extends Tir{
	
	private int decompte;
	private DoubleProperty angle;
	
	public TirSniper(int x, int y, Environnement env, int dmg, Ennemi cible, Effet e, Tourelle t) {
		super(32, x, y, env, dmg, cible, e);
		this.decompte = 1;
		angle = t.getAngleProperty();
	}

	@Override
	public void agir() {
		seDeplacer();
		if(cibleAtteinte()) {
			if(decompte > 0) {
				getCible().perdreHp(getDmg());
				getCible().ajouterEffet(getEffet());
				if(nextEnnemi() == null) {
					getEnv().delTir(getId());
					System.out.println("nextEnnemi null");
				}
				else {
					setCible(nextEnnemi());
					setDecompte(getDecompte()-1);
				}
			}
			else {
				getCible().perdreHp(getDmg());
				getCible().ajouterEffet(getEffet());
				getEnv().delTir(getId());
			}
		}
	}
	
	
	public int getDecompte() {
		return decompte;
	}

	public void setDecompte(int decompte) {
		this.decompte = decompte;
	}

	public Ennemi nextEnnemi() {
		double distance = getEnv().getHauteur() * getEnv().getLargeur() * 64;
		Ennemi ennemi = null;
		if(getEnv().getNbEnnemis() > 0) {
			for(int i = 0; i < getEnv().getNbEnnemis(); i++) {
					if(distance >= getDistance(getX64(), getY64(), getEnv().getEnnemis().get(i).getX64(), getEnv().getEnnemis().get(i).getY64()) && getEnv().getEnnemis().get(i) != getCible()) {
						distance = getDistance(getX64(), getY64(), getEnv().getEnnemis().get(i).getX64(), getEnv().getEnnemis().get(i).getY64());
						ennemi = getEnv().getEnnemis().get(i);
				}
			}		
		}
		return ennemi;
	}
	
	public double getDistance(double ax, double ay, double bx, double by) {
		return Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
	}
	
	public DoubleProperty getAngleProperty(){
		return this.angle;
	}
}


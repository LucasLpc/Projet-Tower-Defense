package applicationV1.modele.TirType;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tir;
import applicationV1.modele.Tourelle;
import javafx.beans.property.DoubleProperty;

public class TirBasique extends Tir{
	
	private DoubleProperty angle;

	public TirBasique(int x, int y, Environnement env, int dmg, Ennemi cible, Effet e, Tourelle t) {
		super(32, x, y, env, dmg, cible, e);
		this.angle = t.getAngleProperty();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agir() {
		seDeplacer();
		if(cibleAtteinte()) {
			getCible().perdreHp(getDmg());
			getCible().ajouterEffet(getEffet());
			getEnv().delTir(getId());
		}
	}
	
	public DoubleProperty getAngleProperty(){
		return this.angle;
	}

}

package applicationV1.modele.TourelleType;

import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;
import applicationV1.modele.TirType.TirSniper;

public class TourelleSniper extends Tourelle{

	public TourelleSniper(int x, int y, Environnement env) {
		super(x, y, 75, 8, 500, env, 60);
	}
	
	@Override
	public void attaquer(Ennemi e) {
		if(this.env.getNbTours() % getCadence() == 0) {
			TirSniper t = new TirSniper(this.getX(), this.getY(), this.env, this.degats, e, getEffet(), this);
			this.env.ajouterTir(t);
		}
	}
	
	@Override
	public String getType() {
		return "Tourelle Sniper";
	}
}

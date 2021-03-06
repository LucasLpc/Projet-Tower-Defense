package applicationV1.modele.TourelleType;

import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;
import applicationV1.modele.TirType.TirShotGun;

public class TourelleShotgun extends Tourelle{

	public TourelleShotgun(int x, int y, Environnement env) {
		super(x, y, 100, 2, 230, env, 40);
	}
	
	@Override
	public void attaquer(Ennemi e) {
		if(this.env.getNbTours() % getCadence() == 0) {
			TirShotGun t = new TirShotGun(this.getX(), this.getY(), this.env, this.degats, e, getEffet(), this);
			this.env.ajouterTir(t);
		}
	}
	
	@Override
	public String getType() {
		return "Tourelle Shotgun";
	}
}

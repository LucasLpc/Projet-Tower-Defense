package applicationV1.modele.TourelleType;

import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;

public class TourelleMinigun extends Tourelle{

	public TourelleMinigun(int x, int y, Environnement env) {
		super(x, y, 1, 4, env, 20);
	}

	@Override
	public String getType() {
		return "Tourelle Minigun";
	}

}

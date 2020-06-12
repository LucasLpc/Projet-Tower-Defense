package applicationV1.modele.TourelleType;

import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;

public class TourelleSniper extends Tourelle{

	public TourelleSniper(int x, int y, Environnement env) {
		super(x, y, 75, 8, env, 60);
	}

}

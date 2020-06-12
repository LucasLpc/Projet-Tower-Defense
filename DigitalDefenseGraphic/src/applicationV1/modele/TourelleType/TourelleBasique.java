package applicationV1.modele.TourelleType;

import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;

public class TourelleBasique extends Tourelle{

	public TourelleBasique(int x, int y, Environnement env) {
		super(x, y, 10, 5, env,5);
	}

	@Override
	public String getType() {
		return "Tourelle Basique";
		
	}
	

}

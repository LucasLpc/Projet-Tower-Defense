package applicationV1.modele.TourelleType;

import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;
import applicationV1.modele.Tourelle;
import applicationV1.modele.TirType.TirBasique;

public class TourelleBasique extends Tourelle{

	public TourelleBasique(int x, int y, Environnement env) {
		super(x, y, 10, 5, 400, env,5);
	}
	
	@Override
	public void attaquer(Ennemi e) {
		if(this.env.getNbTours() % getCadence() == 0) {
			if(this.getEffet() == null) {
				TirBasique t = new TirBasique(this.getX(), this.getY(), this.env, this.degats, e, this.getEffet(), this);
				this.env.ajouterTir(t);
			}else if(this.getEffet() != null) {
				TirBasique t = new TirBasique(this.getX(), this.getY(), this.env, this.degats, e, this.getEffet().dupliquerEffet(), this);
				this.env.ajouterTir(t);
			}
				
			}						
	}

	@Override
	public String getType() {
		return "Tourelle Basique";
		
	}
	

}

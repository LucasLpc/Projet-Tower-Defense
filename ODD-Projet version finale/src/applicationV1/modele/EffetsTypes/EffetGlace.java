package applicationV1.modele.EffetsTypes;
import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;

public class EffetGlace extends Effet {

	public EffetGlace() {
		super(301, 4, 0, 0.2);
	}

	@Override
	public Effet dupliquerEffet() {
		return new EffetGlace();
	}

	@Override
	public void appliquerSur(Ennemi e) {
		System.out.println(this.getNbEffet());
		if(this.getNbEffet() > 0) {
			if(e.getVitesse()>0)
				e.setVitesse(e.getVitesse()+(int)((double)(e.getvInitial()) * this.getRalentissement()));
			this.setNbEffet(this.getNbEffet()-1);
		}else if(this.getNbEffet() == 0) {
			this.setEtat(false);
			e.setVitesse(e.getvInitial());
		}
	}

	@Override
	protected boolean peutEtreAjoute(ArrayList<Effet> effets) {
		for(int i = 0; i<effets.size(); i++) {
			if(effets.get(i) instanceof EffetGlace|| effets.get(i) instanceof EffetChoc) 
				return false;
		}
		return true;
	}
	
}

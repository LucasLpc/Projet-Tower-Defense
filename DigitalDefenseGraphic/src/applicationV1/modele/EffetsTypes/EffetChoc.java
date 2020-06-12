package applicationV1.modele.EffetsTypes;
import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;

public class EffetChoc extends Effet{

	public EffetChoc() {
		super(201, 10, 0, 201);
	}
	
	@Override
	public Effet dupliquerEffet() {
		return new EffetChoc();
	}

	@Override
	public void appliquerSur(Ennemi e) {
		System.out.println(this.getNbEffet());
		if(this.getNbEffet() > 0) {
			if(e.getVitesse()!=(int)(this.getRalentissement())) {
				e.setVitesse((int)(this.getRalentissement()));
			}else if(e.getVitesse()==(int)(this.getRalentissement())) {
				e.setVitesse(e.getvInitial());
			}
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

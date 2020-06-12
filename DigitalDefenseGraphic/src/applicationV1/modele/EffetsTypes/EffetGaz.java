package applicationV1.modele.EffetsTypes;
import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;

public class EffetGaz extends Effet {

	public EffetGaz() {
		super(101, 10, 0.02, 0);
	}

	@Override
	public Effet dupliquerEffet() {
		return new EffetGaz();
	}

	@Override
	public void appliquerSur(Ennemi e) {
		if(this.getNbEffet()>0) {
			e.perdreHp((int)((double)(e.getHpInitial()) * this.getDegat()));
			System.out.println(e.getHp() + " " + (int)((double)(e.getHpInitial()) * this.getDegat()));
			this.setNbEffet(this.getNbEffet()-1);
		}else if(this.getNbEffet() == 0) {
			this.setEtat(false);
		}
	}

	@Override
	protected boolean peutEtreAjoute(ArrayList<Effet> effets) {
		for(int i = 0; i<effets.size(); i++) {
			if(effets.get(i) instanceof EffetGaz) 
				return false;
		}
		return true;
	}
	
}

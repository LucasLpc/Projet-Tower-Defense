package applicationV1.modele.EffetsTypes;
import java.util.ArrayList;

import applicationV1.modele.Effet;
import applicationV1.modele.Ennemi;

public class EffetFeu extends Effet{

	public EffetFeu() {
		super(101, 10, 0.05, 0.4);
	}
	
	@Override
	public Effet dupliquerEffet() {
		return new EffetFeu();
	}

	@Override
	public void appliquerSur(Ennemi e) {
		if(this.getNbEffet()>0) {
			e.perdreHp((int)((double)(e.getHp()) * this.getDegat()) + 2 );
			if(this.getNbEffet()==10)e.setVitesse(e.getVitesse()-(int)((double)(e.getvInitial()*this.getRalentissement())));
			System.out.println(e.getHp() + " " + (int)((double)(e.getHpInitial()) * this.getDegat()) + " " + e.getVitesse());
			this.setNbEffet(this.getNbEffet()-1);
		}else if(this.getNbEffet() == 0) {
			this.setEtat(false);
			e.setVitesse(e.getvInitial());
		}
	}

	@Override
	protected boolean peutEtreAjoute(ArrayList<Effet> effets) {
		for(int i = 0; i<effets.size(); i++) {
			if(effets.get(i) instanceof EffetFeu) 
				return false;
		}
		return true;
	}
}

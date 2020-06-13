package applicationV1.modele;

import java.util.ArrayList;

import applicationV1.modele.EnnemiType.*;

public class Manche {
	private ArrayList<Ennemi> ennemis;
	private Environnement env;
	private int niveau;
	final int AUGMENTATION;
	
	public Manche(int niveau, int difficulté, Environnement env) {
		this.AUGMENTATION = (int) (1.5*difficulté);
		this.ennemis = new ArrayList<Ennemi>();
		this.env = env;
		this.niveau = niveau;
		this.remplirManche();
	}
	public ArrayList<Ennemi> getEnnemis(){
		return ennemis;
	
	}
	public void remplirManche() {
		int tirage;
		switch(niveau) {
		case 0  : 	for(int i=0;i<2*AUGMENTATION;i++) ennemis.add(new EnnemiCoyote(env));
		tirage = (int)Math.random()*3;
		if(tirage==3)
			for(int i=0;i<1*AUGMENTATION;i++) ennemis.add(new EnnemiCoyote(env));
		break;
		case 1 	:	for(int i=0;i<5*AUGMENTATION;i++) ennemis.add(new EnnemiCoyote(env));
		tirage = (int)Math.random()*3;
		if(tirage==3)
			for(int i=0;i<3*AUGMENTATION;i++) ennemis.add(new EnnemiCoyote(env));
		break;
		case 2 	: 	
			for(int i=0;i<5*AUGMENTATION;i++) ennemis.add(new EnnemiHyena(env));
			tirage = (int)Math.random()*3;
			if(tirage==3)
				for(int i=0;i<3*AUGMENTATION;i++) ennemis.add(new EnnemiHyena(env));
			break;
		case 3 	:	
			for(int i=0;i<5*AUGMENTATION;i++) ennemis.add(new EnnemiBear(env));
			tirage = (int)Math.random()*3;
			if(tirage==3)
				for(int i=0;i<3*AUGMENTATION;i++) ennemis.add(new EnnemiBear(env));
			break;
		case 4  :	
			for(int i=0;i<5*AUGMENTATION;i++) ennemis.add(new EnnemiLion(env));
			tirage = (int)Math.random()*3;
			if(tirage==3)
				for(int i=0;i<3*AUGMENTATION;i++) ennemis.add(new EnnemiLion(env));
			break;
		}
	}
	public void exeManche() {
		for(int i=0;i<this.ennemis.size();i++) {
			this.env.ajouterEnnemi(this.ennemis.remove(i));
		}
	}
}

package applicationV1.modele;

import java.util.ArrayList;

public abstract class Effet {
	
	private int numTours;
	private int nbEffet; 
	private double degat; 
	private double ralentissement;
	private boolean etat;
	
	public Effet(int numTours, int nbEffet, double degat, double ralentissement) {
		this.numTours = numTours;
		this.nbEffet = nbEffet;
		this.degat = degat;
		this.ralentissement = ralentissement;
		this.etat = true;
	}
	
	public abstract Effet dupliquerEffet();
	
	public abstract void appliquerSur(Ennemi e);
	
	protected abstract boolean peutEtreAjoute(ArrayList<Effet> effets);

	public double getRalentissement() {
		return ralentissement;
	}

	public int getNumTours() {
		return numTours;
	}
	
	public int getNbEffet() {
		return nbEffet;
	}
	
	public void setNbEffet(int nbEffet) {
		this.nbEffet = nbEffet;
	}
	
	public double getDegat() {
		return degat;
	}
	
	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
}

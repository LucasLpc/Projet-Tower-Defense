package applicationV1.modele.EnnemiType;

import java.util.ArrayList;
import java.util.Random;

import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.Noeud;

public class Ennemi extends Acteur{
	private int vitesse;
	private int direction;
	private int hp;

	public Ennemi(int x, int y, int v, int d, int hp, Environnement env) {
		super(x, y, env);
		this.vitesse = v;
		this.direction = d;
		this.hp = hp;
	}
	
	public Ennemi(int v, int hp, Environnement env) {
		super(0,0,env);
		nouvelleDirection();
		this.vitesse = v;
		this.hp = hp;
	}
	
	public void seDeplacer() {
		ArrayList<Noeud> liste = this.env.BFS(9, 9);
		int distance;
		for(int i=0;i<liste.size();i++) {
			if(liste.get(i).getX() == this.getX() && liste.get(i).getY() == this.getY()) { 
				distance = liste.get(i).getDistance()-1;
				for(int j=0;j<liste.size();j++) {
					if(liste.get(j).getDistance() == distance) {
						this.setX(liste.get(j).getX());
						this.setY(liste.get(j).getY());
					}
				}
			}
		}				
	}
	
	public void agir() {
		if(estMort()) {
			this.env.delEnnemi(this.getId());
		}
		else {
			this.seDeplacer();
		}
	}

	public void nouvelleDirection() {
		// 4 directions sont possibles 0 haut, 1 droite, 2 bas, 3 gauche.
		Random random = new Random();
		this.direction = random.nextInt(4); // attention cela genère un entier compris entre 0 et 3, le 4 est exclu.
	}
	
	public void perdreHp(int degats) {
		this.hp -= degats;
	}
	
	public boolean estMort() {
		if(this.hp <= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Ennemi [vitesse=" + vitesse + ", direction=" + direction + ", hp=" + hp + " " + super.toString() + "]";
	}
	

}
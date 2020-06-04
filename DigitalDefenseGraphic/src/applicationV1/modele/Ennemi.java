package applicationV1.modele;

import java.util.ArrayList;
import java.util.Random;

import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.Noeud;

public class Ennemi extends Acteur{
	private int vitesse;
	private int direction;
	private int hp;
	private int compteur;

	public Ennemi(int x, int y, int v, int d, int hp, Environnement env) {
		super(x, y, x*64, y*64, env);
		this.vitesse = v;
		this.direction = d;
		this.hp = hp;
		this.compteur = 0;
	}
	
	public Ennemi(int v, int hp, Environnement env) {
		super(0,0,0,0,env);
		nouvelleDirection();
		this.vitesse = v;
		this.hp = hp;
		this.compteur = 0;
	}
	
	public void seDeplacer() {
		
		
		if(this.compteur < 64) {
			switch(this.direction) {
			case 0 :
				setX64(getX64()-1);
			break;
			case 1 :
				setX64(getX64()+1);
			break;
			case 2 :
				setY64(getY64()-1);
			break;
			default :
				setY64(getY64()+1);
			break;
			}
			this.compteur += 1;
		}
		else if(this.compteur == 64){
			int minScore = this.env.getBfs().getTerrainAvecDistances()[getX()][getY()];
			if(getX() > 1 && this.env.getBfs().getTerrainAvecDistances()[getX()-1][getY()] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()-1][getY()] != -1) {
				minScore = this.env.getBfs().getTerrainAvecDistances()[getX()-1][getY()];
				this.direction = 0;
			}
			else if(getX() < this.env.getLargeur() && this.env.getBfs().getTerrainAvecDistances()[getX()+1][getY()] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()+1][getY()] != -1) {
				minScore = this.env.getBfs().getTerrainAvecDistances()[getX()+1][getY()];
				this.direction = 1;
			}
			else if(getY() > 1 && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()-1] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()-1] != -1) {
				minScore = this.env.getBfs().getTerrainAvecDistances()[getX()][getY()-1];
				this.direction = 2;
			}
			else if(getY() < this.env.getHauteur() && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()+1] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()+1] != -1) {
				minScore = this.env.getBfs().getTerrainAvecDistances()[getX()][getY()+1];
				this.direction = 3;
			}
			this.compteur = 0;
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
		int minScore = this.env.getBfs().getTerrainAvecDistances()[getX()][getY()];
		if(getX() > 1 && this.env.getBfs().getTerrainAvecDistances()[getX()-1][getY()] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()-1][getY()] != -1) {
			minScore = this.env.getBfs().getTerrainAvecDistances()[getX()-1][getY()];
			this.direction = 0;
		}
		else if(getX() < this.env.getLargeur() && this.env.getBfs().getTerrainAvecDistances()[getX()+1][getY()] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()+1][getY()] != -1) {
			minScore = this.env.getBfs().getTerrainAvecDistances()[getX()+1][getY()];
			this.direction = 1;
		}
		else if(getY() > 1 && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()-1] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()-1] != -1) {
			minScore = this.env.getBfs().getTerrainAvecDistances()[getX()][getY()-1];
			this.direction = 2;
		}
		else if(getY() < this.env.getHauteur() && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()+1] < minScore && this.env.getBfs().getTerrainAvecDistances()[getX()][getY()+1] != -1) {
			minScore = this.env.getBfs().getTerrainAvecDistances()[getX()][getY()+1];
			this.direction = 3;
		}
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
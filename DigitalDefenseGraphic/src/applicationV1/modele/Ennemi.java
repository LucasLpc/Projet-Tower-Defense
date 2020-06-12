package applicationV1.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.Noeud;
import applicationV1.modele.EffetsTypes.EffetChoc;
import applicationV1.modele.EffetsTypes.EffetFeu;
import applicationV1.modele.EffetsTypes.EffetGaz;
import applicationV1.modele.EffetsTypes.EffetGlace;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Ennemi extends Acteur{
	private int vitesse;
	private int vInitial;
	private int direction;
	private int hp;
	private int hpInitial;
	private int compteur;
	private DoubleProperty angle;
	private ArrayList<Effet> effets = new ArrayList<Effet>();

	public Ennemi(int x, int y, int v, int d, int hp, Environnement env) {
		super(x, y, x*64, y*64, env);
		this.vitesse = v;
		this.vInitial = v;
		this.direction = d;
		this.hp = hp;
		this.hpInitial = hp;
		this.compteur = 0;
		this.angle = new SimpleDoubleProperty(angleDeCetteDirection(this.direction));
	}
	
	public Ennemi(int v, int hp, Environnement env) {
		super(0,0,0,0,env);
		nouvelleDirection();
		this.vitesse = v;
		this.vInitial = v;
		this.hp = hp;
		this.hpInitial = hp;
		this.compteur = 0;
		this.angle = new SimpleDoubleProperty(angleDeCetteDirection(this.direction));
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
			refreshAngle();
			System.out.println(this.effets);
		}
	}
	
	public void mourrir() {
		this.perdreHp(this.hp);
	}
	
	public void agir() {
		if(estMort()) {
			this.env.delEnnemi(this.getId());
			this.env.getBanque().ajouterSolde(10);
		}
		else if (estArrive()) {
			atteindreFin();
		}else if(this.env.getNbTours() % this.vitesse == 0) {
				this.seDeplacer();
				if(effets.size() > 0) {
					for(int i = 0; i<this.effets.size(); i++) {
						if(!effets.get(i).isEtat()) effets.remove(i);
						if(effets.get(i).isEtat() && this.env.getNbTours() % effets.get(i).getNumTours() == 0) 
							effets.get(i).appliquerSur(this);
					}
				}
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
	
	public int getHp() {
		return hp;
	}
	
	public int getHpInitial() {
		return hpInitial;
	}

	public boolean estMort() {
		if(this.hp <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double angleDeCetteDirection(int d) {
		switch(d) {
		case 0:
			return 270;
		case 1:
			return 90;
		case 2:
			return 0;
		default:
			return 180;
		
		}
	}
	
	public boolean estArrive() {
		if (this.getX64() == 9*64 && this.getY64() == 9*64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void atteindreFin() {
		this.env.delEnnemi(this.getId());
		this.env.getBase().subirDegats(10);
	}
	
	public void refreshAngle() {
		setAngle(angleDeCetteDirection(this.direction));
	}
	
	public double getAngle() {
		return this.angle.getValue();
	}
	
	public void setAngle(double angle) {
		this.angle.setValue(angle);
	}
	
	public DoubleProperty getAngleProperty() {
		return angle;
	}
	
	public void ajouterEffet (Effet effet) {
		if(peutEtreAjouter(effet))this.effets.add(effet);
	}
	
	public boolean peutEtreAjouter(Effet e) {
		return e.peutEtreAjoute(this.effets);
	}
	
	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getvInitial() {
		return vInitial;
	}

	public void setvInitial(int vInitial) {
		this.vInitial = vInitial;
	}

	@Override
	public String toString() {
		return "Ennemi [vitesse=" + vitesse + ", direction=" + direction + ", hp=" + hp + " " + super.toString() + this.effets + "]";
	}
	

}
package applicationV1.modele;

import java.util.Random;

public class Ennemi extends Acteur{
	private int vitesse;
	private int direction;
	private int hp;

	public Ennemi(int x, int y, int v, int d, int hp, Environnement env) {
		// L'ennemi cr�� aura une position donn�e, une direction donn�e.
		super(x, y, env);
		this.vitesse = v;
		this.direction = d;
		this.hp = hp;
		// TODO Auto-generated constructor stub
	}
	
	public Ennemi(int v, int hp, Environnement env) {
		// L'ennemi cr�� aura une position al�atoire, une direction al�atoire.
		super(env);
		nouvelleDirection();
		this.vitesse = v;
		this.hp = hp;
		// TODO Auto-generated constructor stub
	}
	
	public void seD�placer() {
		// Cette m�thode ne permet � l'ennemi de se d�placer que si la case sur laquelle il s'apr�te � aller est disponible
		int nposX;
		int nposY;
		do {
			switch(direction) {
			case 0:
				nposX = this.getX();
				nposY = this.getY()+1;
			break;
			case 1:
				nposX = this.getX()+1;
				nposY = this.getY();
			break;
			case 2:
				nposX = this.getX();
				nposY = this.getY()-1;
			break;
			case 3:
				nposX = this.getX()-1;
				nposY = this.getY();
			break;
			default:
				nposX = -1;
				nposY = -1;
			}
			if(!this.env.positionValable(nposX, nposY)) {
				nouvelleDirection();
			}
		}while(!this.env.positionValable(nposX, nposY));
		setX(nposX);
		setY(nposY);
	}

	public void nouvelleDirection() {
		// 4 directions sont possibles 0 haut, 1 droite, 2 bas, 3 gauche.
		Random random = new Random();
		this.direction = random.nextInt(4); // attention cela g�n�re un entier compris entre 0 et 3, le 4 est exclu.
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

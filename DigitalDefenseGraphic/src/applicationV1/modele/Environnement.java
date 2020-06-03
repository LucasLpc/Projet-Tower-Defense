package applicationV1.modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import applicationV1.modele.EnnemiType.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private ObservableList<Ennemi> ennemis;
	private ObservableList<Tourelle> tourelles;
	private ObservableList<Tir> tirs;
	private int nbTours;
	private Bfs bfs;
	private char terrain[] = {'c','v','v','v','v','v','v','v','v','v',
							'c','v','v','v','v','v','v','v','v','v',
							'c','c','c','v','v','c','c','c','v','v',
							'v','t','c','v','v','c','t','c','v','v',
							'v','v','c','c','c','c','v','c','v','v',
							'v','v','v','v','v','v','v','c','v','v',
							'v','v','v','v','v','v','v','c','v','v',
							'v','v','v','v','v','v','v','c','c','v',
							'v','v','v','v','v','v','v','t','c','v',
							'v','v','v','v','v','v','v','v','c','c'};
	private char terrain2D[][] = tab2D(terrain,10,10);
	
	public Environnement() {
		this.ennemis = FXCollections.observableArrayList();
		this.tourelles = FXCollections.observableArrayList();
		this.tirs = FXCollections.observableArrayList();
		this.bfs = new Bfs(this.getTerrain2D());
	}
	public char[][] getTerrain2D() {
		return terrain2D;
	}
	
	private char[][] tab2D(char[] n, int ligne, int colonne) {
		char[][] tab = new char[ligne][colonne];
		int indice =0;
		for(int i = 0; i < ligne; i++){
			for(int j=0; j< colonne; j++ ){
				tab[i][j] = n[indice];
	          	indice++;
			}
		}
		return tab;
	 }
	
	public void unTour() {
		// Cette m�thode permet le d�roul� d'un tour de jeu (attention ici un tour de jeu d�signe 1 action par acteur, d�placement ou attaquer)
		// A remarquer : cette m�thode rafraichi �galement le terrain, cela permet que le d�placement et les morts soient pris en compte par l'environnement.
		for(int i = 0; i < getNbEnnemis(); i++) {
			this.ennemis.get(i).agir();
		}
		for(int j = 0; j < getNbTourelles(); j++) {
			this.tourelles.get(j).agir();
			this.tourelles.get(j).getAngle();		
		}
		for(int k = 0; k < getNbTirs(); k++) {
			this.tirs.get(k).agir();
		}
		this.nbTours += 1;
	}

	public void ajouterTourelle(Tourelle t) {
		this.tourelles.add(t);
	}
	
	public void ajouterEnnemi(Ennemi e) {
		this.ennemis.add(e);
	}
	
	public void ajouterTir(Tir t) {
		this.tirs.add(t);
	}
	
	public void delEnnemi(String id) {
		for(int i = getNbEnnemis()-1 ; i >= 0; i--) {
			if(getEnnemis().get(i).getId() == id) {
				this.ennemis.remove(i);
			}
		}
	}
	
	public boolean positionValableEnnemi(int x, int y) {
		// cette methode permet de savoir si un ennemi est autorise a acceder à  cette position.
		if(this.terrain2D[x][y] != 'c') {
			return false;
		}
		return true;
	}

	public int getLargeur() {
		return this.terrain2D.length;
	}

	public int getHauteur() {
		return this.terrain2D[0].length;
	}

	public int getNbActeurs(){
		return ennemis.size() + tourelles.size();
	}

	public int getNbEnnemis() {
		return this.ennemis.size();
	}
	
	public int getNbTourelles() {
		return this.tourelles.size();
	}
	
	public int getNbTirs() {
		return this.tirs.size();
	}
	
	public ObservableList<Ennemi> getEnnemis() {
		return this.ennemis;
	}
	
	public ObservableList<Tourelle> getTourelles(){
		return this.tourelles;
	}
	
	public ObservableList<Tir> getTirs(){
		return this.tirs;
	}
	
	public char getCase(int x, int y) {
		return this.terrain2D[x][y];
	}
	
	public char[][] getTerrain(){
		return this.terrain2D;
	}
	
	public Bfs getBfs() {
		return this.bfs;
	}
	
	@Override
	public String toString() {
		return "Environnement [acteurs=" + getNbActeurs() + ", nbTours=" + nbTours + "]";
	}
}
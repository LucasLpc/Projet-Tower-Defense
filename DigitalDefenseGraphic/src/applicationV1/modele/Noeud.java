package applicationV1.modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math.*;

public class Noeud {
	private int distance;
	private int x,y;
	LinkedList<Noeud> adjacent = new LinkedList<>();
	public Noeud(int x, int y, int distance) {
		this.x=x;
		this.y=y;
		this.distance=distance;
	}
	public boolean estDansListe(ArrayList<Noeud> liste) {
		for(int i=0;i<liste.size();i++) {
			if(liste.get(i).getX() == this.x && liste.get(i).getY() == this.y) return true;
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDistance() {
		return this.distance;
	}
	public void incrDistance() {
		this.distance++;
	}
	public String toString() {
		return this.x + "," + this.y + "," + this.distance;
	}
	
}

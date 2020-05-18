package applicationV1.modele;

import java.util.LinkedList;

public class Noeud {
	private int distance,x,y;
	LinkedList<Noeud> adjacent = new LinkedList<>();
	
	public Noeud(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getDistance() {
		return this.distance;
	}
	
}

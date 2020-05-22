package applicationV1.modele;

import java.util.LinkedList;
import java.lang.Math.*;

public class Noeud {
	private static int distance = 0;
//	private int distance = (Integer)null;
	private int x,y;
	private char value;
	LinkedList<Noeud> adjacent = new LinkedList<>();
	
	public Noeud(int x, int y, char v) {
		this.x=x;
		this.y=y;
		this.value=v;
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
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
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

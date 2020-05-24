package applicationV1.modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math.*;

public class Noeud {
	private int distance;
	private int x,y;
	private char value;
	private Environnement env;
	LinkedList<Noeud> adjacent = new LinkedList<>();
	public Noeud(int x, int y, int distance) {
		this.x=x;
		this.y=y;
		this.distance=distance;
	}
//	private LinkedList<Noeud> adjacents(Noeud noeud){
//		LinkedList<Noeud> file = new LinkedList<>();
//		
////		if(noeud.getX()-1<0 || noeud.getY()-1<0 || noeud.getX()+1>this.terrain2D.length || noeud.getY()+1>this.terrain2D[0].length)
////			throw new Error("Sortie de tableau");
////		else {
////			if(this.terrain2D[noeud.getX()-1][noeud.getY()] == 'c') {
////				file.add(new Noeud(noeud.getX()-1,noeud.getY(),'c'));
////			}
////			if(this.terrain2D[noeud.getX()][noeud.getY()-1] == 'c') {
////				file.add(new Noeud(noeud.getX(),noeud.getY()-1,'c'));
////			}
////			if(this.terrain2D[noeud.getX()+1][noeud.getY()] == 'c') {
////				file.add(new Noeud(noeud.getX()+1,noeud.getY(),'c'));
////			}
////			if(this.terrain2D[noeud.getX()][noeud.getY()+1] == 'c') {
////				file.add(new Noeud(noeud.getX(),noeud.getY()+1,'c'));
////			}
////		}
//		if(this.env.terrain2D.length > noeud.getX()+1) {
//			if(this.env.terrain2D[noeud.getX()+1][noeud.getY()] == 'c') 
//				file.add(new Noeud(noeud.getX()+1,noeud.getY(),'c'));
//			else System.out.println("la case X+1 n'est pas pratiquable");
//		}else System.out.println("la case X+1 n'est pas valable");
//
//		if(0 <= noeud.getX()-1) {
//			if(this.env.terrain2D[noeud.getX()-1][noeud.getY()] == 'c') 
//				file.add(new Noeud(noeud.getX()-1,noeud.getY(),'c'));
//			else System.out.println("la case X-1 n'est pas pratiquable");
//		}else System.out.println("la case X-1 n'est pas valable");
//
//		if(this.env.terrain2D[0].length > noeud.getY()+1) {
//			if(this.env.terrain2D[noeud.getX()][noeud.getY()+1] == 'c') 
//				file.add(new Noeud(noeud.getX(),noeud.getY()+1,'c'));
//			else System.out.println("la case Y+1 n'est pas pratiquable");
//		}else System.out.println("la case Y+1 n'est pas valable");
//
//		if(0 <= noeud.getY()-1) {
//			if (this.env.terrain2D[noeud.getX()][noeud.getY()-1] == 'c') 
//				file.add(new Noeud(noeud.getX(),noeud.getY()-1,'c'));
//			else System.out.println("la case Y-1 n'est pas pratiquable");
//		}else System.out.println("la case Y-1 n'est pas valable");
//
//
//		return file;
//	}
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

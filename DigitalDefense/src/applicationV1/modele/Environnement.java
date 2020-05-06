package applicationV1.modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Environnement {

	private ArrayList<Ennemi> ennemis;
	private IntegerProperty nbToursProperty;
	private int terrain[][];
	
	public Environnement(int x, int y) {
		super();
		this.nbToursProperty = new SimpleIntegerProperty(0);
		this.ennemis= new ArrayList();
		this.terrain = new int[x][y];
	}
	public void initTerrain() {
		// Création du terrain
		for(int i=0; i<this.terrain.length;i++) {
			for(int j=0; j<this.terrain[i].length;j++) {
				if(i!=0 && i!=this.terrain.length-1 && j!=this.terrain[i].length-1 && j!=0) 
					this.terrain[i][j]=1;	
				else this.terrain[i][j]=0;
			}
		}
		
	}
	public void refresh() {
		for(int i=0; i<this.ennemis.size();i++) {
			this.terrain[ennemis.get(i).getX()][ennemis.get(i).getY()]=1;
			ennemis.get(i).seDeplace();
			this.terrain[ennemis.get(i).getX()][ennemis.get(i).getY()]=2;
			
		}
		
		afficherTerrain();
		
	}
	public void afficherTerrain() {
		for(int i=0; i<this.terrain.length;i++) {
			System.out.println();
			for(int j=0; j<this.terrain[i].length;j++) {
				System.out.print(terrain[i][j] + " ");
			}
		}
	}

	public final int getNbTours(){
		return this.nbToursProperty.getValue();	
	}
	
	public final IntegerProperty getNbToursProperty(){
		return nbToursProperty;
	}

	public final void setNbTours(int n){
		this.nbToursProperty.setValue(n);
	}

	public int getWidth() {
		return this.terrain.length;
	}

	public int getHeight() {
		return this.terrain[0].length;
	}

	public ArrayList<Ennemi> getActeurs() {
		return ennemis;
	}

	public Ennemi getActeur(String id) {
		for(Ennemi a:this.ennemis){
			if(a.getId().equals(id)){
				return a;
			}
		}
		return null;
	}


	public void ajouter(Ennemi e){
		ennemis.add(e);
		this.terrain[e.getX()][e.getY()]=2;
	}

	public boolean dansTerrain(int x, int y){
		if(terrain[x][y] == 1)
			return true;
		return false;
	}

	public void unTour(){
		// cela ne peut etre un foreach a cause des naissances 
		// modification de acteurs.
		for(int i=0;i<ennemis.size(); i++){
			Ennemi e = ennemis.get(i);			
			e.seDeplace();
		}
		for(int i=ennemis.size()-1; i>=0;i--){
			Ennemi e = ennemis.get(i);
			if(!e.estVivant()){
				System.out.println("mort de : " + e);
				ennemis.remove(i);
			}
		}
		this.nbToursProperty.setValue(this.nbToursProperty.getValue()+1);
	}
}

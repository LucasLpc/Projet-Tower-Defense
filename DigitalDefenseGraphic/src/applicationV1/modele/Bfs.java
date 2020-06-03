package applicationV1.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class Bfs {
	private char terrain2D[][];
	private int terrainAvecDistances[][];
	
	public Bfs(char terrain2D[][]) {
		this.terrain2D = terrain2D;
		this.terrainAvecDistances = chargerTerrainBfs(9, 9);
	}
	
	public int[][] chargerTerrainBfs(int x, int y){
		int[][] terrainAvecDist = new int[this.terrain2D.length][this.terrain2D[0].length];
		ArrayList<Noeud> liste = BFS(x, y); 
		for(int i = 0; i < terrainAvecDist.length; i++) {
			for(int j = 0; j < terrainAvecDist[0].length; j++) {
				for(int k = 0; k < liste.size(); k++) {
					if(liste.get(k).getX() == i && liste.get(k).getY() == j) {
						terrainAvecDist[i][j] = liste.get(k).getDistance();
						k = liste.size();
					}
					else {
						terrainAvecDist[i][j] = -1;
					}
				}
			}
		}
		
		for(int i = 0; i < terrainAvecDist.length; i++) {
			for(int j = 0; j < terrainAvecDist[0].length; j++) {
				System.out.print(terrainAvecDist[i][j] + " ");
			}
			System.out.println();
		}
		
		return terrainAvecDist;
	}
	
	public ArrayList<Noeud> BFS(int x,int y){			
		ArrayList<Noeud> file = new ArrayList<Noeud>();
		Noeud noeud = new Noeud(x,y,0);
		file.add(noeud);
		while(!(noeud.getX() == 0 && noeud.getY() == 0)) {
			noeud = file.get(file.size()-1);
			noeud.adjacent = adjacents(noeud);
			for(Noeud adjNoeud : noeud.adjacent) {
				if(!adjNoeud.estDansListe(file)) {
//					System.out.println(adjNoeud);					
					file.add(adjNoeud);
				}
			}
		}
		return file;
		
	}
	
	private LinkedList<Noeud> adjacents(Noeud noeud){

		LinkedList<Noeud> file = new LinkedList<>();
		int distance = noeud.getDistance()+1;
		if(this.terrain2D.length > noeud.getX()+1) {
            if(this.terrain2D[noeud.getY()][noeud.getX()+1] == 'c') 
                file.add(new Noeud(noeud.getX()+1,noeud.getY(),distance));
//            else System.out.println("la case X+1 n'est pas pratiquable");
        }
//            else System.out.println("la case X+1 n'est pas valable");

        if(0 <= noeud.getX()-1) {
            if(this.terrain2D[noeud.getY()][noeud.getX()-1] == 'c') 
                file.add(new Noeud(noeud.getX()-1,noeud.getY(),distance));
//            else System.out.println("la case X-1 n'est pas pratiquable");
            }
//            else System.out.println("la case X-1 n'est pas valable");

        if(this.terrain2D[0].length > noeud.getY()+1) {
            if(this.terrain2D[noeud.getY()+1][noeud.getX()] == 'c') 
                file.add(new Noeud(noeud.getX(),noeud.getY()+1,distance));
//            else System.out.println("la case Y+1 n'est pas pratiquable");
      }
//            else System.out.println("la case Y+1 n'est pas valable");

        if(0 <= noeud.getY()-1) {
            if (this.terrain2D[noeud.getY()-1][noeud.getX()] == 'c') 
                file.add(new Noeud(noeud.getX(),noeud.getY()-1,distance));
//            else System.out.println("la case Y-1 n'est pas pratiquable");
        }
//        else System.out.println("la case Y-1 n'est pas valable");


		return file;
	}
	
	public int[][] getTerrainAvecDistances() {
		return this.terrainAvecDistances;
	}
	
}

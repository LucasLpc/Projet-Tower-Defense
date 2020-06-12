package applicationV1.modele;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private ObservableList<Ennemi> ennemis;
	private ObservableList<Tourelle> tourelles;
	private ObservableList<Tir> tirs;
	private BooleanProperty statutPartie;
	private int nbTours;
	private Bfs bfs;
	private Base base;
	private Manche manche;
	private Banque banque;
	private static int cptManche = 0;
	private static IntegerProperty cptMancheGlobale;
	private static int difficulté = 1;
	private char[][] terrain2D = new char[10][14];
	
	public Environnement() {
		this.terrain2D = Config.tab2D();
		this.ennemis = FXCollections.observableArrayList();
		this.tourelles = FXCollections.observableArrayList();
		this.tirs = FXCollections.observableArrayList();
		this.base = new Base(9, 9, 9*64, 9*64, 50, this);
		this.bfs = new Bfs(this);
		this.banque = new Banque();
		this.manche = new Manche(1,0,this);
		this.cptMancheGlobale = new SimpleIntegerProperty(0);
		this.statutPartie = new SimpleBooleanProperty(true);
	}
	public BooleanProperty getStatutPartieProperty() {
		return statutPartie;
	}
	public void setStatutPartie(boolean b) {
		this.statutPartie.setValue(b);
	}
	public boolean getStatutPartie() {
		return this.statutPartie.getValue();
	}
	public IntegerProperty getCptMancheGlobaleProperty() {
		return cptMancheGlobale;
	}
	public void setCptMancheGlobale(int v) {
		cptMancheGlobale.setValue(v);
	}
	public int getCptMancheGlobale() {
		return cptMancheGlobale.getValue();
	}
	public Banque getBanque() {
		return banque;
	}
	public void setBanque(Banque banque) {
		this.banque = banque;
	}
	public Manche getManche() {
		return manche;
	}
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	public char[][] getTerrain2D() {
		return terrain2D;
	}
	public void nouvelleManche() {
		this.manche = new Manche(cptManche,difficulté, this);
		int cpt = cptManche;
		System.out.println(cptManche);
		if(cpt+1 == 5) {
			cptManche=0;
			difficulté++;
		}
		else cptManche++;	
		cptMancheGlobale.setValue(cptMancheGlobale.getValue()+1);
	}
	
	public void unTour() {
		// Cette mï¿½thode permet le dï¿½roulï¿½ d'un tour de jeu (attention ici un tour de jeu dï¿½signe 1 action par acteur, dï¿½placement ou attaquer)
		// A remarquer : cette mï¿½thode rafraichi ï¿½galement le terrain, cela permet que le dï¿½placement et les morts soient pris en compte par l'environnement.
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
		if(manche.getEnnemis().size() > 0) {
			if(this.getNbTours() % 200 == 0) {
				manche.exeManche();
			}
		}
		if(this.base.mort()) {
			this.statutPartie.setValue(false);
		}
		this.nbTours += 1;
	}
	public void ajouterTourelle(Tourelle t) {
		this.tourelles.add(t);
	}
	public void enleverTourelle(Tourelle t) {
		this.tourelles.remove(t);
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
	public void delTir(String id) {
		for(int i = getNbTirs()-1 ; i >= 0; i--) {
			if(getTirs().get(i).getId() == id) {
				this.tirs.remove(i);
			}
		}
	}
	
	public boolean positionValableEnnemi(int x, int y) {
		// cette methode permet de savoir si un ennemi est autorise a acceder Ã   cette position.
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
	
	public int getNbTours() {
		return this.nbTours;
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
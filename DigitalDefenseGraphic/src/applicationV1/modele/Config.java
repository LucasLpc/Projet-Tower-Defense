package applicationV1.modele;

import java.io.FileReader;
import java.io.IOException;

public class Config {
	private final static int LONGUEUR = 10;
	private final static int HAUTEUR = 10;
	private static char[] map = {
			'c','v','v','v','v','v','v','v','v','v',
			'c','v','v','v','v','v','v','v','v','v',
			'c','c','c','v','v','c','c','c','v','v',
			'v','t','c','v','v','c','t','c','v','v',
			'v','v','c','c','c','c','v','c','v','v',
			'v','v','v','v','v','v','v','c','v','v',
			'v','v','v','v','v','v','v','c','v','v',
			'v','v','v','v','v','v','v','c','c','v',
			'v','v','v','v','v','v','v','t','c','v',
			'v','v','v','v','v','v','v','v','c','c'};
	public static char[][] tab2D() {
		char[][] tab = new char[LONGUEUR][HAUTEUR];
		int indice =0;
		for(int i = 0; i < LONGUEUR; i++){
			for(int j=0; j< HAUTEUR; j++ ){
				tab[i][j] = map[indice];
	          	indice++;
			}
		}
		return tab;
	 }
}

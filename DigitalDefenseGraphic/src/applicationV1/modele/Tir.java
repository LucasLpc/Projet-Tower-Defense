package applicationV1.modele;

public class Tir extends Acteur{
	
	private int dmg;
	private Ennemi cible;
	
	public Tir(int x, int y, Environnement env, int dmg, Ennemi cible) {
		super(x, y, x*64+32, y*64+32, env);
		this.dmg=dmg;
		this.cible=cible;
	}
	
	@Override
	public void agir() {
		seDeplacer();
		if(cibleAtteinte()) {
			this.env.delTir(this.getId());
			this.cible.perdreHp(this.dmg);
		}
	}
	
	public void seDeplacer() {
		double deltaY64 = this.cible.getY64()+32 - this.getY64();
		double deltaX64 = this.cible.getX64()+32 - this.getX64();
		double pente;
		if(deltaY64 == 0) {
			pente = 0;
		}
		else {
			if (deltaX64 == 0) {
				if(deltaY64 > 0) {
					pente = 1;
				}
				else if (deltaY64 < 0) {
					pente = -1;
				}
				else {
					pente = 0;
				}
			}
			else {
				pente = deltaY64 / deltaX64;
			}
		}
		System.out.println(pente);

		if(deltaX64 > 0) {
			this.setX64(this.getX64() + 1);
			this.setY64(this.getY64() + pente);
		}
		else if(deltaX64 < 0) {
			this.setX64(this.getX64() - 1);
			this.setY64(this.getY64() - pente);
		}
		else {
				this.setY64(this.getY64() + pente);	
		}
	}
	
	public boolean cibleAtteinte() {
		if(this.getX64() == cible.getX64()+32 && this.getY64() == cible.getY64()+32) {
			return true;
		}
		else {
			return false;
		}
	}
}

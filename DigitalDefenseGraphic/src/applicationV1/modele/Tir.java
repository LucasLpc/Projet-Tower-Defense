package applicationV1.modele;

public class Tir extends Acteur{
	
	private int dmg;
	private Ennemi cible;
	
	public Tir(int x, int y, Environnement env, int dmg, Ennemi cible) {
		super(x, y, x*64+16, y*64+16, env);
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
		
		// Je veux que le projectile se deplace d'une distance de 1 par deplacement, il s'agit donc de l'intersection de la droite de trajectoire 
		// et d'un cercle de rayon 1 autour de la position actuelle du projectile
		// -------------------------------------------------------------
		// y = ax + b : equation de droite
		// (x - xo)² + (y - yo)² = 1 : equation de cercle
		// -----------------------------
		// (x - xo)² + (ax + (b - yo))² = 1
		// x² -2*xo*x + xo² + (ax)² - 2*(b - yo)*(ax) + (b - yo)² = 1
		// x² -2*xo*x + xo² + a²*x² - 2*(b - yo)*(ax) + (b - yo)² = 1
		// x² -2*xo*x + xo² + a²*x² - 2*(b - yo)*(ax) + (b - yo)² -1 = 0
		// x² (1 + a²) + x ((-2*xo*x) + (-2*(b-yo)*a)) + 1 (xo² + (b-yo)²) = 0
		// ---------------------------------------------------------------------
		// On a donc une equation du type ax²+bx+c = 0
		// discriminant = b² - 4ac
		// discriminant = ((-2*xo*x) + (-2*(b-yo)*a))² - 4 * (1 + a²) * (xo² + (b-yo)²)
		// ---------------------------------------------------------------------
		// racine1 = (-b + Racine(discriminant)) / 2a)
		//		   = (-((-2*xo*x) + (-2*(b-yo)*a)) + Racine(discriminant)) / 2 * (1 + a²)) 
		// racine2 = (-b - Racine(discriminant)) / 2a)
		//		   = (-((-2*xo*x) + (-2*(b-yo)*a)) - Racine(discriminant)) / 2 * (1 + a²))

		double xo = this.getX64();
		double yo = this.getY64();
		double a = deltaY64 / deltaX64;
		double b = yo - a*xo;
		double discriminant = Math.pow(((-2 * xo) + (2 * a * (b - yo))), 2) - 4 * (1 + Math.pow(a, 2)) * (Math.pow(xo, 2) + Math.pow(b-yo, 2) - 1);
		double racine1 = (-((-2 * xo) + (2 * a * (b - yo))) + Math.sqrt(discriminant)) / (2 * (1 + Math.pow(a, 2)));
		double racine2 = (-((-2 * xo) + (2 * a * (b - yo))) - Math.sqrt(discriminant)) / (2 * (1 + Math.pow(a, 2)));
		if(deltaX64 > 0) {
			this.setX64(racine1);
			this.setY64(this.getY64() + (racine1 - xo) * a);
		}
		else if (deltaX64 < 0) {
			this.setX64(racine2);
			this.setY64(this.getY64() + (racine2 - xo) * a);
		}
		else {
			if (deltaY64 > 0) {
				this.setY64(this.getY64() + 1);
			}
			else if (deltaY64 < 0) {
				this.setY64(this.getY64() - 1);
			}
		}
	}
	
	public boolean cibleAtteinte() {
		if(Math.sqrt(Math.pow(this.cible.getX64()+32 - this.getX64(), 2) + Math.pow(this.cible.getY64()+32 - this.getY64(), 2)) <= 1) {
			return true;
		}
		return false;
	}
	
//	public boolean cibleAtteinte() {
//		if(this.getX64() == cible.getX64()+32 && this.getY64() == cible.getY64()+32) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
}

package applicationV1.modele;

public class Tir extends Acteur{
	
	private int dmg;
	private Acteur cible;
	private Trajectoire traj;
	
	public Tir(int x64, int y64, Environnement env, int dmg, Acteur cible) {
		super(x64/64, y64/64, x64, y64, env);
		this.dmg=dmg;
		this.cible=cible;
		this.traj = new Trajectoire(x64, y64, cible.getX64(), cible.getY64());
	}
	
	@Override
	public void agir() {
		// TODO Auto-generated method stub
	}
	
//	while x64 != cible.getX64() +32
//	idem avec y
}

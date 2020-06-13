package applicationV1.modele;

public class Base extends Acteur{

	private int hp;

	public Base(int x, int y, int x64, int y64, int hp,Environnement env) {
		super(x, y, x64, y64, env);
		this.hp = hp;
		// TODO Auto-generated constructor stub
	}
	
	public void subirDegats(int degats) {
		this.hp -= degats;
	}
	
	public boolean mort() {
		if(this.hp > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void endGame() {
		System.out.println("Tu as perdu !!");
	}

	@Override
	public void agir() {
		if(mort()) {
			endGame();
		}
	}
	

}

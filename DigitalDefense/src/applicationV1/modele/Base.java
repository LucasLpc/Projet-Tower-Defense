package applicationV1.modele;

public class Base {
	
	private int x;
	private int y;
	protected Environnement map;
	private int hp;
	
	public Base(Environnement map) {
		this.x = 8;
		this.y = 8;
		this.map = map;
		this.hp = 1000;
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public void perdreHP() {
		
	}
	
}

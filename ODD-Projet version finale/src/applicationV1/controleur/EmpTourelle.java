package applicationV1.controleur;

public class EmpTourelle extends javafx.scene.image.ImageView {
	public boolean occupation;
	public EmpTourelle() {
		super("ressources/tilePlacementTest.png");
		this.occupation=false;
	}
	public boolean isOccupation() {
		return occupation;
	}
	public void setOccupation(boolean occupation) {
		this.occupation = occupation;
	}
	
}

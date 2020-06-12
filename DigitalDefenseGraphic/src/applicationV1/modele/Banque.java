package applicationV1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Banque {
	private IntegerProperty bourse;
	private double multiplicateur = 1;
	public Banque() {
		this.bourse = new SimpleIntegerProperty(30);
	}
	public Banque(int v) {
		this.bourse = new SimpleIntegerProperty(v);
	}
	public void ajouterSolde(int v) {
		if(bourse.getValue() >= 100) this.multiplicateur = bourse.getValue()/100;
		this.bourse.setValue((int) (bourse.getValue()+v*multiplicateur));		
	}
	public void acheter(int v) {
		this.bourse.setValue(bourse.getValue()-v);
	}
	public boolean achetable(int v) {
		if(bourse.getValue()-v >= 0) return true;
		return false;
	}
	public int getBourse() {
		return this.bourse.getValue();
	}
	public IntegerProperty getBourseProperty() {
		return this.bourse;
	}
	public void setBourse(int v) {
		this.bourse.setValue(v);
	}
}

package applicationV1.modele.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import applicationV1.modele.Ennemi;
import applicationV1.modele.Environnement;

class TestEnnemi {

	@Test
	void testMourir() {
		Environnement env = new Environnement();
		Ennemi e = new Ennemi(1, 20, env);
		e.mourrir();
		assertEquals(0, e.getHp());
	}
	
	@Test
	void testPerdreHp() {
		Environnement env = new Environnement();
		Ennemi e = new Ennemi(1, 20, env);
		int v = 10;
		e.perdreHp(v);
		assertEquals(10, e.getHp());
	}
	
	@Test
	void testEstMortTrue() {
		Environnement env = new Environnement();
		Ennemi e = new Ennemi(1, 20, env);
		int v = 30;
		e.perdreHp(v);
		assertTrue(e.estMort());
	}
	
	@Test
	void testEstMortEgal() {
		Environnement env = new Environnement();
		Ennemi e = new Ennemi(1, 20, env);
		int v = 20;
		e.perdreHp(v);
		assertTrue(e.estMort());
	}
	
	@Test
	void testEstMortFaux() {
		Environnement env = new Environnement();
		Ennemi e = new Ennemi(1, 20, env);
		int v = 10;
		e.perdreHp(v);
		assertFalse(e.estMort());
	}
	
	@Test
	void testAngleDeCetteDirectionCase0() {
		Environnement env = new Environnement();
		Ennemi e = new Ennemi(1, 20, env);
		int v = 0;
		assertEquals(270, e.angleDeCetteDirection(v));
	}

}
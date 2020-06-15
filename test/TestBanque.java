package applicationV1.modele.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import applicationV1.modele.Banque;

class TestBanque {

	@Test
	void testAchetableFalse() {
		Banque b = new Banque(30);
		int v = 40;
		assertFalse(b.achetable(v));
	}
	
	@Test
	void testAchetableTrue() {
		Banque b = new Banque(30);
		int v = 20;
		assertTrue(b.achetable(v));
	}
	
	@Test
	void testAchetableEgal() {
		Banque b = new Banque(30);
		int v = 30;
		assertTrue(b.achetable(v));
	}
	
	@Test
	void testAjouterSoldeSuperieur100() {
		Banque b = new Banque(130);
		int v = 30;
		b.ajouterSolde(v);
		assertEquals(160, b.getBourse());
	}
	
	@Test
	void testAjouterSoldeSuperieur200() {
		Banque b = new Banque(230);
		int v = 30;
		b.ajouterSolde(v);
		assertEquals(290, b.getBourse());
	}
	
	@Test
	void testAjouterSoldeInferieur100() {
		Banque b = new Banque(30);
		int v = 30;
		b.ajouterSolde(v);
		assertEquals(60, b.getBourse());
	}
	
	@Test
	void testAcheter() {
		Banque b = new Banque(30);
		int v = 30;
		b.acheter(v);
		assertEquals(0, b.getBourse());
	}

}

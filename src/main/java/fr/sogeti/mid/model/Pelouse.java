package fr.sogeti.mid.model;

public class Pelouse {

	private final short longueur;
	private final short largeur;
	private boolean[][] cells;

	public Pelouse(String s1, String s2) {
		this.longueur = Short.parseShort(s1);
		this.largeur = Short.parseShort(s2);
		cells = new boolean[this.largeur+1][this.longueur+1];
	}

	public short getLongueur() {
		return longueur;
	}

	public short getLargeur() {
		return largeur;
	}

	public boolean isAvailable(int x, int y) {
		return !cells[x][y];
	}

	public void freeCell(short x, short y) {
		cells[x][y] = false;
	}

	public void makeOccupied(short x, short y) {
		cells[x][y] = true;
	}
}

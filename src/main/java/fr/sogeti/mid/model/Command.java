package fr.sogeti.mid.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Command {

	private static Pelouse pelouse;
	private List<Tondeuse> tondeuses = new ArrayList<>();

	public static Command parseCommands(File fichier) throws FileNotFoundException {
		Command command = null;
		try {
			Scanner lecteur = new Scanner(fichier);
			command = new Command();
			String taillePelouse = lecteur.nextLine();
			String[] lignePelouse = taillePelouse.split(" ");
			command.setPelouse( new Pelouse(lignePelouse[0], lignePelouse[1]));
			int tondeuseCounter = 0;
			while (lecteur.hasNextLine()) {
				String commandeTondeuse = lecteur.nextLine();
				String[] ligneTondeuse = commandeTondeuse.split(" ");
				command.addTondeuse(new Tondeuse(ligneTondeuse[0], ligneTondeuse[1], ligneTondeuse[2]));
				String ligneInstruction = lecteur.nextLine();
				String[] instructions = ligneInstruction.split("");
				command.addInstructions(instructions, tondeuseCounter);
				tondeuseCounter++;
			}
			lecteur.close();
		} catch (NoSuchElementException e) {
			System.err.println("File is not well formatted");
		}
		return command;
	}

	public static Command runCommands(Command command) {
        for(Tondeuse t : command.tondeuses) {
			t.runTondeuse(pelouse);
		}
        return command;
    }

	private void addInstructions(String[] instructions, int tondeuseNumber) {
		tondeuses.get(tondeuseNumber).addInstructions(instructions);
	}

	public void addTondeuse(Tondeuse tondeuse) {
		tondeuses.add(tondeuse);
	}

	public void setPelouse(Pelouse pelouse) {
		this.pelouse = pelouse;
	}

	public short getPelouseLongueur() {
		return pelouse.getLongueur();
	}

	public short getPelouseLargeur() {
		return pelouse.getLargeur();
	}

	public List<Tondeuse> getTondeuses() {
		return tondeuses;
	}

	@Override
	public String toString() {
		return "{" +
				"tondeuses=" + tondeuses +
				'}';
	}
}

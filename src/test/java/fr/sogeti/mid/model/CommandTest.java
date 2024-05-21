package fr.sogeti.mid.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static fr.sogeti.mid.model.Direction.East;
import static fr.sogeti.mid.model.Direction.North;
import static fr.sogeti.mid.model.Instruction.AVANCER;
import static fr.sogeti.mid.model.Instruction.GAUCHE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

	@Test
	public void file_parseCommands_command() throws FileNotFoundException {
		File fichier = new File("files/test.txt");
		Command command = Command.parseCommands(fichier);
		assertEquals(5, command.getPelouseLongueur());
		assertEquals(5,command.getPelouseLargeur());
		assertEquals(1,command.getTondeuses().get(0).getX());
		assertEquals(2,command.getTondeuses().get(0).getY());
		assertEquals(North,command.getTondeuses().get(0).getDirection());
		assertEquals(List.of(GAUCHE, AVANCER, GAUCHE, AVANCER, GAUCHE, AVANCER, GAUCHE, AVANCER, AVANCER), command.getTondeuses().get(0).getInstructions());
		assertEquals(3, command.getTondeuses().get(1).getX());
		assertEquals(3, command.getTondeuses().get(1).getY());
		assertEquals(East, command.getTondeuses().get(1).getDirection());
	}

	@Test
	public void command_runTondeuse_command() {
		Command command = new Command();
		command.setPelouse(new Pelouse("5","5"));
		Tondeuse t1 = new Tondeuse("1", "2", "N");
		Tondeuse t2 = new Tondeuse("3", "3", "E");
		t1.addInstructions(new String[]{"G","A","G","A","G","A","G","A","A"});
		t2.addInstructions(new String[]{"A","A","D","A","A","D","A","D","D","A"});
		command.addTondeuse(t1);
		command.addTondeuse(t2);
		Command commandResults = Command.runCommands(command);
		assertEquals(1, commandResults.getTondeuses().get(0).getX());
		assertEquals(3, commandResults.getTondeuses().get(0).getY());
		assertEquals(North, commandResults.getTondeuses().get(0).getDirection());
		assertEquals(5, commandResults.getTondeuses().get(1).getX());
		assertEquals(1, commandResults.getTondeuses().get(1).getY());
		assertEquals(East, commandResults.getTondeuses().get(1).getDirection());
	}
}

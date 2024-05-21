package fr.sogeti.mid.runner;

import fr.sogeti.mid.model.Command;

import java.io.File;
import java.io.FileNotFoundException;

public class Runner {

    public static void main(String[] args) {
        final File file = new File("files/test.txt");
        try {
            Command command = Command.parseCommands(file);
            Command commandResults = Command.runCommands(command);
            System.out.print(commandResults);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

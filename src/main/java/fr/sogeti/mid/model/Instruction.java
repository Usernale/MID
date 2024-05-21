package fr.sogeti.mid.model;

public enum Instruction {


    DROITE("D"),
    GAUCHE("G"),
    AVANCER("A");

    private String instruction;

    Instruction(String instruction) {
        this.setInstruction(instruction);
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    public static Instruction fromValue(String instruction) {
        for (Instruction b : Instruction.values()) {
            if (b.instruction.equalsIgnoreCase(instruction)) {
                return b;
            }
        }
        throw new RuntimeException("Instruction "+instruction+" is not supported");
    }
}

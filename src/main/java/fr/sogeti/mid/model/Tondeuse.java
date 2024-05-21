package fr.sogeti.mid.model;

import java.util.ArrayList;
import java.util.List;

public class Tondeuse {

    private short x;
    private short y;
    private Direction direction;
    private List<Instruction> instructions = new ArrayList<>();

    public Tondeuse(String x, String y, String direction) {
        this.x = Short.parseShort(x);
        this.y = Short.parseShort(y);
        this.direction = Direction.fromValue(direction);
    }

    public short getX() {
        return this.x;
    }

    public short getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    private void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    public void addInstructions(String[] instructions) {
        for(String instruction : instructions) {
           this.instructions.add(Instruction.fromValue(instruction));
        }
    }

    void runTondeuse(Pelouse pelouse) {
        for(Instruction i : instructions) {
            moveTondeuse(i, pelouse);
        }
    }

    void moveTondeuse(Instruction i, Pelouse pelouse) {
         switch (i) {
            case AVANCER -> avancer(pelouse);
            case DROITE -> rotateRight();
            case GAUCHE -> rotateLeft();
        };
    }

    private void rotateLeft() {
        switch (direction) {
            case East -> this.setDirection(Direction.North);
            case South -> this.setDirection(Direction.East);
            case West -> this.setDirection(Direction.South);
            case North -> this.setDirection(Direction.West);
        }
    }

    private void rotateRight() {
        switch (direction) {
            case East -> this.setDirection(Direction.South);
            case South -> this.setDirection(Direction.West);
            case West -> this.setDirection(Direction.North);
            case North -> this.setDirection(Direction.East);
        }
    }

    private void avancer(Pelouse pelouse) {
        switch (direction) {
            case East -> moveEast(pelouse);
            case South -> moveSouth(pelouse);
            case West -> moveWest(pelouse);
            case North -> moveNorth(pelouse);
        }
    }

    private void moveNorth(Pelouse pelouse) {
        if(this.getY() < pelouse.getLongueur() && pelouse.isAvailable(this.x, this.y+1)){
            pelouse.freeCell(x,y);
            this.y++;
            pelouse.makeOccupied(x,y);
        }
    }

    private void moveWest(Pelouse pelouse) {
        if (this.getX() > 0 && pelouse.isAvailable(this.x-1, this.y)){
            pelouse.freeCell(x,y);
            this.x--;
            pelouse.makeOccupied(x,y);
        }
    }

    private void moveSouth(Pelouse pelouse) {
        if (this.getY() > 0 && pelouse.isAvailable(this.x, this.y-1)){
            pelouse.freeCell(x,y);
            this.y--;
            pelouse.makeOccupied(x,y);
        }
    }

    private void moveEast(Pelouse pelouse) {
        if (this.getX() < pelouse.getLargeur() && pelouse.isAvailable(this.x+1, this.y)){
            pelouse.freeCell(x,y);
            this.x++;
            pelouse.makeOccupied(x,y);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}

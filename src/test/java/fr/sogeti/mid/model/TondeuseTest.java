package fr.sogeti.mid.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static fr.sogeti.mid.model.Direction.*;
import static fr.sogeti.mid.model.Instruction.*;
import static org.junit.jupiter.api.Assertions.*;

class TondeuseTest {

    @Test
    void tondeuse_runTondeuse_tondeuse() {
        Tondeuse tondeuse = new Tondeuse("0","0", "E");
        tondeuse.addInstructions(new String[]{"D", "A", "D", "A", "D"});
        tondeuse.runTondeuse(new Pelouse("5","5"));
        assertEquals(0, tondeuse.getX());
        assertEquals(0,tondeuse.getY());
        assertEquals(North, tondeuse.getDirection());
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void tondeuse_moveTondeuse_tondeuse(Tondeuse t, Instruction inputInstruction, int x , int y, Direction direction ) {
        t.moveTondeuse(inputInstruction, new Pelouse("5","5"));
        assertEquals(x, t.getX());
        assertEquals(y,t.getY());
        assertEquals(direction, t.getDirection());
    }

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new Tondeuse("0","0", "E"), AVANCER, 1, 0 , East ),
                Arguments.of(new Tondeuse("0","0", "W"), AVANCER, 0, 0 , West ),
                Arguments.of(new Tondeuse("0","0", "S"), AVANCER, 0, 0 , South ),
                Arguments.of(new Tondeuse("0","0", "N"), AVANCER, 0, 1 , North ),
                Arguments.of(new Tondeuse("0","0", "E"), GAUCHE, 0, 0 , North),
                Arguments.of(new Tondeuse("0","0", "W"), GAUCHE, 0, 0 , South ),
                Arguments.of(new Tondeuse("0","0", "S"), GAUCHE, 0, 0 , East ),
                Arguments.of(new Tondeuse("0","0", "N"), GAUCHE, 0, 0 , West ),
                Arguments.of(new Tondeuse("0","0", "E"), DROITE, 0, 0 , South),
                Arguments.of(new Tondeuse("0","0", "W"), DROITE, 0, 0 , North ),
                Arguments.of(new Tondeuse("0","0", "S"), DROITE, 0, 0 , West ),
                Arguments.of(new Tondeuse("0","0", "N"), DROITE, 0, 0 , East )
        );
    }
}
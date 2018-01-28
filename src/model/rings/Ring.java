package model.rings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Ring {
    private final Colour COLOUR;

    Ring(Colour colour) {
        this.COLOUR = colour;
    }

    public Colour getColour() {
        return COLOUR;
    }

    public static Set<Ring> generateRingSet(Colour colour) {
        Set<Ring> rings = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Arrays.stream(Size.values()).map(size -> new SizeRing(colour, size)).forEach(rings::add);
            rings.add(new BaseRing(colour));
        }
        return rings;
    }
}

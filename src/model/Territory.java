package model;

import model.rings.BaseRing;
import model.rings.Colour;
import model.rings.Ring;
import model.rings.Size;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Territory {
    private final int X;
    private final int Y;
    private Map<Size, Ring> rings;

    public Territory(int x, int y) {
        this.X = x;
        this.Y = y;
        rings = new HashMap<>();
        Arrays.stream(Size.values()).forEach(size -> rings.put(size, null));
    }

    public Colour getWinner() {
        if (rings.get(Size.TINY) != null && rings.get(Size.TINY) instanceof BaseRing) {
            return null;
        }

        rings.values().stream().max() // TODO: write comparator.
    }
}

package model;

import exceptions.InvalidMoveException;
import model.rings.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Territory {
    private final int X;
    private final int Y;
    private Map<Size, Ring> rings;

    Territory(int x, int y) {
        this.X = x;
        this.Y = y;
        rings = new HashMap<>();
        Arrays.stream(Size.values()).forEach(size -> rings.put(size, null));
    }

    public Colour getWinner(Colour except) {
        if (rings.get(Size.TINY) != null && rings.get(Size.TINY) instanceof BaseRing) {
            return null;
        }

        Map<Colour, Long> ringCounts = rings.values().stream()
                .filter(Objects::nonNull)
                .map(Ring::getColour)
                .filter(colour -> colour != except)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Colour> mostFrequent = new ArrayList<>();
        int highest = 0;
        ringCounts.forEach((key, value) -> {
            if (value == highest) {
                mostFrequent.add(key);
            } else if (value > highest) {
                mostFrequent.clear();
                mostFrequent.add(key);
            }
        });

        if (mostFrequent.size() == 1) {
            return mostFrequent.get(0);
        }

        return null;
    }

    public void doMove(Move move) {
        Ring ring = move.getRing();
        if (ring instanceof BaseRing) {

        } else if (ring instanceof SizeRing) {

        } else {
            throw new InvalidMoveException("Ring is of wrong type: " + ring);
        }
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}

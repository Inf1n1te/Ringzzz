package model;

import exceptions.InvalidMoveException;
import exceptions.InvalidRingTypeException;
import model.rings.*;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Territory implements Serializable {
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

    public void placeRing(Ring ring) throws InvalidMoveException, InvalidRingTypeException {
        if (ring instanceof BaseRing) {
            if (rings.values().stream().allMatch(Objects::isNull)) rings.keySet().forEach(key -> rings.put(key, ring));
            else throw new InvalidMoveException("Cannot place a base ring if another ring is already present");
        } else if (ring instanceof SizeRing) {
            if (rings.get(((SizeRing) ring).getSize()) == null) rings.put(((SizeRing) ring).getSize(), ring);
            else
                throw new InvalidMoveException("Cannot place a ring if a ring of that size or a base ring is already present");
        } else {
            throw new InvalidRingTypeException("Ring is of wrong type: " + ring);
        }
    }

    public boolean isValidMove(Ring ring) throws InvalidRingTypeException {
        if (ring instanceof BaseRing) {
            return rings.values().stream().allMatch(Objects::isNull);
        } else if (ring instanceof SizeRing) {
            return rings.get(((SizeRing) ring).getSize()) == null;
        } else {
            throw new InvalidRingTypeException("Ring is of wrong type: " + ring);
        }
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}

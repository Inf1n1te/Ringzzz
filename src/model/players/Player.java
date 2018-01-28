package model.players;

import model.rings.Colour;
import model.rings.Ring;

import java.util.HashSet;
import java.util.Set;

public abstract class Player {
    private Set<Ring> rings;
    private Set<Colour> colours;

    public Player() {
        this.rings = new HashSet<>();
    }

    public void addRing(Ring ring) {
        this.rings.add(ring);
        if(ring.getColour() != Colour.ALL) colours.add(ring.getColour());
    }

    public void addRingSet(Set<Ring> rings) {
        this.rings.addAll(rings);
        rings.stream().map(Ring::getColour).forEach(colours::add);
    }

    public Set<Colour> getColours() {
        return colours;
    }
}

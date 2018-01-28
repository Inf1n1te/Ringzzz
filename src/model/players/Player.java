package model.players;

import model.rings.Ring;

import java.util.HashSet;
import java.util.Set;

public abstract class Player {
    private Set<Ring> rings;

    public Player() {
        this.rings = new HashSet<>();
    }

    public void addRing(Ring ring) {
        this.rings.add(ring);
    }

    public void addRingSet(Set<Ring> rings) {
        this.rings.addAll(rings);
    }
}

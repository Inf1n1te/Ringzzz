package model;

import model.rings.Ring;

public class Move {
    private final int x;
    private final int y;
    private final Ring ring;

    public Move(int x, int y, Ring ring) {
        this.x = x;
        this.y = y;
        this.ring = ring;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ring getRing() {
        return ring;
    }
}

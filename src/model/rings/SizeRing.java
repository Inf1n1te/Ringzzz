package model.rings;

public class SizeRing extends Ring {
    private final Size SIZE;

    public SizeRing(Colour colour, Size size) {
        super(colour);
        this.SIZE = size;
    }

    public Size getSize() {
        return SIZE;
    }
}

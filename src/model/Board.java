package model;

import exceptions.InvalidNumberOfPlayersException;
import model.players.Player;
import model.rings.Colour;
import model.rings.Ring;

import java.util.Arrays;
import java.util.Set;

public class Board {
    private final int DIM;
    private final Player[] players;
    private final Territory[][] territories;

    public Board(int dim, Player[] players) throws InvalidNumberOfPlayersException {
        // Initialize board
        this.DIM = dim;
        territories = new Territory[DIM][DIM];
        for (int x = 0; x < DIM; x++) {
            for (int y = 0; y < DIM; y++) {
                territories[x][y] = new Territory(x, y);
            }
        }

        // Set up players
        if (players.length < 2 || players.length > 4)
            throw new InvalidNumberOfPlayersException("Number of players must be between 2 and 4, input = " + players.length);
        this.players = players;
        giveRings(players);
    }

    private void giveRings(Player[] players) {
        players[0].addRingSet(Ring.generateRingSet(Colour.RED));
        players[1].addRingSet(Ring.generateRingSet(Colour.PURLPLE));
        if (players.length > 2) {
            players[2].addRingSet(Ring.generateRingSet(Colour.GREEN));
            if (players.length == 3) {
                Set<Ring> sharedRings = Ring.generateRingSet(Colour.YELLOW);
                Arrays.stream(players).forEach(player -> player.addRingSet(sharedRings));
            } else {
                players[3].addRingSet(Ring.generateRingSet(Colour.YELLOW));
            }
        } else {
            players[0].addRingSet(Ring.generateRingSet(Colour.GREEN));
            players[1].addRingSet(Ring.generateRingSet(Colour.YELLOW));
        }
    }

    private Territory getTerritories(int x, int y) {
        return territories[x][y];
    }

}

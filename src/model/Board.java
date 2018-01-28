package model;

import exceptions.InvalidNumberOfPlayersException;
import model.players.Player;
import model.rings.Colour;
import model.rings.Ring;
import model.rings.StartingRing;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {
    private final int DIM;
    private final Player[] players;
    private final Territory[][] territories;
    private Colour shared;
    private Map<Colour, Player> playerColours;

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
                this.shared = Colour.YELLOW;
            } else {
                players[3].addRingSet(Ring.generateRingSet(Colour.YELLOW));
            }
        } else {
            players[0].addRingSet(Ring.generateRingSet(Colour.GREEN));
            players[1].addRingSet(Ring.generateRingSet(Colour.YELLOW));
        }
        players[0].addRing(new StartingRing(Colour.ALL));
        playerColours = Arrays.stream(Colour.values())
                .filter(colour -> colour != Colour.ALL && colour != shared)
                .collect(Collectors.toMap(Function.identity(),
                        colour -> Arrays.stream(players)
                                .filter(player -> player.getColours()
                                        .contains(colour)).findFirst()
                                .orElse(null)));
    }

    public Map<Player, Long> getScore() {
        return Arrays.stream(territories)
                .flatMap(Arrays::stream)
                .map(territory -> territory.getWinner(shared))
                .filter(Objects::nonNull)
                .map(playerColours::get)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private Territory getTerritory(int x, int y) {
        return territories[x][y];
    }

}

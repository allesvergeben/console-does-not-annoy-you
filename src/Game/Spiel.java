package Game;

import UserInputs.IntInput;
import UserInputs.StringInput;

import java.util.ArrayList;

public class Spiel {
    private static ArrayList<Spieler> player;
    private static int playercount;

    public static void main(String[] args) {
        createPlayers();
        //System.out.println(new StringInput("Test: ", 15, true).get());
        //for (int i = 0; i <100; i++) System.out.println(new Wurfel().get());
        //System.out.println(new DoubleInput("Input a Double",1,10).get());
    }

    private static void createPlayers() {
        ArrayList<Spieler> Spieler = new ArrayList<>();
        playercount = new IntInput("Zu wie vielt soll gespielt werden", 2, 4).get();
        for (int i = 1; i <= playercount; i++) {
            Spieler.add(new Spieler(new StringInput("Bitte gib den Name des " + i + ". Spielers an.", 15, false).get()));
        }
        setPlayers(Spieler);
    }

    int getPlayercount() {
        return playercount;
    }

    ArrayList<Spieler> getPlayers() {
        return player;
    }

    private static void setPlayers(ArrayList<Spieler> p_spieler) {
        player = p_spieler;
    }

}

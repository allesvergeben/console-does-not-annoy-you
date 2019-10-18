import UserInputs.DoubleInput;
import UserInputs.IntInput;
import UserInputs.StringInput;

import java.util.ArrayList;

public class Spiel {
    private static ArrayList<Spieler> Spieler;
    private static int Spieleranzahl;

    public static void main(String[] args) {
        createPlayers();
        //System.out.println(new DoubleInput("Input a Double",1,10).get());
    }

    private static void createPlayers() {
        ArrayList<Spieler> Spieler = new ArrayList<>();
        Spieleranzahl = new IntInput("Zu wie vielt soll gespielt werden", 1, 4).get();
        for (int i = 1; i <= Spieleranzahl; i++) {
            Spieler.add(new Spieler(new StringInput("Bitte gib den Name des " + i + ". Spielers an.", 15).get()));
        }
        setSpieler(Spieler);
    }

    private static void setSpieler(ArrayList<Spieler> p_spieler) {
        Spieler = p_spieler;
    }

    public int getSpieleranzahl() {
        return Spieleranzahl;
    }

    ArrayList<Spieler> getPlayers() {
        return Spieler;
    }

}

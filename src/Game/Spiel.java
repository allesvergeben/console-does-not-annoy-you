package Game;

import Graphics.Display;
import UserInputs.IntInput;
import UserInputs.StringInput;
import UserOutputs.PlayerOutput;

import java.util.ArrayList;

public class Spiel {
    private static ArrayList<Spieler> player;
    private static int playercount;
    private static boolean gameruns;
    private static boolean hack;
    private static boolean pc;

    public static void main(String[] args) {
        gameruns = true;
        createPlayers();
        Spielfeld.setWin();
        Spielfeld.setStart();
        setWinspots();
        main(); //token ring system
    }

    private static void createPlayers() {
        ArrayList<Spieler> Spieler = new ArrayList<>();
        playercount = new IntInput("Zu wie vielt soll gespielt werden", 2, 4).get();
        for (int i = 1; i <= playercount; i++) {
            Spieler.add(new Spieler(new StringInput("Bitte gib den Name des " + i + ". Spielers an.", 15).get(), i, hack, pc));
        }
        setPlayers(Spieler);
    }

    private static void main() {
        while (gameruns) {
            for (Spieler spieler : player) {
                new PlayerOutput().turn(spieler.getName(), spieler.getId());
                spieler.move(false);
                if (spieler.getPc() && gameruns) {
                    new Display();
                }
            }
        }
        new Display();
    }

    public static int getPlayercount() {
        return playercount;
    }

    static void stopGame() {
        gameruns = false;
    }

    private static void setPlayers(ArrayList<Spieler> p_spieler) {
        player = p_spieler;
    }

    public static Spieler getPlayer(int p_index) {
        return player.get(p_index);
    }

    private static void setWinspots() {
        for (int i = 0; i < playercount; i++) {
            Spielfeld.getWinspot().add(new ArrayList<>());
        }
    }

    public static void setHack(boolean p_hack) {
        hack = p_hack;
    }

    public static void setPc(boolean p_pc) {
        pc = p_pc;
    }
}
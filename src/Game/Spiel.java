package Game;

import Graphics.Display;
import UserInputs.IntInput;
import UserInputs.StringInput;

import java.util.ArrayList;

public class Spiel {
    private static ArrayList<Spieler> player;
    private static int playercount;
    private static boolean gameruns = true;
    private static boolean hack;
    private static boolean pc;

    public static void main(String[] args) {
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
                System.out.println(spieler.getName() + " (P" + spieler.getId() + ") ist jetzt dran.");
                spieler.move(false);
                if (spieler.getPc()) {
                    new Display();
                }
            }
        }
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

    static String getStaticNameById(int p_id) {
        return player.get(p_id).getName();
    }

    public static Spieler getPlayer(int p_index) {
        return player.get(p_index);
    }

    private static void setWinspots() {
        ArrayList<Boolean> winlist = new ArrayList<>();
        for (int i = 0; i < playercount; i++) {
            Spielfeld.getWinspot().add(i, winlist);
        }
    }

    public static void setHack(boolean p_hack) {
        hack = p_hack;
    }

    public static void setPc(boolean p_pc) {
        pc = p_pc;
    }
}
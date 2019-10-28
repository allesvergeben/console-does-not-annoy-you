package Game;

import java.util.ArrayList;

public class Spielfeld extends Spiel {
    private int[][] Spielfeld = new int[40][2];
    private ArrayList<ArrayList<Boolean>> winspot;
    private int[] start = new int[4];
    private int[] home = new int[4];

    Spielfeld() {
        setHome();
        setStart();
    }

    public boolean setSpielfeld(int p_position, int p_figurID, int p_spieler) {
        if (Spielfeld[p_position][0] == 0) {
            Spielfeld[p_position][0] = p_spieler;
            Spielfeld[p_position][1] = p_figurID;
            return true;
        } else {
            if (Spielfeld[p_position][0] == p_spieler) {
                return false;
            } else {
                Spielfeld[p_position][0] = p_spieler;
                Spielfeld[p_position][1] = p_figurID;
                return true;
            }
        }
    }

    private void setHome() {
        home[0] = 1;
        home[1] = 1;
        home[2] = 1;
        home[3] = 1;
    }

    private void setStart() {
        start[0] = 1;
        start[1] = 1;
        start[2] = 1;
        start[3] = 1;
    }

    int getHome(int p_spieler) {
        return home[p_spieler];
    }

    int getStart(int p_spieler) {
        return start[p_spieler];
    }

    public void setWinspot(int p_spieler) {
        winspot.get(p_spieler).set(winspot.get(p_spieler).size(), true);
        if (winspot.get(p_spieler).size() == 4) {
            stopGame();
            System.out.println("Spieler " + getNameById(p_spieler) + " hat gewonnen");
        }
    }
}


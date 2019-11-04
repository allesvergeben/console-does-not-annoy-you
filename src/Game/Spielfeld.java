package Game;

import java.util.ArrayList;

public class Spielfeld extends Spiel {
    private static int[][] Spielfeld = new int[40][2];
    private static ArrayList<ArrayList<Boolean>> winspot = new ArrayList<>();
    private static int[] start = new int[4];
    private static int[] home = new int[4];

    Spielfeld() {
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

    static void setHome() {
        home[0] = 39;
        home[1] = 9;
        home[2] = 19;
        home[3] = 29;
    }

    static void setWin() {
        start[0] = 0;
        start[1] = 10;
        start[2] = 20;
        start[3] = 30;
    }

    static int getHome(int p_spieler) {
        return home[p_spieler];
    }

    private static int getWin(int p_spieler) {
        return start[p_spieler];
    }

    static boolean setFeld(int p_Spieler, int p_Wurf, int p_FigurID) {
        int Feld = p_Wurf + getPlayer(p_Spieler - 1).getFigures().get(p_FigurID).getFeld();
        if (Feld >= 40) {
            Feld = Feld - 39; // not tested
            getPlayer(p_Spieler - 1).getFigures().get(p_FigurID).setRound(true);

        }
        if (Spielfeld[Feld][0] == p_Spieler) {
            System.out.println("Hier ist bereits eine Figur von dir");
            return false;
        } else if (Spielfeld[Feld][0] != p_Spieler && Spielfeld[Feld][0] != 0) {
            //Figur löschen (scheißen)
            getPlayer(Spielfeld[Feld][0] - 1).getFigures().remove(Spielfeld[Feld][1]); //GEWORFEN!
            //Spielfeld wo die figur vorher drauf war leeren
            switchFig(p_Spieler - 1, p_FigurID, Feld);
        } else {
            switchFig(p_Spieler - 1, p_FigurID, Feld);
        }
        return true;
    }

    private static void switchFig(int p_Spieler, int p_FigurID, int p_Feld) {
        //Altes Feld leeren
        Spielfeld[getPlayer(p_Spieler).getFigures().get(p_FigurID).getFeld()][0] = 0;
        Spielfeld[getPlayer(p_Spieler).getFigures().get(p_FigurID).getFeld()][1] = 0;
        //Figur Feld Aktualisieren
        if (getWin(p_Spieler) > p_Feld && p_Spieler == 0) { // Für Spieler 1
            //entsacken!
            getPlayer(p_Spieler).getFigures().remove(p_FigurID);
            setWinspot(p_Spieler);
        } else if (getPlayer(p_Spieler).getFigures().get(p_FigurID).getRound() && getWin(p_Spieler) < p_Feld && p_Spieler != 0) {
            //entsacken!
            getPlayer(p_Spieler).getFigures().remove(p_FigurID);
            setWinspot(p_Spieler);
        } else {
            getPlayer(p_Spieler).getFigures().get(p_FigurID).setFeld(p_Feld);
            //Figur auf das Spielfeld setzen
            Spielfeld[getPlayer(p_Spieler).getFigures().get(p_FigurID).getFeld()][0] = p_Spieler + 1;
            Spielfeld[getPlayer(p_Spieler).getFigures().get(p_FigurID).getFeld()][1] = p_FigurID;
        }
    }

    static ArrayList<ArrayList<Boolean>> getWinspot() {
        return winspot;
    }

    public static void setWinspot(int p_spieler) {
        winspot.get(p_spieler).add(true);
        if (winspot.get(p_spieler).size() == 4) {
            stopGame();
            System.out.println("Spieler " + getStaticNameById(p_spieler) + " hat gewonnen");
        }
    }
}


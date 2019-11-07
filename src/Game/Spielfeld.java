package Game;

import java.util.ArrayList;

public class Spielfeld extends Spiel {
    private static int[][] Spielfeld = new int[40][2];
    private static ArrayList<ArrayList<Boolean>> winspot = new ArrayList<>();
    private static int[] start = new int[4];
    private static int[] win = new int[4];

    static void setWin() {
        win[0] = 39;
        win[1] = 9;
        win[2] = 19;
        win[3] = 29;
    }

    static void setStart() {
        start[0] = 0;
        start[1] = 10;
        start[2] = 20;
        start[3] = 30;
    }

    static int getStart(int p_spieler) {
        return start[p_spieler];
    }

    private static int getWin(int p_spieler) {
        return win[p_spieler];
    }

    static boolean setFeld(int p_Spieler, int p_Wurf, int p_FigurID) {
        int Feld;
        try {
            Feld = p_Wurf + getPlayer(p_Spieler - 1).getFigures().get(p_FigurID).getFeld();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error Spieler: " + (p_Spieler - 1));
            System.out.println("Error FID: " + p_FigurID);
            System.out.println("Error Wurf: " + p_Wurf);
            System.out.println("Error Figur Size" + getPlayer(p_Spieler - 1).getFigures().size());
            //secureCheck
            return false;
        }
        if (Feld >= 40) {
            Feld = Feld - 39;
            getPlayer(p_Spieler - 1).getFigures().get(p_FigurID).setRound(true);

        }
        if (Spielfeld[Feld][0] == p_Spieler) {
            System.out.println("Hier ist bereits eine Figur von dir");
            return false;
        } else if (Spielfeld[Feld][0] != p_Spieler && Spielfeld[Feld][0] != 0) {
            //Figur löschen (schmeißen)

            getPlayer(Spielfeld[Feld][0] - 1).getFigures().remove(Spielfeld[Feld][1]); //GEWORFEN!

            System.out.println(getPlayer(p_Spieler - 1).getName() + " hat von " + getPlayer(Spielfeld[Feld][0] - 1).getName() + " die Figur mit der ID " + Spielfeld[Feld][1] + " rausgeworfen!");
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
        if (getWin(p_Spieler) < p_Feld && p_Spieler == 0) { //Win Für Spieler 1
            //entsacken!
            getPlayer(p_Spieler).getFigures().remove(p_FigurID);
            setWinspot(p_Spieler);
        } else if (getPlayer(p_Spieler).getFigures().get(p_FigurID).getRound() && getWin(p_Spieler) < p_Feld && p_Spieler != 0) {
            //entsacken!
            getPlayer(p_Spieler).getFigures().remove(p_FigurID);
            setWinspot(p_Spieler);
        } else {
            //Figurfeld auf das p_Feld setzen
            getPlayer(p_Spieler).getFigures().get(p_FigurID).setFeld(p_Feld);
            Spielfeld[getPlayer(p_Spieler).getFigures().get(p_FigurID).getFeld()][0] = p_Spieler + 1;
            Spielfeld[getPlayer(p_Spieler).getFigures().get(p_FigurID).getFeld()][1] = p_FigurID;
        }
    }

    public static ArrayList<ArrayList<Boolean>> getWinspot() {
        return winspot;
    }

    private static void setWinspot(int p_spieler) {
        winspot.get(p_spieler).add(true);
        if (winspot.get(p_spieler).size() == 4) {
            stopGame();
            System.out.println("Spieler " + getStaticNameById(p_spieler) + " hat gewonnen");
        }
    }

    public static int[][] getSpielfeld() {
        return Spielfeld;
    }
}
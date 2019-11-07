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

    static boolean setFeld(int p_Spieler, int p_Wurf, int p_FigurHash) {
        int Feld;
        try {
            Feld = p_Wurf + getPlayer(p_Spieler - 1).getFigurByHash(p_FigurHash).getFeld();
        } catch (Exception e) {
            System.out.println("Error Spieler: " + (p_Spieler - 1));
            System.out.println("Error FID: " + p_FigurHash);
            System.out.println("Error Wurf: " + p_Wurf);
            System.out.println("Error Figur Size" + getPlayer(p_Spieler - 1).getFigures().size());
            //secureCheck
            return false;
        }
        if (Feld >= 40) {
            Feld = Feld - 39;
            getPlayer(p_Spieler - 1).getFigurByHash(p_FigurHash).setRound(true);

        }
        if (Spielfeld[Feld][0] == p_Spieler) {
            System.out.println("Hier ist bereits eine Figur von dir");
            return false;
        } else if (Spielfeld[Feld][0] != p_Spieler && Spielfeld[Feld][0] != 0) {
            //Figur schmeißen
            System.out.println(getPlayer(p_Spieler - 1).getName() + " hat von " + getPlayer(Spielfeld[Feld][0] - 1).getName() + " die Figur mit der ID " + getPlayer(Spielfeld[Feld][0] - 1).getFigurIndexByHash(Spielfeld[Feld][1]) + " rausgeworfen!");
            getPlayer(Spielfeld[Feld][0] - 1).getFigures().remove(getPlayer(Spielfeld[Feld][0] - 1).getFigurIndexByHash(Spielfeld[Feld][1])); //GEWORFEN!
            switchFig(p_Spieler - 1, p_FigurHash, Feld);
        } else {
            switchFig(p_Spieler - 1, p_FigurHash, Feld);
        }
        return true;
    }

    private static void switchFig(int p_Spieler, int p_FigurHash, int p_Feld) {
        //Altes Feld leeren
        Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][0] = 0;
        Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][1] = 0;
        //Figur Feld Aktualisieren
        if (getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getRound() && getWin(p_Spieler) < p_Feld) {
            // To-Do Entsacken
            getPlayer(p_Spieler).getFigures().remove(getPlayer(p_Spieler).getFigurIndexByHash(p_FigurHash));
            setWinspot(p_Spieler);
        } else {
            //Figurfeld auf das p_Feld setzen
            getPlayer(p_Spieler).getFigurByHash(p_FigurHash).setFeld(p_Feld);
            Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][0] = p_Spieler + 1;
            Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][1] = p_FigurHash;
        }
    }

    public static ArrayList<ArrayList<Boolean>> getWinspot() {
        return winspot;
    }

    private static void setWinspot(int p_spieler) {
        System.out.println("Spieler " + getPlayer(p_spieler).getName() + " ist mit einer weiteren Figur ins Ziel gekommen");
        winspot.get(p_spieler).add(true);
        if (winspot.get(p_spieler).size() == 4) {
            System.out.println("Spieler " + getStaticNameById(p_spieler) + " hat gewonnen");
            stopGame();
        }
    }

    public static int[][] getSpielfeld() {
        return Spielfeld;
    }
}
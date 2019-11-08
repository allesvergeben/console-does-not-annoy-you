package Game;

import UserOutputs.PlayerOutput;

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
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            //Sollte nicht mehr auftreten…
            return false;
        }
        if (Feld >= 40) {
            Feld = Feld - 39;
            getPlayer(p_Spieler - 1).getFigurByHash(p_FigurHash).setRound(true);
        }
        if (!gohome(p_Spieler - 1, p_FigurHash, Feld)) {
            //vorher checken ob sie nicht in den Winspot kommt
            if (Spielfeld[Feld][0] == p_Spieler) {
                new PlayerOutput().blockedbyown();
                return false;
            } else if (Spielfeld[Feld][0] != p_Spieler && Spielfeld[Feld][0] != 0) {

                //Figur schmeißen
                int kickout = getPlayer(Spielfeld[Feld][0] - 1).getFigurIndexByHash(Spielfeld[Feld][1]);
                new PlayerOutput().kicked(getPlayer(p_Spieler - 1).getName(), getPlayer(Spielfeld[Feld][0] - 1).getName(), getPlayer(p_Spieler - 1).getId(), Spielfeld[Feld][0], kickout);

                getPlayer(Spielfeld[Feld][0] - 1).getFigures().remove(kickout); //GEWORFEN!
                switchFig(p_Spieler - 1, p_FigurHash, Feld);
            } else {
                switchFig(p_Spieler - 1, p_FigurHash, Feld);
            }
        }
        return true;
    }

    private static void switchFig(int p_Spieler, int p_FigurHash, int p_Feld) {
        //Altes Feld leeren
        Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][0] = 0;
        Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][1] = 0;
        //Figur Feld Aktualisieren
        getPlayer(p_Spieler).getFigurByHash(p_FigurHash).setFeld(p_Feld);
        //Figurfeld auf das p_Feld setzen
        Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][0] = p_Spieler + 1;
        Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][1] = p_FigurHash;
    }

    private static boolean gohome(int p_Spieler, int p_FigurHash, int p_Feld) {

        if (getPlayer(p_Spieler).getFigures().size() != 0) {
            if (getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getRound() && getWin(p_Spieler) < p_Feld) {
                //To-Do Entsackem
                //Altes Feld leeren
                Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][0] = 0;
                Spielfeld[getPlayer(p_Spieler).getFigurByHash(p_FigurHash).getFeld()][1] = 0;
                //Figur löschen
                getPlayer(p_Spieler).getFigures().remove(getPlayer(p_Spieler).getFigurIndexByHash(p_FigurHash));
                //winspot+1
                setWinspot(p_Spieler);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static ArrayList<ArrayList<Boolean>> getWinspot() {
        return winspot;
    }

    private static void setWinspot(int p_spieler) {
        new PlayerOutput().nextinWin(getPlayer(p_spieler).getName());
        winspot.get(p_spieler).add(true);
        if (winspot.get(p_spieler).size() == 4) {
            new PlayerOutput().won(getPlayer(p_spieler).getName(), getPlayer(p_spieler).getId());
            stopGame();
        }
    }

    public static int[][] getSpielfeld() {
        return Spielfeld;
    }
}
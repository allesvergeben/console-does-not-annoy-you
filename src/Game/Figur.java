package Game;

public class Figur {
    private int Feld = 0;

    Figur(int aktuellesFeld) {
        this.Feld = aktuellesFeld;
    }

    public int getFeld() {
        return Feld;
    }

}

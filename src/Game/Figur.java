package Game;

class Figur {
    private int Feld;
    private int ID;
    private boolean round = false;

    Figur(int p_home, int p_id) {
        this.Feld = p_home;
        this.ID = p_id;
        this.ID = hashCode();
    }

    int getFeld() {
        return Feld;
    }

    void setFeld(int p_Feld) {
        this.Feld = p_Feld;
    }

    int getID() {
        return ID;
    }

    boolean getRound() {
        return round;
    }

    @Override
    public int hashCode() {
        int result = ID ^ (ID >>> 32);
        result = 31 * result + (Math.random() * 9 + "" + Math.random() * 9).hashCode();
        result = 31 * result + (Math.random() * 9 + "" + Math.random() * 9).hashCode();
        return result;
    }
    void setRound(boolean p_round) {
        this.round = p_round;
    }
}
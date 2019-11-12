package Game;

class Figur {
    private int Feld;
    private int hash;
    private int id;
    private boolean round = false;


    Figur(int p_home, int p_id) {
        this.Feld = p_home;
        this.id = p_id;
        this.hash = hashCode();
    }

    int getFeld() {
        return Feld;
    }

    void setFeld(int p_Feld) {
        this.Feld = p_Feld;
    }

    int getHash() {
        return hash;
    }

    boolean getRound() {
        return round;
    }

    void setRound(boolean p_round) {
        this.round = p_round;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result = 31 * result + (Math.random() * 9 + "" + Math.random() * 9).hashCode();
        result = 31 * result + (Math.random() * 9 + "" + Math.random() * 9).hashCode();
        return result;
    }

}
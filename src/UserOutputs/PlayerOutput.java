package UserOutputs;

public class PlayerOutput {
    public PlayerOutput() {

    }

    public void rolled(int p_rolled) {
        System.out.println("Du hast eine " + p_rolled + " gewürfelt.");
    }

    public void turn(String p_name, int p_id) {
        System.out.println(p_name + " (P" + p_id + ") ist jetzt dran.");
    }

    public void out() {
        System.out.println("Eine neue Figur ist jetzt auf dem Spielfeld");
    }

    public void nextinWin(String p_Name) {
        System.out.println("Spieler " + p_Name + " ist mit einer weiteren Figur ins Ziel gekommen");
    }

    public void won(String p_player, int p_ID) {
        System.out.println("Spieler " + p_player + "(ID: " + p_ID + ") hat gewonnen");
    }

    public void blockedbyown() {
        System.out.println("Hier ist bereits eine Figur von dir");
    }

    public void kicked(String p_name, String p_namek, int p_id, int p_idk, int p_FigurID) {
        System.out.println(p_name + " (ID: " + p_id + ") hat von " + p_namek + " (ID: " + p_idk + ") die Figur mit der ID " + p_FigurID + " rausgeworfen!");
    }
}

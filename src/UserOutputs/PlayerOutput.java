package UserOutputs;

import Graphics.Colors;
import UserInputs.IntInput;

import java.util.ArrayList;

public class PlayerOutput {
    public PlayerOutput() {

    }

    public void rolled(int p_rolled) {
        System.out.println("Du hast eine " + p_rolled + " gewürfelt.");
    }

    public void turn(String p_name, int p_id) {
        System.out.println(p_name + " " + Colors.color(p_id, "(P", ")") + " ist jetzt dran.");
    }

    public void out() {
        System.out.println("Eine neue Figur ist jetzt auf dem Spielfeld");
    }

    public void nextinWin(String p_Name) {
        System.out.println("Spieler " + p_Name + " ist mit einer weiteren Figur ins Ziel gekommen");
    }

    public void won(String p_player, int p_ID) {
        System.out.println("Spieler " + p_player + Colors.color(p_ID, "(ID: ", ")") + " hat " + Colors.GREEN + "gewonnen" + Colors.RESET);
    }

    public void blockedbyown() {
        System.out.println("Hier ist bereits eine Figur von dir");
    }

    public void kicked(String p_name, String p_namek, int p_id, int p_idk, int p_FigurID) {
        System.out.println(p_name + Colors.color(p_id, " (ID: ", ")") + " hat von " + p_namek + " " + Colors.color(p_idk, "(ID: ", ")") + " die Figur mit der ID " + p_FigurID + " rausgeworfen!");
    }

    public void homemove(int p_from, int p_to) {
        System.out.println("Im Winspot wurde eine Figur von Position " + p_from + " nach " + p_to + " bewegt");
    }

    public int askhome(ArrayList<int[]> p_choices) {
        return new IntInput("Willst du vieleicht deine Zielpositionen etwas aufräumen?\n Wenn nicht, geb einfach -1 ein ;-)", -1, p_choices.size()).get();
    }
}

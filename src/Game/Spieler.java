package Game;

import Graphics.Display;
import UserInputs.IntInput;

import java.util.ArrayList;

public class Spieler {
    /*
- colors: Color[]
     */

    private ArrayList<Figur> figures = new ArrayList<>();
    private String name;
    private int id;

    Spieler(String p_name, int p_id) {
        this.name = p_name;
        this.id = p_id;
    }

    void move(boolean gotout) {
        int w;
        if (gotout) {
            w = new Wurfel().get();
            if (!Spielfeld.setFeld(id, w, figures.get(figures.size() - 1).getID())) {
                askedmove(w);
            }
        } else {
            if (figures.size() == 0) {
                for (int i = 0; i < 3; i++) {
                    w = new Wurfel().get();
                    outputWurf(w);
                    if (w == 6) {
                        figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                        i = 4;
                        move(true);
                    }
                }
            } else {
                w = new Wurfel().get();
                if (w == 6) {
                    if (figures.size() == 4) {
                        askedmove(w);
                    } else {
                        outputWurf(w);
                        figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                        move(true);
                    }
                } else {
                    askedmove(w);
                }
            }
        }
    }

    public ArrayList<Figur> getFigures() {
        return figures;
    }

    String getName() {
        return name;
    }

    private void outputWurf(int w) {
        System.out.println("Du hast eine " + w + " gewürfelt.");
    }

    private void askedmove(int p_w) {
        new Display();
        outputWurf(p_w);
        while (!Spielfeld.setFeld(id, p_w, new IntInput("Mit Welcher Figur willst du gehen?\n" + "Du kannst von Figur " + 0 + " bis Figur " + (getFigures().size() - 1) + " wählen", 0, getFigures().size()).get()))
            ;
    }

    int getId() {
        return id;
    }
}
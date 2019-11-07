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
    private boolean hack;
    private boolean pc;

    Spieler(String p_name, int p_id, boolean p_hack, boolean p_pc) {
        this.name = p_name;
        this.id = p_id;
        this.hack = p_hack;
        this.pc = p_pc;
    }

    void move(boolean p_gotout) {
        if (pc) {
            PC(p_gotout);
        } else {
            int w;
            if (p_gotout) {
                w = new Wurfel().get(hack);
                if (!Spielfeld.setFeld(id, w, figures.get(figures.size() - 1).getID())) {
                    askedmove(w);
                } else {
                    System.out.println("Du bist mit einer Figur rausgekommen und hast eine " + w + " gewürfelt");
                }
            } else {
                if (figures.size() == 0) {
                    for (int i = 0; i < 3; i++) {
                        w = new Wurfel().get(hack);
                        outputWurf(w);
                        if (w == 6) {
                            figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                            i = 4;
                            move(true);
                        }
                    }
                } else {
                    w = new Wurfel().get(hack);
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
    }

    public ArrayList<Figur> getFigures() {
        return figures;
    }

    String getName() {
        return name;
    }

    private void outputWurf(int p_w) {
        System.out.println("Du hast eine " + p_w + " gewürfelt.");
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

    private void PC(boolean p_gotout) {
        int w;
        if (p_gotout) {
            w = new Wurfel().get(false);
            if (!Spielfeld.setFeld(id, w, figures.get(figures.size() - 1).getID())) {
                Spielfeld.setFeld(id, w, (int) (Math.random() * getFigures().size()));
            }
        } else {
            if (figures.size() == 0) {
                for (int i = 0; i < 3; i++) {
                    w = new Wurfel().get(false);
                    Spielfeld.setFeld(id, w, (int) (Math.random() * getFigures().size() - 1));
                    if (w == 6) {
                        figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                        i = 4;
                        move(true);
                    }
                }
            } else {
                w = new Wurfel().get(false);
                if (w == 6) {
                    if (figures.size() == 4) {
                        Spielfeld.setFeld(id, w, (int) (Math.random() * getFigures().size()));
                    } else {
                        figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                        move(true);
                    }
                } else {
                    Spielfeld.setFeld(id, w, (int) (Math.random() * getFigures().size()));
                }
            }
        }
    }
}
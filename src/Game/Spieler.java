package Game;

import Graphics.Display;
import UserInputs.IntInput;
import UserOutputs.PcOutput;
import UserOutputs.PlayerOutput;

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
                if (!Spielfeld.setFeld(id, w, figures.get(figures.size() - 1).getHash())) {
                    askedmove(w);
                } else {
                    new PlayerOutput().out();
                }
            } else {
                if (figures.size() == 0 && Spielfeld.getWinspot().get(id - 1).size() != 4) {
                    for (int i = 0; i < 3; i++) {
                        w = new Wurfel().get(hack);
                        new PlayerOutput().rolled(w);
                        if (w == 6) {
                            figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                            Spielfeld.setFeld(id - 1, Spielfeld.getStart(id - 1), figures.get(getFigures().size() - 1).getHash());
                            i = 4;
                            move(true);
                        }
                    }
                } else {
                    w = new Wurfel().get(hack);
                    if (w == 6) {
                        if (figures.size() + Spielfeld.getWinspot().get(id - 1).size() >= 4) {
                            askedmove(w);
                        } else {
                            new PlayerOutput().rolled(w);
                            figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                            Spielfeld.setFeld(id - 1, Spielfeld.getStart(id - 1), figures.get(getFigures().size() - 1).getHash());
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

    private void askedmove(int p_w) {
        new Display();
        new PlayerOutput().rolled(p_w);
        if (checkInHomeWalk(p_w)) {
            //wenn er bock hat
        } else {
            while (!Spielfeld.setFeld(id, p_w, figures.get(new IntInput("Mit Welcher Figur willst du gehen?\n" + "Du kannst von Figur " + 0 + " bis Figur " + (getFigures().size() - 1) + " wählen", 0, getFigures().size() - 1).get()).getHash())) {
                //just do it!
            }
        }
    }

    int getId() {
        return id;
    }

    Figur getFigurByHash(int p_hash) {
        for (Figur Figur : figures) {
            if (Figur.getHash() == p_hash) {
                return Figur;
            }
        }
        return null;
    }

    boolean getPc() {
        return pc;
    }

    private void PC(boolean p_gotout) {
        int w;
        if (p_gotout) {
            w = new Wurfel().get(false);
            new PcOutput().rolled(getName(), getId(), w);
            if (!Spielfeld.setFeld(id, w, figures.get(figures.size() - 1).getHash())) {
                int choosefigure = (int) (Math.random() * getFigures().size() - 1);
                new PcOutput().moved(getName(), getId(), choosefigure);
                Spielfeld.setFeld(id, w, getFigures().get(choosefigure).getHash());
            } else {
                new PcOutput().out();
            }
        } else {
            if (figures.size() == 0 && Spielfeld.getWinspot().get(id - 1).size() != 4) {
                for (int i = 0; i < 3; i++) {
                    w = new Wurfel().get(false);
                    if (w == 6) {
                        figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                        i = 4;
                        move(true);
                    }
                }
            } else {
                w = new Wurfel().get(false);
                new PcOutput().rolled(getName(), getId(), w);
                if (w == 6) {
                    if (figures.size() + Spielfeld.getWinspot().get(id - 1).size() >= 4) {
                        Spielfeld.setFeld(id, w, (int) (Math.random() * getFigures().size()));
                    } else {
                        figures.add(new Figur(Spielfeld.getStart(id - 1), figures.size()));
                        move(true);
                    }
                } else {
                    Spielfeld.setFeld(id, w, getFigures().get((int) (Math.random() * getFigures().size())).getHash());
                }
            }
        }
    }

    public int getFigurIndexByHash(int p_hash) {
        //System.out.println("Gesucht wird nach " + p_hash);
        for (int i = 0; i < figures.size(); i++) {
            //System.out.println(i + " hat den hash " + figures.get(i).getHash());
            if (figures.get(i).getHash() == p_hash) {
                //System.out.println("WORKED on " + i);
                return i;
            }
        }
        //System.out.println("Error: Could not find the matching hash!");
        return -1;
    }

    boolean checkInHomeWalk(int p_w) {
        if (Spielfeld.getWinspot().get(id).size() > 0) {
            ArrayList<int[]> choices = new ArrayList<>();
            switch (p_w) {
                case 1:
                    for (int i = 0; i < 3; i++) {
                        if (Spielfeld.getWinspot().get(id).get(i) == Spielfeld.getWinspot().get(id).get(i + 1)) {
                            choices.add(new int[]{i, i + 1});
                        }
                    }
                    return askedHomeMove(choices);
                //check 0 auf 1, 1 auf 2, 2 auf 3
                case 2:
                    for (int i = 0; i < 1; i++) {
                        if (Spielfeld.getWinspot().get(id).get(i) == Spielfeld.getWinspot().get(id).get(i + 2) && Spielfeld.getWinspot().get(id).get(i + 1) == null) {
                            choices.add(new int[]{i, i + 1});
                        }
                    }
                    return askedHomeMove(choices);
                //0 auf 2, 1 auf 3
                case 3:
                    if (Spielfeld.getWinspot().get(id).get(0) != null) {
                        for (int i = 1; i < 3; i++) {
                            if (Spielfeld.getWinspot().get(id).get(0) != null) {
                                return false;
                            }
                        }
                        choices.add(new int[]{0, 3});
                    }
                    return askedHomeMove(choices);
                //check 0 auf 3 und dazeischen
                default:
                    return false;
            }
        }
        return false;

    }

    boolean letsgoin(int p_w) {
        switch (p_w) {
            case 1:
                if (Spielfeld.getWinspot().get(id).get(0) == null) {
                    Spielfeld.setWinspot(0, id);
                    return true;
                }
                return false;
            //check ob auf Slot 0 eine figur steht
            case 2:
                if (Spielfeld.getWinspot().get(id).get(0) == null && Spielfeld.getWinspot().get(id).get(1) == null) {
                    Spielfeld.setWinspot(1, id);
                    return true;
                }
                return false;
            //0 und 1 etwas liegt
            case 3:
                for (int i = 0; i < 3; i++) {
                    if (Spielfeld.getWinspot().get(id).get(0) != null) {
                        return false;
                    }
                }
                Spielfeld.setWinspot(2, id);
                return true;
            //checkt ob 0-2 leer ist
            case 4:
                for (int i = 0; i < 4; i++) {
                    if (Spielfeld.getWinspot().get(id).get(0) != null) {
                        return false;
                    }
                }
                Spielfeld.setWinspot(3, id);
                return true;
            //check ob alle leer sind
            default:
                //wenn Wurfanzahl zu hoch um reinzukommen
                return false;
        }
    }

    private boolean askedHomeMove(ArrayList<int[]> p_choices) {
        new Display();
        int choice = new PlayerOutput().askhome(p_choices);
        if (p_choices.get(choice)[0] == -1) {
            return false;
        } else {
            Spielfeld.getWinspot().get(id).remove(p_choices.get(choice)[0]);
            Spielfeld.getWinspot().get(id).add(p_choices.get(choice)[1], true);
            new PlayerOutput().homemove(p_choices.get(choice)[0], p_choices.get(choice)[1]);
            return true;
        }
    }

    private int theIntelligence(int p_w) {
        for (int i = 0; i < figures.size(); i++) {
            if (Spielfeld.getSpielfeld()[figures.get(i).getFeld() + p_w][0] != id && Spielfeld.getSpielfeld()[figures.get(i).getFeld() + p_w][0] != 0) {
                return i;
            }
        }
        return -1;
    }
}
package Game;

import java.util.ArrayList;

class Spieler {
    /*
- colors: Color[]
     */

    private ArrayList<Figur> figures;
    private String name;

    Spieler(String p_name) {
        this.name = p_name;
    }

    private void createFigures() {

        for (int i = 0; i < 4; i++)
            figures.add(new Figur(-1));

    }

    void move() {
        System.out.println(name);
    }

    Figur selectSpielfigur(int index) {
        return figures.get(index);
    }

    String getName() {
        return name;
    }
}

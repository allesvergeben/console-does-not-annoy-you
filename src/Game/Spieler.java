package Game;

public class Spieler {

    private String name;
    private int id;

    Spieler(String p_name) {
        this.name = p_name;
    }

    private void createFigures() {
        new Figur(-1);
    }

    String getName() {
        return name;
    }
}

public class Spieler {

    private String name;

    public Spieler(String name) {
        this.name = name;
    }

    private void createFiguren() {
        new Figur(-1);

    }

    public String getName() {
        return name;
    }
}

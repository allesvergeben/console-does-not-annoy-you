package Game;

public class Wurfel {
    private int rolled;

    Wurfel() {
        set(rollthedice());
    }

    private int rollthedice() {
        return (int) (Math.random() * 6) + 1;
    }

    private void set(int p_rolled) {
        this.rolled = p_rolled;
    }

    int get() {
        return rolled;
    }
}

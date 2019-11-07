package Game;

import UserInputs.IntInput;

class Wurfel {
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

    int get(boolean p_hack) {
        if (p_hack) {
            return new IntInput("Was willst du Würfeln?", 1, 6).get();
        } else {
            return rolled;
        }
    }
}
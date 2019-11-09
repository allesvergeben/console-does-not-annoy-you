package Game;

import Graphics.Display;
import UserInputs.IntInput;

class Wurfel {
    private int rolled;

    Wurfel() {
        set(rollthedice());
    }

    private int rollthedice() {
        //random 1-6
        return (int) (Math.random() * 6) + 1;
    }

    private void set(int p_rolled) {
        //setzt rolled zu p_rolled
        this.rolled = p_rolled;
    }

    int get(boolean p_hack) {
        //motherload
        if (p_hack) {
            new Display();
            return new IntInput("Was willst du Würfeln?", 1, 6).get();
        } else {
            return rolled;
        }
    }
}
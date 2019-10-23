package UserInputs;

import java.util.Scanner;

public class StringInput {
    private Scanner inputScanner = new Scanner(System.in);
    private String userInputString;

    public StringInput(String message, int length) {
        System.out.println(message);
        set(inputScanner.nextLine(), length);
    }

    private void set(String p_input, int p_length) {
        if (p_length <= p_input.length()) {
            System.out.println("Die Eingabe darf nur maximal " + p_length + " Zeichen haben.\n Deine Eingabe hatte " + p_input.length() + " Zeichen!");
            set(inputScanner.nextLine(), p_length);
        } else {
            userInputString = p_input;
        }
    }

    public String get() {
        return userInputString;
    }
}

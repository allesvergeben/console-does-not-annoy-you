package UserInputs;

import java.util.Scanner;

public class StringInput {
    private Scanner inputScanner = new Scanner(System.in);
    private String userInputString;

    public StringInput(String message, int length, boolean command) {
        if (command) {
            System.out.println(message);
            checkCommands(inputScanner.nextLine());
        } else {
            System.out.println(message);
            set(inputScanner.nextLine(), length);
        }
    }

    private void set(String p_input, int p_length) {
        if (p_length <= p_input.length()) {
            System.out.println("Die Eingabe darf nur maximal " + p_length + " Zeichen haben.\n Deine Eingabe hatte " + p_input.length() + " Zeichen!");
            set(inputScanner.nextLine(), p_length);
        } else {
            userInputString = p_input;
        }
    }

    private void checkCommands(String p_input) {
        if ("!HELP".equals(p_input.toUpperCase())) {
            userInputString = "!help wurde eigegeben";
        } else {
            System.out.println("Dieser Befehl wurde nicht gefunden! Für eine Liste an befehlen, gib !help ein.");
            checkCommands(inputScanner.nextLine());
        }
    }
    public String get() {
        return userInputString;
    }
}




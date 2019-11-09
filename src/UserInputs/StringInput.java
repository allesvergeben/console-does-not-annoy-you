package UserInputs;

import Game.Spiel;

import java.util.Scanner;

public class StringInput {
    private Scanner inputScanner = new Scanner(System.in);
    private String userInputString;

    public StringInput(String p_message, int p_length) {
        //Sagt dem Nutzer was er eingeben soll
        System.out.println(p_message);
        //setzt die eingabe auf userInputString
        set(inputScanner.nextLine(), p_length);
    }

    private void set(String p_input, int p_length) {
        checkCommand(p_input);
        if (p_length <= p_input.length()) {
            //ausgabe
            System.out.println("Die Eingabe darf nur maximal " + p_length + " Zeichen haben.\n Deine Eingabe hatte " + p_input.length() + " Zeichen!");
            //nochmal fragen
            set(inputScanner.nextLine(), p_length);
        } else {
            userInputString = p_input;
        }
    }

    private void checkCommand(String p_input) {
        //reset
        Spiel.setHack(false);
        Spiel.setPc(false);
        //equals.ignor hatte mal wieder kein bock
        if ("EL NINO".equals(p_input.toUpperCase())) {
            System.out.println("Ahja, da kann wohl einer nicht verlieren");
            Spiel.setHack(true);
        } else if ("PC".equals(p_input.toUpperCase())) {
            System.out.println("Keine Freunde oder was?");
            Spiel.setPc(true);
        }
    }

    public String get() {
        return userInputString;
    }
}




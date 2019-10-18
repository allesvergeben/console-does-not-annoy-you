package UserInputs;

import java.util.Scanner;

public class IntInput {
    private int UserInputInt;

    public IntInput(String message, double von, double bis) {
        Scanner inputScanner = new Scanner(System.in);
        set(inputhandler(message, inputScanner, von, bis));
    }

    private static int parseInt(String p_validate, double p_von, double p_bis) {
        //Checkt ob der Input eine Zahl ist die dem Schema ±xxxxx... entspricht
        int p_validate_int;
        try {
            //Versucht die Eingabe von einem String zu einem int zu "parsen"

            p_validate_int = Integer.parseInt(p_validate);
            if (p_validate_int >= p_von && p_validate_int <= p_bis) {
                return p_validate_int;
            } else {
                System.out.println("Es können hier nur Zahlen von " + Math.round(p_von) + " bis " + Math.round(p_bis) + " eingegeben werden.");
                return -1;
            }
        } catch (java.lang.NumberFormatException e) {
            //java.lang.NumberFormatException ist ein Error der kommt wenn keine Zahl eingegeben wurde die ±xxxxx… entspricht

            //Nutzer auffordern, nur Zahlen nach dem Schema ±xxxxxx... einzugeben
            System.out.println("Es können hier nur Ganze Zahlen eingegeben werden");
            //-1 zurückgeben, weil -> siehe inputhandler
            //geht in dem falle, weil -1 nie vom Nutzer eingegeben werden müsste, sonst wird er einfach nochmal gefragt
            return -1; // "sagt da ist ein Fehler"
        }
    }

    private static int inputhandler(String p_message, Scanner p_scanner, double p_von, double p_bis) {
        int input;
        System.out.println(p_message);
        //parseInt(p_scanner.nextLine()) == -1 heißt error
        while ((input = parseInt(p_scanner.nextLine(), p_von, p_bis)) == -1) {
            //wenn die Nutzereingabe nicht Valide ist, Frage wiederholen
            System.out.println(p_message);
        }
        return input;
    }

    private void set(int p_input) {
        UserInputInt = p_input;
    }

    public int get() {
        return UserInputInt;
    }
}

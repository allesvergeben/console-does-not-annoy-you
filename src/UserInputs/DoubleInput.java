package UserInputs;

import java.util.Scanner;

public class DoubleInput {
    private double UserInputDouble;

    public DoubleInput(String message, double von, double bis) {
        Scanner inputScanner = new Scanner(System.in);
        set(inputhandler(message, inputScanner, von, bis));
    }

    //eigenes parseDouble
    private static double parseDouble(String p_validate, double p_von, double p_bis) {
        //Checkt ob der Input eine Zahl ist die dem Schema ±x.xxxx... entspricht
        double p_validate_double;
        try {
            //Versucht die Eingabe von einem String zu einem double zu "parsen"
            p_validate_double = Double.parseDouble(p_validate);
            if (p_validate_double >= p_von && p_validate_double <= p_bis) {
                return p_validate_double;
            } else {
                System.out.println("Es können hier nur Zahlen von " + p_von + " bis " + p_bis + " eingegeben werden.");
                return Double.NaN;
            }
        } catch (java.lang.NumberFormatException e) {
            //java.lang.NumberFormatException ist ein Error der kommt wenn keine Zahl eingegeben wurde die ±xxxx.xxxx… entspricht

            //Nutzer auffordern, nur Zahlen nach dem Schema ±x.xx... einzugeben
            System.out.println("Es können hier nur Zahlen nach dem Schema x.xx... eingegeben werden");
            //NaN zurückgeben, weil -> siehe inputhandler
            return Double.NaN; // "sagt da ist ein Fehler"
        }
    }

    private static double inputhandler(String p_message, Scanner p_scanner, double p_von, double p_bis) {
        double input;

        System.out.println(p_message);

        //Double.isNaN checkt ob bei parseDouble ein Fehler aufgetreten ist und wiederholt sonst die Frage nach der Zahl bis der Nutzer eine valide Zahl eingegeben hat
        //gleichzeitig wird inputs[i] auf die aktuelle Eingabe gesetzt falls der Input valide ist
        while (Double.isNaN(input = parseDouble(p_scanner.nextLine(), p_von, p_bis))) {
            //wenn die Nutzereingabe nicht Valide ist, Frage wiederholen
            System.out.println(p_message);
        }
        return input;
    }

    private void set(double p_input) {
        UserInputDouble = p_input;
    }

    public Double get() {
        return UserInputDouble;
    }
}

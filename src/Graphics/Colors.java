package Graphics;

public class Colors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";

    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String BLUE_BACKGROUND = "\033[44m";

    public static String color(int p_ID, String prefix, String suffix) {
        switch (p_ID) {
            case 1:
                return Colors.BLUE + prefix + p_ID + suffix + Colors.RESET;
            case 2:
                return Colors.GREEN + prefix + p_ID + suffix + Colors.RESET;
            case 3:
                return Colors.RED + prefix + p_ID + suffix + Colors.RESET;
            case 4:
                return Colors.YELLOW + prefix + p_ID + suffix + Colors.RESET;
        }
        return "";
    }
}

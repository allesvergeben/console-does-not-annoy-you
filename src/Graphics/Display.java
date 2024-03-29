package Graphics;

import Game.Spiel;
import Game.Spielfeld;

public class Display {
    private String[][] positions = new String[40][2];
    private String[] outputs;
    private String[][] homespots = new String[4][4];
    private String[][] winspots = new String[4][4];

    public Display() {
        Colors C = new Colors();
        setPositions();
        setOutput();
        print();
    }

    private void print() {
        for (String output : outputs) {
            System.out.println(output);
        }
    }

    private void setPositions() {
        //winspots
        for (int i = 0; i < Spiel.getPlayercount(); i++) {
            for (int j = 0; j < 4; j++) {
                if (Spielfeld.getWinspot().get(i).size() < j + 1) {
                    winspots[i][j] = "  ";
                } else {
                    switch (i) {
                        case 0:
                            winspots[i][j] = Colors.BLUE + "MM" + Colors.RESET;
                            break;
                        case 1:
                            winspots[i][j] = Colors.GREEN + "MM" + Colors.RESET;
                            break;
                        case 2:
                            winspots[i][j] = Colors.RED + "MM" + Colors.RESET;
                            break;
                        case 3:
                            winspots[i][j] = Colors.YELLOW + "MM" + Colors.RESET;
                            break;
                    }
                }
            }
        }
        //winspots for not playing player
        for (int i = Spiel.getPlayercount(); i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                winspots[i][j] = "  ";
            }
        }
        //Spielfelder
        for (int i = 0; i < positions.length; i++) {
            if (Spielfeld.getSpielfeld()[i][0] == 0) {
                positions[i][0] = "  ";
                positions[i][1] = "  ";
            } else {
                switch (Spielfeld.getSpielfeld()[i][0]) {
                    case 1:
                        positions[i][0] = Colors.BLUE + "P" + Spielfeld.getSpielfeld()[i][0] + Colors.RESET;
                        positions[i][1] = Colors.BLUE + "F" + Spiel.getPlayer(Spielfeld.getSpielfeld()[i][0] - 1).getFigurIndexByHash(Spielfeld.getSpielfeld()[i][1]) + Colors.RESET;
                        break;
                    case 2:
                        positions[i][0] = Colors.GREEN + "P" + Spielfeld.getSpielfeld()[i][0] + Colors.RESET;
                        positions[i][1] = Colors.GREEN + "F" + Spiel.getPlayer(Spielfeld.getSpielfeld()[i][0] - 1).getFigurIndexByHash(Spielfeld.getSpielfeld()[i][1]) + Colors.RESET;
                        break;
                    case 3:
                        positions[i][0] = Colors.RED + "P" + Spielfeld.getSpielfeld()[i][0] + Colors.RESET;
                        positions[i][1] = Colors.RED + "F" + Spiel.getPlayer(Spielfeld.getSpielfeld()[i][0] - 1).getFigurIndexByHash(Spielfeld.getSpielfeld()[i][1]) + Colors.RESET;
                        break;
                    case 4:
                        positions[i][0] = Colors.YELLOW + "P" + Spielfeld.getSpielfeld()[i][0] + Colors.RESET;
                        positions[i][1] = Colors.YELLOW + "F" + Spiel.getPlayer(Spielfeld.getSpielfeld()[i][0] - 1).getFigurIndexByHash(Spielfeld.getSpielfeld()[i][1]) + Colors.RESET;
                        break;
                }
            }
        }
        //homespots
        for (int i = 0; i < Spiel.getPlayercount(); i++) {
            int resthomespotanzahl = 4 - Spiel.getPlayer(i).getFigures().size() - Spielfeld.getWinspot().get(i).size();
            for (int j = 0; j < 4; j++) { //4 Spots
                if (resthomespotanzahl < j + 1) {
                    switch (i) {
                        case 0:
                            homespots[i][j] = Colors.BLUE_BACKGROUND + "    " + Colors.RESET;
                            break;
                        case 1:
                            homespots[i][j] = Colors.GREEN_BACKGROUND + "    " + Colors.RESET;
                            break;
                        case 2:
                            homespots[i][j] = Colors.RED_BACKGROUND + "    " + Colors.RESET;
                            break;
                        case 3:
                            homespots[i][j] = Colors.YELLOW_BACKGROUND + "    " + Colors.RESET;
                            break;
                    }

                } else {
                    switch (i) {
                        case 0:
                            homespots[i][j] = Colors.BLUE + "MMMM" + Colors.RESET;
                            break;
                        case 1:
                            homespots[i][j] = Colors.GREEN + "MMMM" + Colors.RESET;
                            break;
                        case 2:
                            homespots[i][j] = Colors.RED + "MMMM" + Colors.RESET;
                            break;
                        case 3:
                            homespots[i][j] = Colors.YELLOW + "MMMM" + Colors.RESET;
                            break;
                    }
                }
            }
        }
        //Homespots for not playing player
        for (int i = Spiel.getPlayercount(); i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                homespots[i][j] = "    ";
            }
        }
    }

    private void setOutput() {
        outputs = new String[]{
                "                         " + "#".repeat(16) + "                         ",
                "                         # " + positions[8][0] + " | " + positions[9][0] + " | " + positions[10][0] + " #                         ",
                "                         # " + positions[8][1] + " | " + positions[9][1] + " | " + positions[10][1] + " #                         ",
                "         " + homespots[0][0] + "   " + homespots[0][1] + "     #____XXXXXX____#   " + homespots[1][0] + "   " + homespots[1][1] + "           ",
                "         " + homespots[0][0] + "   " + homespots[0][1] + "     # " + positions[7][0] + " X " + winspots[1][3] + " X " + positions[11][0] + " #   " + homespots[1][0] + "   " + homespots[1][1] + "           ",
                "                         # " + positions[7][1] + " X " + winspots[1][3] + " X " + positions[11][1] + " #                         ",
                "                         #____X....X____#                         ",
                "         " + homespots[0][2] + "   " + homespots[0][3] + "     # " + positions[6][0] + " X " + winspots[1][2] + " X " + positions[12][0] + " #   " + homespots[1][2] + "   " + homespots[1][3] + "           ",
                "         " + homespots[0][2] + "   " + homespots[0][3] + "     # " + positions[6][1] + " X " + winspots[1][2] + " X " + positions[12][1] + " #   " + homespots[1][2] + "   " + homespots[1][3] + "           ",
                "                         #____X....X____#                         ",
                "                         # " + positions[5][0] + " X " + winspots[1][1] + " X " + positions[13][0] + " #                         ",
                "                         # " + positions[5][1] + " X " + winspots[1][1] + " X " + positions[13][1] + " #                         ",
                "     " + "#".repeat(21) + "----X....X----" + "#".repeat(21) + "     ",
                "     # " + positions[0][0] + " | " + positions[1][0] + " | " + positions[2][0] + " | " + positions[3][0] + " | " + positions[4][0] + " X " + winspots[1][0] + " X " + positions[14][0] + " | " + positions[15][0] + " | " + positions[16][0] + " | " + positions[17][0] + " | " + positions[18][0] + " #     ",
                "     # " + positions[0][1] + " | " + positions[1][1] + " | " + positions[2][1] + " | " + positions[3][1] + " | " + positions[4][1] + " X " + winspots[1][0] + " X " + positions[14][1] + " | " + positions[15][1] + " | " + positions[16][1] + " | " + positions[17][1] + " | " + positions[18][1] + " #     ",
                "     #----" + "X".repeat(46) + "----#     ",
                "     # " + positions[39][0] + " X " + winspots[0][3] + " : " + winspots[0][2] + " : " + winspots[0][1] + " : " + winspots[0][0] + " X####X " + winspots[2][0] + " : " + winspots[2][1] + " : " + winspots[2][2] + " : " + winspots[2][3] + " X " + positions[19][0] + " #     ",
                "     # " + positions[39][1] + " X " + winspots[0][3] + " : " + winspots[0][2] + " : " + winspots[0][1] + " : " + winspots[0][0] + " X####X " + winspots[2][0] + " : " + winspots[2][1] + " : " + winspots[2][2] + " : " + winspots[2][3] + " X " + positions[19][1] + " #     ",
                "     #----" + "X".repeat(46) + "----#     ",
                "     # " + positions[38][0] + " | " + positions[37][0] + " | " + positions[36][0] + " | " + positions[35][0] + " | " + positions[34][0] + " X " + winspots[3][0] + " X " + positions[24][0] + " | " + positions[23][0] + " | " + positions[22][0] + " | " + positions[21][0] + " | " + positions[20][0] + " #     ",
                "     # " + positions[38][1] + " | " + positions[37][1] + " | " + positions[36][1] + " | " + positions[35][1] + " | " + positions[34][1] + " X " + winspots[3][0] + " X " + positions[24][1] + " | " + positions[23][1] + " | " + positions[22][1] + " | " + positions[21][1] + " | " + positions[20][1] + " #     ",
                "     " + "#".repeat(21) + "----X....X----" + "#".repeat(21) + "     ",
                "                         # " + positions[33][0] + " X " + winspots[3][1] + " X " + positions[25][0] + " #                         ",
                "                         # " + positions[33][1] + " X " + winspots[3][1] + " X " + positions[25][1] + " #                         ",
                "          " + homespots[3][2] + "   " + homespots[3][3] + "    #____X....X____#   " + homespots[2][3] + "   " + homespots[2][2] + "           ",
                "          " + homespots[3][2] + "   " + homespots[3][3] + "    # " + positions[32][0] + " X " + winspots[3][2] + " X " + positions[26][0] + " #   " + homespots[2][3] + "   " + homespots[2][2] + "           ",
                "                         # " + positions[32][1] + " X " + winspots[3][2] + " X " + positions[26][1] + " #                         ",
                "          " + homespots[3][1] + "   " + homespots[3][0] + "    #____X....X____#   " + homespots[2][1] + "   " + homespots[2][0] + "           ",
                "          " + homespots[3][1] + "   " + homespots[3][0] + "    # " + positions[31][0] + " X " + winspots[3][3] + " X " + positions[27][0] + " #   " + homespots[2][1] + "   " + homespots[2][0] + "           ",
                "                         # " + positions[31][1] + " X " + winspots[3][3] + " X " + positions[27][1] + " #                         ",
                "                         #____XXXXXX____#                         ",
                "                         # " + positions[30][0] + " | " + positions[29][0] + " | " + positions[28][0] + " #                         ",
                "                         # " + positions[30][1] + " | " + positions[29][1] + " | " + positions[28][1] + " #                         ",
                "                         " + "#".repeat(16) + "                         "
        };
    }
}

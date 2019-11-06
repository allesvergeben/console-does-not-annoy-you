package Graphics;

import Game.Spiel;
import Game.Spielfeld;


public class Display {
    private String[][] positions = new String[40][2];
    private String[] output;
    private String[][] homespots = new String[4][4];

    public Display() {
        setPositions();
        setOutput();
        print();
    }

    private void print() {
        for (String output : output) {
            System.out.println(output);
        }
    }

    private void setPositions() {
        for (int i = 0; i < positions.length; i++) {
            if (Spielfeld.getSpielfeld()[i][0] == 0) {
                positions[i][0] = "  ";
                positions[i][1] = "  ";
            } else {
                positions[i][0] = "P" + Spielfeld.getSpielfeld()[i][0];
                positions[i][1] = "F" + Spielfeld.getSpielfeld()[i][1];
            }
        }
        for (int i = 0; i < Spiel.getPlayercount(); i++) {
            int resthomespotanzahl = 4 - Spiel.getPlayer(i).getFigures().size() - Spielfeld.getWinspot().get(i).size();
            for (int j = 0; j < 4; j++) { //4 Spots
                //wenn nur 2 oder 3 Spieler
                if (resthomespotanzahl < j + 1) {
                    homespots[i][j] = "    ";
                } else {
                    homespots[i][j] = "MMMM";
                }

            }
        }
        for (int i = Spiel.getPlayercount(); i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                homespots[i][j] = "    ";
            }
        }
    }

    private void setOutput() {
        output = new String[]{
                "                         " + "#".repeat(16) + "                         ",
                "                         # " + positions[8][0] + " | " + positions[9][0] + " | " + positions[10][0] + " #                         ",
                "                         # " + positions[8][1] + " | " + positions[9][1] + " | " + positions[10][1] + " #                         ",
                "         " + homespots[0][0] + "   " + homespots[0][1] + "     #____XXXXXX____#   " + homespots[1][0] + "   " + homespots[1][1] + "           ",
                "         " + homespots[0][0] + "   " + homespots[0][1] + "     # " + positions[7][0] + " X    X " + positions[11][0] + " #   " + homespots[1][0] + "   " + homespots[1][1] + "           ",
                "                         # " + positions[7][1] + " X    X " + positions[11][1] + " #                         ",
                "                         #____X....X____#                         ",
                "         " + homespots[0][2] + "   " + homespots[0][3] + "     # " + positions[6][0] + " X    X " + positions[12][0] + " #   " + homespots[1][2] + "   " + homespots[1][3] + "           ",
                "         " + homespots[0][2] + "   " + homespots[0][3] + "     # " + positions[6][1] + " X    X " + positions[12][1] + " #   " + homespots[1][2] + "   " + homespots[1][3] + "           ",
                "                         #____X....X____#                         ",
                "                         # " + positions[5][0] + " X    X " + positions[13][0] + " #                         ",
                "                         # " + positions[5][1] + " X    X " + positions[13][1] + " #                         ",
                "     " + "#".repeat(21) + "____X....X____" + "#".repeat(21) + "     ",
                "     # " + positions[0][0] + " | " + positions[1][0] + " | " + positions[2][0] + " | " + positions[3][0] + " | " + positions[4][0] + " X    X " + positions[14][0] + " | " + positions[15][0] + " | " + positions[16][0] + " | " + positions[17][0] + " | " + positions[18][0] + " #     ",
                "     # " + positions[0][1] + " | " + positions[1][1] + " | " + positions[2][1] + " | " + positions[3][1] + " | " + positions[4][1] + " X    X " + positions[14][1] + " | " + positions[15][1] + " | " + positions[16][1] + " | " + positions[17][1] + " | " + positions[18][1] + " #     ",
                "     #____" + "X".repeat(46) + "____#     ",
                "     # " + positions[39][0] + " X    :    :    :    X####X    :    :    :    X " + positions[19][0] + " #     ",
                "     # " + positions[39][1] + " X    :    :    :    X####X    :    :    :    X " + positions[19][1] + " #     ",
                "     #____" + "X".repeat(46) + "____#     ",
                "     # " + positions[38][0] + " | " + positions[37][0] + " | " + positions[36][0] + " | " + positions[35][0] + " | " + positions[34][0] + " X    X " + positions[24][0] + " | " + positions[23][0] + " | " + positions[22][0] + " | " + positions[21][0] + " | " + positions[20][0] + " #     ",
                "     # " + positions[38][1] + " | " + positions[37][1] + " | " + positions[36][1] + " | " + positions[35][1] + " | " + positions[34][1] + " X    X " + positions[24][1] + " | " + positions[23][1] + " | " + positions[22][1] + " | " + positions[21][1] + " | " + positions[20][1] + " #     ",
                "     " + "#".repeat(21) + "____X....X____" + "#".repeat(21) + "     ",
                "                         # " + positions[33][0] + " X    X " + positions[25][0] + " #                         ",
                "                         # " + positions[33][1] + " X    X " + positions[25][1] + " #                         ",
                "          " + homespots[2][2] + "   " + homespots[2][3] + "    #____X....X____#   " + homespots[3][3] + "   " + homespots[3][2] + "           ",
                "          " + homespots[2][2] + "   " + homespots[2][3] + "    # " + positions[32][0] + " X    X " + positions[26][0] + " #   " + homespots[3][3] + "   " + homespots[3][2] + "           ",
                "                         # " + positions[32][1] + " X    X " + positions[26][1] + " #                         ",
                "          " + homespots[2][1] + "   " + homespots[2][0] + "    #____X....X____#   " + homespots[3][1] + "   " + homespots[3][0] + "           ",
                "          " + homespots[2][1] + "   " + homespots[2][0] + "    # " + positions[31][0] + " X    X " + positions[27][0] + " #   " + homespots[3][1] + "   " + homespots[3][0] + "           ",
                "                         # " + positions[31][1] + " X    X " + positions[27][1] + " #                         ",
                "                         #____XXXXXX____#                         ",
                "                         # " + positions[30][0] + " | " + positions[29][0] + " | " + positions[28][0] + " #                         ",
                "                         # " + positions[30][1] + " | " + positions[29][1] + " | " + positions[28][1] + " #                         ",
                "                         " + "#".repeat(16) + "                         "
        };
    }
}

package UserOutputs;

public class PcOutput {


    public void rolled(String p_Name, int p_ID, int p_rolled) {
        System.out.println(p_Name + " (ID: " + p_ID + ") hat eine " + p_rolled + " gewürfelt");
    }

    public void moved(String p_Name, int p_ID, int p_FigurId) {
        System.out.println(p_Name + " (ID: " + p_ID + ") ist mit Figur " + p_FigurId + " gegangen");
    }

    public void out() {
        System.out.println("Eine neue Figur ist jetzt auf dem Spielfeld");
    }
}

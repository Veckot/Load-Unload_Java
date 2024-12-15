public class Main {
    private static final String FILE_NAME = "jmena.txt";
    private static final String DELIMITER = ";";
    private static final String FILE_NAME2 = "jmena2.txt";
    public static void main(String[] args) {
        EvidenceOsob evidenceOsob = new EvidenceOsob();
        try {
            evidenceOsob.nactiOsobyZeSouboru(FILE_NAME, DELIMITER);
            for (Osoba osoba : evidenceOsob.getEvidence()) {
                System.out.println(osoba.getJmeno()+ " " + osoba.getVyska() + " " + osoba.getDatum() + " " + osoba.getPlatba() + " " + osoba.isJeZamestnanec() + " " + osoba.getVaha());
            }
            evidenceOsob.zapisOsobyDoSouboru(FILE_NAME2, DELIMITER+" ");
        } catch (EvidenceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
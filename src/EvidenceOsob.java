import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvidenceOsob {
    private List<Osoba> evidence = new ArrayList<>();

    public void pridani(Osoba e) {
        evidence.add(e);
    }

    public void odstraneni(Osoba e) {
        evidence.remove(e);
    }

    public List<Osoba> getEvidence() {
        return new ArrayList<>(evidence);
    }

    public void nactiOsobyZeSouboru(String nazevSouboru, String oddelovac) throws EvidenceException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(nazevSouboru)))) {
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                pridani(parseOsoba(radek, oddelovac));
            }
        } catch (FileNotFoundException exception) {
            throw new EvidenceException("Soubor: " + nazevSouboru + " nebyl nalezen: " + exception.getLocalizedMessage());
        } catch (EvidenceException e) {
            throw new EvidenceException("Chyba při načítání souboru: " + e.getMessage());
        }
    }
    public void zapisOsobyDoSouboru(String nazevSouboru, String oddelovac) throws EvidenceException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nazevSouboru)))) {
            for(Osoba osoba : evidence) {
                writer.println(osoba.getJmeno() + oddelovac + osoba.getVyska() + oddelovac + osoba.getDatum() + oddelovac + osoba.getPlatba() + oddelovac + osoba.isJeZamestnanec() + oddelovac + osoba.getVaha());
            }
        } catch (IOException exception) {
            throw new EvidenceException("Chyba při zapisu do souboru: " + nazevSouboru + exception.getLocalizedMessage());
        }
    }

    private Osoba parseOsoba(String radek, String oddelovac) throws EvidenceException {
        String[] polozky = radek.split(oddelovac);
        if (polozky.length != 6) {
            throw new EvidenceException("Chybný počet údajů v řádku: " + radek);
        }
        try {
            String jmeno = polozky[0].trim();
            int vyska = Integer.parseInt(polozky[1].trim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate datum = LocalDate.parse(polozky[2].trim(), formatter);
            BigDecimal platba = new BigDecimal(polozky[3].trim());
            boolean jeZamestnanec = Boolean.parseBoolean(polozky[4].trim());
            double vaha = Double.parseDouble(polozky[5].trim());
            return new Osoba(jmeno, vyska, datum, platba, jeZamestnanec, vaha);
        } catch (NumberFormatException e) {
            throw new EvidenceException("Chybný formát čísla v řádku: " + radek);
        } catch (DateTimeParseException e) {
            throw new EvidenceException("Chybný formát datumu v řádku: " + radek);
        }
    }
}
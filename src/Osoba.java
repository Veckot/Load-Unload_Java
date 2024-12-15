import java.math.BigDecimal;
import java.time.LocalDate;

public class Osoba {
    private String jmeno;
    private int vyska;
    private LocalDate datum;
    private BigDecimal platba;
    private boolean jeZamestnanec;
    private double vaha;

    public Osoba(String jmeno, int vyska, LocalDate datum, BigDecimal platba, boolean jeZamestnanec, double vaha) {
        this.jmeno = jmeno;
        this.vyska = vyska;
        this.datum = datum;
        this.platba = platba;
        this.jeZamestnanec = jeZamestnanec;
        this.vaha = vaha;
    }

    public String getJmeno() {
        return jmeno;
    }

    public int getVyska() {
        return vyska;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public BigDecimal getPlatba() {
        return platba;
    }

    public boolean isJeZamestnanec() {
        return jeZamestnanec;
    }

    public double getVaha() {
        return vaha;
    }
}
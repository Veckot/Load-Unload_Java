import java.math.BigDecimal;
import java.time.LocalDate;

public class Player {
    private String name;
    private int networth;
    private BigDecimal level;
    private LocalDate profileCreation;
    private boolean ironman;

    public Player(String name, int networth, BigDecimal level, LocalDate profileCreation, boolean ironman) {
        this.name = name;
        this.networth = networth;
        this.level = level;
        this.profileCreation = profileCreation;
        this.ironman = ironman;
    }

    public String getName() {
        return name;
    }

    public int getNetworth() {
        return networth;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public LocalDate getProfileCreation() {
        return profileCreation;
    }

    public boolean isIronman() {
        return ironman;
    }
}

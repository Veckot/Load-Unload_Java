import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config {
    private List<Player> playerList = new ArrayList<>();
    public void addToList(Player p) {playerList.add(p);};
    public void removeFromList(Player p) {playerList.remove(p);};
    public List<Player> getList() {return new ArrayList<>(playerList);}

    public void loadPlayersFromFile(String filepath, String semicolon) throws ConfigException{
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filepath)))){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                addToList(parsePlayer(line, semicolon));
            }
        } catch (FileNotFoundException exception){
            throw new ConfigException("File: " + filepath + " not found: " + exception.getLocalizedMessage());
        } catch (ConfigException e) {
            throw new ConfigException("Failed to load file: " + e.getMessage());
        }
    }

    public void addPlayersToList(String filepath, String semicolon) throws ConfigException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))){
            for(Player player : playerList){
                writer.println(player.getName() + semicolon + player.getNetworth() + semicolon + player.getLevel() + semicolon + player.getProfileCreation() + semicolon + player.isIronman());
            }
        } catch (IOException exception){
            throw new ConfigException("Faild to write to file: " + filepath + exception.getLocalizedMessage());
        }
    }

    private Player parsePlayer(String line, String semicolon) throws ConfigException{
        String[] parts = line.split(semicolon);
        if (parts.length != 5){
            throw new ConfigException("Bad number of parts: " + line);
        }
        try {
            String name = parts[0].trim();
            int networth = Integer.parseInt(parts[1].trim());
            BigDecimal level = new BigDecimal(parts[2].trim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate profileCreation = LocalDate.parse(parts[3].trim(), formatter);
            boolean ironman = Boolean.parseBoolean(parts[4].trim());
            return new Player(name, networth, level, profileCreation, ironman);
        } catch (NumberFormatException e){
            throw new ConfigException("Bad format: " + line);
        } catch (DateTimeParseException e){
            throw new ConfigException("Bad format: " + line);
        }
    }

    public Player createPlayer(Scanner scanner) {
        System.out.print("Enter player's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter player's net worth(in m): ");
        int networth = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter player's level (x.x): ");
        BigDecimal level = new BigDecimal(scanner.nextLine());

        System.out.print("Enter profile creation date (YYYY-MM-DD): ");
        LocalDate profileCreation = LocalDate.parse(scanner.nextLine());

        System.out.print("Is the player an ironman? (true/false): ");
        boolean isIronman = Boolean.parseBoolean(scanner.nextLine());

        return new Player(name, networth, level, profileCreation, isIronman);
    }
}

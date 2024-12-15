import java.util.Scanner;

public class Main {
    private static final  String FILE_PATH = "players.txt";
    private static final  String FILE_PATH2 = "players2.txt";
    private static final  String SEMICOLON = ";";
    public static void main(String[] args) {
        Config config = new Config();
        Scanner scanner = new Scanner(System.in);

        try {
            config.loadPlayersFromFile(FILE_PATH2, SEMICOLON);
            for (Player player : config.getList()) {
                System.out.println(player.getName() + " " + player.getNetworth() + "m " + player.getLevel() + " " + player.getProfileCreation() + " " + player.isIronman());
            }

            // Add new players through scanner
            boolean addMore = true;
            while (addMore) {
                System.out.println("Do you want to add a new player? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes")) {
                    Player newPlayer = config.createPlayer(scanner);
                    config.addToList(newPlayer);
                    config.addPlayersToList(FILE_PATH2, SEMICOLON + " ");
                } else {
                    addMore = false;
                }
            }
        } catch (ConfigException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
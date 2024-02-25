import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to the Console Chat App!");
        ChatHistoryModel chatModel = new ChatHistoryModel();
        ChatObserver detailedLogger = new DetailedChatLogger();
       //main part
        chatModel.addObserver(detailedLogger);

        System.out.println("Please enter your username:");
        String username = "";
        try {
            username = reader.readLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return;
        }

        boolean continueApp = true;

        while (continueApp) {
            System.out.println("Choose a service:");
            System.out.println("1: Get a random joke");
            System.out.println("2: Get your horoscope");
            System.out.println("3: Get your chat history");
            System.out.println("4: Delete your chat history");
            System.out.println("5: Update your chat history");
            System.out.println("6: Exit");
            System.out.println("Enter the number of the service you want to use:");

            try {
                String userInput = reader.readLine();

                switch (userInput) {
                    case "1":
                        try {
                            String apiUrl = "https://official-joke-api.appspot.com/random_joke";
                            String jokeJson = JokeFetcher.getJokeFromApi(apiUrl);
                            String joke = JokeFetcher.parseJoke(jokeJson);
                            System.out.println("Joke: " + joke);
                            String userInputForDb = "User selected joke service";
                            String serverResponseForDb = joke;
                            chatModel.saveChat(username, userInputForDb, serverResponseForDb, LocalDateTime.now());
                        } catch (Exception e) {
                            System.out.println("Error fetching joke: " + e.getMessage());
                        }
                        break;
                    case "2":
                        System.out.println("Enter your zodiac sign:");
                        String sign = reader.readLine().trim().toLowerCase();
                        String horoscope = HoroscopeFetcher.getHoroscope(sign);
                        System.out.println("Horoscope for " + sign + ": " + horoscope);
                        String userInputForDb = "user requested horoscope for " + sign;
                        String serverResponseForDb = horoscope;
                        LocalDateTime timestamp = LocalDateTime.now();
                        chatModel.saveChat(username, userInputForDb, serverResponseForDb, timestamp);
                        break;
                    case "3":
                        chatModel.readChatByUsername(username);
                        break;
                    case "4":
                        System.out.println("WARNING: This will delete all chats for a username.");
                        chatModel.deleteChatByUsername(username);
                        System.out.println("All chats for " + username + " have been deleted.");
                        break;
                    case "5":
                        System.out.println("Enter new username:");
                        String newUsername = reader.readLine().trim();
                        chatModel.updateUsernameInChat(username, newUsername);
                        System.out.println("All chats for " + username + " have been updated to " + newUsername + ".");
                        break;
                    case "6":
                        System.out.println("Exiting the application.");
                        continueApp = false;
                        break;
                    default:
                        System.out.println("Service not recognized. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}

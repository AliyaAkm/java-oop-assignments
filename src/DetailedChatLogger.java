import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetailedChatLogger implements ChatObserver {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    public void update(String action, String username, String details) {
        String currentTime = LocalDateTime.now().format(formatter);
        System.out.println(ANSI_BLUE + currentTime + " - Log: " + action + " by user '" + username + "'. " + details + ANSI_RESET);
    }
}

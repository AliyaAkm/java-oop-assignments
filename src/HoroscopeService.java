import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class HoroscopeService implements Service {
    @Override
    public void execute(String username, ChatHistoryModel chatModel) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your zodiac sign:");
        String sign = reader.readLine().trim().toLowerCase();
        String horoscope = HoroscopeFetcher.getHoroscope(sign);
        System.out.println("Horoscope for " + sign + ": " + horoscope);
        String userInputForDb = "User requested horoscope for " + sign;
        String serverResponseForDb = horoscope;
        chatModel.saveChat(username, userInputForDb, serverResponseForDb, LocalDateTime.now());
    }
}

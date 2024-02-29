import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class JokeFetcher implements Service {

    @Override
    public void execute(String username, ChatHistoryModel chatModel) throws Exception {
        executeService(username, chatModel);
    }

    public void executeService(String username, ChatHistoryModel chatModel) throws Exception {
        String apiUrl = "https://official-joke-api.appspot.com/random_joke";
        String jokeJson = getJokeFromApi(apiUrl);
        String joke = parseJoke(jokeJson);
        System.out.println("Joke: " + joke);
        String userInputForDb = "User selected joke service";
        String serverResponseForDb = joke;
        chatModel.saveChat(username, userInputForDb, serverResponseForDb, LocalDateTime.now());
    }

    private String getJokeFromApi(String urlString) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    private String parseJoke(String json) {
        String setup = json.split("\"setup\":\"")[1].split("\",")[0];
        String punchline = json.split("\"punchline\":\"")[1].split("\"}")[0];
        return setup + " - " + punchline;
    }
}

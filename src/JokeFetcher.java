import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JokeFetcher {

    public static String getJokeFromApi(String urlString) throws Exception {
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

   public static String parseJoke(String json) {
        String setup = json.split("\"setup\":\"")[1].split("\",")[0];
        String punchline = json.split("\"punchline\":\"")[1].split("\"}")[0];
        return setup + " - " + punchline;
    }

}

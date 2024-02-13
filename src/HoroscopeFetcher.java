import java.util.HashMap;
import java.util.Map;

public class HoroscopeFetcher {

    private static final Map<String, String> horoscopes = new HashMap<>();

    static {
        horoscopes.put("aries", "New beginnings are on the horizon.");
        horoscopes.put("taurus", "Stability and comfort are key today.");
        horoscopes.put("gemini", "Your adaptability will bring opportunities.");
        horoscopes.put("cancer", "Nurture your relationships today.");
        horoscopes.put("leo", "Your confidence shines and leads the way.");
        horoscopes.put("virgo", "Attention to detail pays off.");
        horoscopes.put("libra", "Seek balance in all things.");
        horoscopes.put("scorpio", "Intensity in connections deepens.");
        horoscopes.put("sagittarius", "Adventure calls to you.");
        horoscopes.put("capricorn", "Discipline and effort bring rewards.");
        horoscopes.put("aquarius", "Innovation and ideas flow freely.");
        horoscopes.put("pisces", "Creativity and intuition are heightened.");
    }

    public static String getHoroscope(String sign) {
        return horoscopes.getOrDefault(sign.toLowerCase(), "Sign not recognized. Please try again.");
    }
}

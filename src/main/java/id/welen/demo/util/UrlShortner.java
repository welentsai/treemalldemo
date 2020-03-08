package id.welen.demo.util;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLOutput;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UrlShortner {
    private static final int MAX_ID = 897132831;
    private static final int MIN_ID = 116132831;
    private static SecureRandom random = null;
    private static final Map<Integer, String> URL_MAP = new ConcurrentHashMap<>();

    private static UrlShortner shortner = null;

    public static UrlShortner getInstance() throws Exception {
        if (shortner == null) {
            shortner = new UrlShortner();
        }
        return shortner;
    }

    public UrlShortner() throws Exception {
        random = SecureRandom.getInstance("SHA1PRNG");
    }

    public String getShortURL(String longUrl) {
        int id = getID();
        URL_MAP.put(id, longUrl);
        return Base62.encode(id);
    }

    private int getID() {
        int id = random.nextInt(MAX_ID);
        return id;
    }

    public String getOriginalURL(String shortUrl) {
        if (shortUrl != null && shortUrl.length() > 0) {
            return URL_MAP.get(Base62.decode(shortUrl));
        }
        return "";
    }

//    public static void main(String... args) throws Exception {
//        System.out.println("Hello World");
//        String shortUrl = UrlShortner.getInstance().getShortURL("https://howtodoinjava.com/java/multi-threading/atomicinteger-example/");
//        System.out.println("short url =" + shortUrl);
//        String longUrl = UrlShortner.getInstance().getOriginalURL(shortUrl);
//        System.out.println("long url =" + longUrl);
//    }
}

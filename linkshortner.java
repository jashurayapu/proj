import java.util.HashMap;
import java.util.Map;

public class LinkShortener {
    private Map<String, String> urlMap;
    private String baseUrl;

    public LinkShortener(String baseUrl) {
        this.urlMap = new HashMap<>();
        this.baseUrl = baseUrl;
    }

    // Basic hash function to generate short URL
    private String generateShortUrl(String longUrl) {
        int hashCode = longUrl.hashCode(); // Get the hash code of the long URL
        String shortUrl = Integer.toHexString(hashCode); // Convert hash code to hexadecimal string
        return shortUrl.substring(0, 6); // Take the first 6 characters as the short URL
    }

    // Shorten a long URL
    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        urlMap.put(shortUrl, longUrl);
        return baseUrl + "/" + shortUrl;
    }

    // Retrieve original URL from short URL
    public String getOriginalUrl(String shortUrl) {
        if (shortUrl.startsWith(baseUrl + "/")) {
            String key = shortUrl.substring(baseUrl.length() + 1);
            return urlMap.get(key);
        }
        return null;
    }

    public static void main(String[] args) {
        // Example usage:
        String baseUrl = "https://short.url";
        LinkShortener shortener = new LinkShortener(baseUrl);

        String longUrl1 = "https://www.example.com/page1";
        String shortUrl1 = shortener.shortenUrl(longUrl1);
        System.out.println("Shortened URL 1: " + shortUrl1);

        String longUrl2 = "https://www.example.com/page2";
        String shortUrl2 = shortener.shortenUrl(longUrl2);
        System.out.println("Shortened URL 2: " + shortUrl2);

        // Retrieving original URLs
        String originalUrl1 = shortener.getOriginalUrl(shortUrl1);
        String originalUrl2 = shortener.getOriginalUrl(shortUrl2);
        System.out.println("Original URL 1: " + originalUrl1);
        System.out.println("Original URL 2: " + originalUrl2);
    }
}

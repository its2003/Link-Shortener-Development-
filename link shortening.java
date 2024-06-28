import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> urlMap;
    private String domain;
    private int counter;

    public LinkShortener() {
        urlMap = new HashMap<>();
        domain = "http://short.url/";
        counter = 1;
    }

    public String shortenURL(String longURL) {
        if (urlMap.containsValue(longURL)) {
            for (Map.Entry<String, String> entry : urlMap.entrySet()) {
                if (entry.getValue().equals(longURL)) {
                    return entry.getKey();
                }
            }
        }
        String shortURL = domain + encode(counter);
        urlMap.put(shortURL, longURL);
        counter++;
        return shortURL;
    }

    public String expandURL(String shortURL) {
        if (urlMap.containsKey(shortURL)) {
            return urlMap.get(shortURL);
        } else {
            return "Error: Short URL does not exist.";
        }
    }

    private String encode(int num) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder shortURL = new StringBuilder();
        while (num > 0) {
            shortURL.append(map[num % 62]);
            num /= 62;
        }
        return shortURL.reverse().toString();
    }
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option: ");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Enter the long URL: ");
                    String longURL = scanner.nextLine();
                    String shortURL = linkShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;
                case 2:
                    System.out.println("Enter the short URL: ");
                    String shortURLToExpand = scanner.nextLine();
                    String originalURL = linkShortener.expandURL(shortURLToExpand);
                    System.out.println("Original URL: " + originalURL);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

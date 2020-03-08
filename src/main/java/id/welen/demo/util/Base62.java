package id.welen.demo.util;

public class Base62 {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String encode(int id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(ALPHABET.charAt(id % BASE));
            id /= BASE;
        }
        return sb.reverse().toString();
    }

    public static int decode(String str) {
        int id = 0;
        for ( int i = 0; i < str.length(); i++ )
            id = id * BASE + ALPHABET.indexOf(str.charAt(i));
        return id;
    }
}

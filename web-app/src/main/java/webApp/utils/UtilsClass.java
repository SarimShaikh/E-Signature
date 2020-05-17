package webApp.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Sarim on 5/2/2020.
 */
public class UtilsClass {

    public static String generateSignatureCode() {
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey.toString();
    }

    public static String generateOauthkey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        String generatedString;
        Random random = new Random();

        return generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String dateformat(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate localdate = LocalDate.parse(date, inputFormatter);
        return outputFormatter.format(localdate).toString();

    }

    public static String getLocalDate() {

        return java.time.LocalDate.now().toString();
    }
}

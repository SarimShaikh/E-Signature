package webApp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /*public static String dateformat(String date) {
        String dob = date;  //its in MM/dd/yyyy
        String newDate = null;
        Date dtDob = new Date(dob);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newDate = sdf.format(dtDob);
        } catch (Exception e) {
        }
        return newDate;
    }*/
}

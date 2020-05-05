package webApp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Sarim on 5/2/2020.
 */
public class UtilsClass {

    public static String generateSignatureCode() {
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey.toString();
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

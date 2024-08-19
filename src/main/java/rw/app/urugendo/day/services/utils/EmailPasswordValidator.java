package rw.app.urugendo.day.services.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPasswordValidator {
    public static boolean isValidGmail(String email){
        String regex = "^[A-Za-z0-9+_.-]@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_+=<>?]).{6,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(password);

        return matcher.matches();
    }
}

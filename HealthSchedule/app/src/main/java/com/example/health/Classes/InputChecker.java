package com.example.health.Classes;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {
    public static boolean checkEmail(String email) {
        Pattern emailPattern = Pattern.compile("([[a-z][A-Z][0-9]._])+@([a-z])+.com");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    public static boolean checkPassword(String password) {
        Pattern passwordPattern = Pattern.compile("([[a-z][A-Z][0-9]]){6,}");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }

    public static boolean checkName(String name) {
        Pattern namePattern = Pattern.compile("[A-Z]([a-z])*");
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }

    public static boolean checkBirthday(String birthday) {
        HashMap<String, Integer> monthToDays = new HashMap<String, Integer>();
        monthToDays.put("01", 31);
        monthToDays.put("02", 29);
        monthToDays.put("03", 31);
        monthToDays.put("04", 30);
        monthToDays.put("05", 31);
        monthToDays.put("06", 30);
        monthToDays.put("07", 31);
        monthToDays.put("08", 31);
        monthToDays.put("09", 30);
        monthToDays.put("10", 31);
        monthToDays.put("11", 30);
        monthToDays.put("12", 31);

        Pattern birthdayPattern = Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2}");
        Matcher birthdayMatcher = birthdayPattern.matcher(birthday);
        //If the format is right...
        if (birthdayMatcher.matches()) {
            //IGNORE year
            //If the number of days in the month are realistic (IGNORING non-leap year, assuming February has 29)
            if (Integer.parseInt(birthday.substring(8)) <= monthToDays.get(birthday.substring(5,7)))
                return true;
        }
        return false;
    }

    public static boolean checkHealthCard(String healthCard) {
        Pattern healthCardPattern = Pattern.compile("([0-9]){10}");
        Matcher healthCardMatcher = healthCardPattern.matcher(healthCard);
        return healthCardMatcher.matches();
    }

    public static boolean checkProficiency(String proficiency) {
        if (proficiency.length() < 1) return false;
        return true;
    }
}

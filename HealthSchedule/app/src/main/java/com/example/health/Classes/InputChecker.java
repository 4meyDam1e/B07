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

package com.example.health.Classes;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {
    public static boolean checkEmail(String email) {
        Pattern emailPattern = Pattern.compile("([0-9a-zA-Z](_|\\.)?)*[0-9a-zA-Z]@([0-9a-zA-Z]+\\.)+[0-9a-zA-Z]+");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    public static boolean checkPassword(String password) {
        Pattern passwordPattern = Pattern.compile("([a-zA-Z0-9]){6,}");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }

    public static boolean checkName(String name) {
        if(name.length() > 50) return false;
        Pattern namePattern = Pattern.compile("[a-zA-Z]+");
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }

    public static boolean checkHealthCard(String healthCard) {
        Pattern healthCardPattern = Pattern.compile("([0-9]){10}");
        Matcher healthCardMatcher = healthCardPattern.matcher(healthCard);
        return healthCardMatcher.matches();
    }

    public static boolean checkProficiency(String proficiency) {
        List<String> ps = Doctor.proficiencyList(proficiency);
        if (ps.size() == 0) return false;
        for (String p : ps)
            if (!checkSingleProficiency(p))
                return false;
        return true;
    }

    public static boolean checkSingleProficiency(String proficiency) {
        Pattern proficiencyPattern = Pattern.compile("(\\w+\\s?)*\\w+");
        Matcher proficiencyMatcher = proficiencyPattern.matcher(proficiency);
        return proficiencyMatcher.matches();
    }
}
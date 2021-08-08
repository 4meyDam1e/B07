package com.example.health.Classes;

public class InputChecker {
    public static boolean checkEmail(String email) {
        if (email.length() < 1) return false;
        return true;
    }

    public static boolean checkPassword(String password) {
        if (password.length() < 6) return false;
        return true;
    }

    public static boolean checkName(String name) {
        if (name.length() < 1) return false;
        return true;
    }

    public static boolean checkBirthday(String birthday) {
        if (birthday.length() < 1) return false;
        return true;
    }

    public static boolean checkHealthCard(String healthCard) {
        if (healthCard.length() < 1) return false;
        return true;
    }

    public static boolean checkProficiency(String proficiency) {
        if (proficiency.length() < 1) return false;
        return true;
    }
}

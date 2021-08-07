package com.groupone.projectapp.Classes;

public class SingletonUserStore {
    private static SingletonUserStore globalUser = null;
    private static User user;

    private SingletonUserStore(User user) {
        this.user = user;
    }

    public static SingletonUserStore getGlobalUser(User user) {
        if (globalUser == null)
            return new SingletonUserStore(user);
        else
            return globalUser;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User userToSet) {
        user = userToSet;
    }

    public static boolean isDoctor() { return user instanceof Doctor; }
    public static boolean isPatient() { return user instanceof Patient; }
}

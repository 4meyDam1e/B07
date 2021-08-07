package com.groupone.projectapp.Classes;

public class SingletonUserStore {
    private static SingletonUserStore globalUser = null;
    private User user;

    private SingletonUserStore(User user) {
        this.user = user;
    }

    public static SingletonUserStore getGlobalUser(User user) {
        if (globalUser == null)
            return new SingletonUserStore(user);
        else
            return globalUser;
    }
}

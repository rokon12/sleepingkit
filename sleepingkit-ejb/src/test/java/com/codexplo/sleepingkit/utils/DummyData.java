package com.codexplo.sleepingkit.utils;

import com.codexplo.sleepingkit.domain.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 2/13/13
 * Time: 12:21 PM
 */
public class DummyData {

    public static List<Users> getUserses(int number) {
        List<Users> usersList = new ArrayList<Users>();
        Users users;
        for (int i = 0; i < number; i++) {
            users = new Users();
            users.setEmail(createDummyText(5)+"@gmail.com");
            users.setPassword(createDummyText(10));
            users.setUserName(createDummyText(6));
            usersList.add(users);
        }
        return usersList;
    }

    public static String createDummyText(int number) {
        String text = "";
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        Random random = new Random();

        for (int i = 0; i < number; i++) {
           text+=letters.charAt( random.nextInt(letters.length()));
        }
        return text;
    }
}

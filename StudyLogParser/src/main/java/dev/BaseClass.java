package dev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract public class BaseClass {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static Set<String> getAllIpList(Date after, Date before, List<String> listLogs, String user, Event event, Status status) {
        Set<String> ipAddress = new HashSet<>();

        if(after == null && before == null) {
            // Обработать абсолютно все записи.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                checkNull(user, event, status, ipAddress, letter);
            }
        }

        else if(after == null) {
            // Записи, у которых дата меньше или равна before.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                try {
                    if(sdf.parse(letter[2]).getTime() <= sdf.parse(sdf.format(before)).getTime()) {
                        checkNull(user, event, status, ipAddress, letter);
                    }
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге after == null.");
                    e.printStackTrace();
                }
            }
        }

        else if(before == null) {
            // Записи, у которых дата больше или равна after.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                try {
                    if(sdf.parse(letter[2]).getTime() >= sdf.parse(sdf.format(after)).getTime()) {
                        checkNull(user, event, status, ipAddress, letter);
                    }
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        } else {
            // Записи за данный период, включая даты after и before.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                try {
                    if(sdf.parse(letter[2]).getTime() >= sdf.parse(sdf.format(after)).getTime() && sdf.parse(letter[2]).getTime() <= sdf.parse(sdf.format(before)).getTime()) {
                        checkNull(user, event, status, ipAddress, letter);
                    }
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        }

        return ipAddress;
    }

    private static void checkNull(String user, Event event, Status status, Set<String> ipAddress, String[] letter) {
        if(user != null) {
            if(user.equals(letter[1]))
                ipAddress.add(letter[0]);
        } else if(event != null) {
            if(event.toString().equals(letter[3].split(" ")[0]))
                ipAddress.add(letter[0]);
        } else if(status != null) {
            if(status.toString().equals(letter[4]))
                ipAddress.add(letter[0]);
        } else {
            ipAddress.add(letter[0]);
        }
    }

}
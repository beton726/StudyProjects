package dev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract public class BaseClass {


    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static int countEventsOfUser;

    public static Set<String> getAllIpList(String query, Date after, Date before, List<String> listLogs, String user, Event event, Status status, String ip, AllMethods methods) {
        Set<String> ipAddress = new HashSet<>();
        countEventsOfUser = 0;

        if(after == null && before == null) {
            // Обработать абсолютно все записи.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                if(query.equals("IpQuery"))
                    checkNullIpQuery(user, event, status, ipAddress, letter);
                else if(query.equals("UserQuery"))
                    checkNullUserQuery(user, ipAddress, letter, ip, methods);
            }
        } else if(after == null) {
            // Записи, у которых дата меньше или равна before.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                try {
                    if(sdf.parse(letter[2]).getTime() <= sdf.parse(sdf.format(before)).getTime()) {
                        if(query.equals("IpQuery"))
                            checkNullIpQuery(user, event, status, ipAddress, letter);
                        else if(query.equals("UserQuery"))
                            checkNullUserQuery(user, ipAddress, letter, ip, methods);
                    }
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге after == null.");
                    e.printStackTrace();
                }
            }
        } else if(before == null) {
            // Записи, у которых дата больше или равна after.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                try {
                    if(sdf.parse(letter[2]).getTime() >= sdf.parse(sdf.format(after)).getTime()) {
                        if(query.equals("IpQuery"))
                            checkNullIpQuery(user, event, status, ipAddress, letter);
                        else if(query.equals("UserQuery"))
                            checkNullUserQuery(user, ipAddress, letter, ip, methods);
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
                        if(query.equals("IpQuery"))
                            checkNullIpQuery(user, event, status, ipAddress, letter);
                        else if(query.equals("UserQuery"))
                            checkNullUserQuery(user, ipAddress, letter, ip, methods);
                    }
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        }

        return ipAddress;
    }

    private static void checkNullIpQuery(String user, Event event, Status status, Set<String> ipAddress, String[] letter) {
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

    private static void checkNullUserQuery(String user, Set<String> ipAddress, String[] letter, String ip, AllMethods methods) {
        if(user != null) {
            if(user.equals(letter[1])) {
                ipAddress.add(String.valueOf(countEventsOfUser));
                countEventsOfUser++;
            }
        } else if(ip != null) {
            if(ip.equals(letter[0]))
                ipAddress.add(letter[1]);
        } else if(methods != null) {
            checkMethods(ipAddress, letter, methods);
        } else {
            ipAddress.add(letter[1]);
        }

    }

    private static void checkMethods(Set<String> ipAddress, String[] letter, AllMethods methods) {
        if(methods.equals(AllMethods.getLoggedUsers)) {
            if(letter[3].split(" ")[0].equals(Event.LOGIN.toString()))
                ipAddress.add(letter[1]);
        } else if(methods.equals(AllMethods.getDownloadedPluginUsers)) {
            System.out.println(1);
            // Логика
        } else if(methods.equals(AllMethods.getWroteMessageUsers)) {
            System.out.println(1);
            // Логика
        } else if(methods.equals(AllMethods.getSolvedTaskUsers)) {
            System.out.println(1);
            // Логика
        } else if(methods.equals(AllMethods.getDoneTaskUsers)) {
            System.out.println(1);
            // Логика
        }



    }

}
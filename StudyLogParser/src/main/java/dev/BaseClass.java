package dev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract public class BaseClass {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static Set<String> getAllIpList(String query, Date after, Date before, List<String> listLogs, String user, Event event, Status status, String ip, AllMethods methods, Integer task) {
        Set<String> ipAddress = new HashSet<>();

        if(after == null && before == null) {
            // Обработать абсолютно все записи.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                if(query.equals("IpQuery"))
                    checkNullIpQuery(user, event, status, ipAddress, letter);
                else if(query.equals("UserQuery"))
                    checkNullUserQuery(user, ipAddress, letter, ip, methods, task);
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
                            checkNullUserQuery(user, ipAddress, letter, ip, methods, task);
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
                            checkNullUserQuery(user, ipAddress, letter, ip, methods, task);
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
                            checkNullUserQuery(user, ipAddress, letter, ip, methods, task);
                    }
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        }

        return ipAddress;
    }

    public static Set<Date> getAllDateList(Date after, Date before, List<String> listLogs, String user, Event event, Status status, String ip, AllMethods methods, Integer task) {
        Set<Date> infoDate = new HashSet<>();

        if(after == null && before == null) {
            // Обработать абсолютно все записи.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                checkNameEventDateQuery(user, event, letter, infoDate, methods, task);
            }
        } else if(after == null) {
            // Записи, у которых дата меньше или равна before.
            for (String word : listLogs) {
                String[] letter = word.split("\t");
                try {
                    if(sdf.parse(letter[2]).getTime() <= sdf.parse(sdf.format(before)).getTime())
                        checkNameEventDateQuery(user, event, letter, infoDate, methods, task);
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
                    if(sdf.parse(letter[2]).getTime() >= sdf.parse(sdf.format(after)).getTime())
                        checkNameEventDateQuery(user, event, letter, infoDate, methods, task);
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
                    if(sdf.parse(letter[2]).getTime() >= sdf.parse(sdf.format(after)).getTime() && sdf.parse(letter[2]).getTime() <= sdf.parse(sdf.format(before)).getTime())
                        checkNameEventDateQuery(user, event, letter, infoDate, methods, task);
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        }
        return infoDate;
    }

    private static void checkNameEventDateQuery(String user, Event event, String[] letter, Set<Date> infoDate, AllMethods methods, Integer task) {
        switch(methods) {
            case getDatesForUserAndEvent:
                // должен возвращать даты, когда определенный пользователь произвел определенное событие(Event).
                methGetDatesForUserAndEvent(user, event, letter, infoDate);
                break;
            case getDatesWhenSomethingFailed:
                // должен возвращать даты, когда любое событие не выполнилось (статус FAILED).
                methGetDatesWhenSomethingFailed(letter, infoDate);
                break;
            case getDatesWhenErrorHappened:
                // должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR).
                methGetDatesWhenErrorHappened(letter, infoDate);
                break;
            case getDateWhenUserLoggedFirstTime:
                // должен возвращать дату, когда пользователь залогинился(LOGIN) впервые за указанный период. Если такой даты в логах нет - null.
                methGetDateWhenUserLoggedFirstTime(user, letter, infoDate);
                break;
            case getDateWhenUserSolvedTask:
                // должен возвращать дату, когда пользователь впервые попытался(SOLVE_TASK) решить определенную задачу. Если такой даты в логах нет - null.
                methodGetDateWhenUserSolvedTask(user, letter, infoDate, task);
                break;
            case getDateWhenUserDoneTask:
                // должен возвращать дату, когда пользователь впервые решил(DONE_TASK) определенную задачу. Если такой даты в логах нет - null.
                methodGetDateWhenUserDoneTask(user, letter, infoDate, task);
                break;
            case getDatesWhenUserWroteMessage:
                // должен возвращать даты, когда пользователь написал сообщение(WRITE_MESSAGE).
                methodGetDatesWhenUserWroteMessage(user, letter, infoDate);
                break;
            case getDatesWhenUserDownloadedPlugin:
                // должен возвращать даты, когда пользователь скачал плагин(DOWNLOAD_PLUGIN).
                methodGetDatesWhenUserDownloadedPlugin(user, letter, infoDate);
                break;
        }
    }

    private static void methGetDatesForUserAndEvent(String user, Event event, String[] letter, Set<Date> infoDate) {
        if(letter[1].equals(user) && letter[3].split(" ")[0].equals(event.toString())) {
            try {
                infoDate.add(sdf.parse(letter[2]));
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Error parse date: Method methGetDatesForUserAndEvent.");
            }
        }
    }

    private static void methGetDatesWhenSomethingFailed(String[] letter, Set<Date> infoDate) {
        try {
            if(letter[4].equals(Status.FAILED.toString()))
                infoDate.add(sdf.parse(letter[2]));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methGetDatesWhenSomethingFailed.");
        }
    }

    private static void methGetDatesWhenErrorHappened(String[] letter, Set<Date> infoDate) {
        try {
            if(letter[4].equals(Status.ERROR.toString()))
                infoDate.add(sdf.parse(letter[2]));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methGetDatesWhenErrorHappened.");
        }
    }

    private static void methGetDateWhenUserLoggedFirstTime(String user, String[] letter, Set<Date> infoDate) {
        try {
            String[] spitLetter = letter[3].split(" ");
            if(letter[1].equals(user) && spitLetter[0].equals(Event.LOGIN.toString()))
                infoDate.add(sdf.parse(letter[2]));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methGetDateWhenUserLoggedFirstTime.");
        }
    }

    public static void methodGetDateWhenUserSolvedTask(String user, String[] letter, Set<Date> infoDate, Integer task) {
        try {
            String[] spitLetter = letter[3].split(" ");
            if(letter[1].equals(user) && spitLetter[0].equals(Event.SOLVE_TASK.toString()) && spitLetter[1].equals(String.valueOf(task)))
                infoDate.add(sdf.parse(letter[2]));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methodGetDateWhenUserSolvedTask.");
        }
    }

    public static void methodGetDateWhenUserDoneTask(String user, String[] letter, Set<Date> infoDate, Integer task) {
        try {
            String[] spitLetter = letter[3].split(" ");
            if(letter[1].equals(user) && spitLetter[0].equals(Event.DONE_TASK.toString()) && spitLetter[1].equals(String.valueOf(task)))
                infoDate.add(sdf.parse(letter[2]));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methodGetDateWhenUserDoneTask.");
        }
    }

    public static void methodGetDatesWhenUserWroteMessage(String user, String[] letter, Set<Date> infoDate) {
        try {
            if(letter[1].equals(user) && letter[3].split(" ")[0].equals(Event.WRITE_MESSAGE.toString())) {
                infoDate.add(sdf.parse(letter[2]));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methodGetDatesWhenUserWroteMessage.");
        }
    }

    public static void methodGetDatesWhenUserDownloadedPlugin(String user, String[] letter, Set<Date> infoDate) {
        try {
            if(letter[1].equals(user) && letter[3].split(" ")[0].equals(Event.DOWNLOAD_PLUGIN.toString())) {
                infoDate.add(sdf.parse(letter[2]));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parse date: Method methodGetDatesWhenUserDownloadedPlugin.");
        }
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

    private static void checkNullUserQuery(String user, Set<String> ipAddress, String[] letter, String ip, AllMethods methods, Integer task) {
        if(user != null) {
            if(user.equals(letter[1])) {
                ipAddress.add(letter[3]);
            }
        } else if(ip != null) {
            if(ip.equals(letter[0]))
                ipAddress.add(letter[1]);
        } else if(methods != null && task != null) {
            checkMethodsTask(ipAddress, letter, methods, task);
        } else if(methods != null) {
            checkMethods(ipAddress, letter, methods);
        } else {
            ipAddress.add(letter[1]);
        }
    }

    private static void checkMethods(Set<String> ipAddress, String[] letter, AllMethods methods) {
        switch (methods) {
            case getLoggedUsers:
                addListIpAddress(ipAddress, letter, Event.LOGIN);
                break;
            case getDownloadedPluginUsers:
                addListIpAddress(ipAddress, letter, Event.DOWNLOAD_PLUGIN);
                break;
            case getWroteMessageUsers:
                addListIpAddress(ipAddress, letter, Event.WRITE_MESSAGE);
                break;
            case getSolvedTaskUsers:
                addListIpAddress(ipAddress, letter, Event.SOLVE_TASK);
                break;
            case getDoneTaskUsers:
                addListIpAddress(ipAddress, letter, Event.DONE_TASK);
                break;
        }
    }

    private static void checkMethodsTask(Set<String> ipAddress, String[] letter, AllMethods methods, Integer task) {
        switch (methods) {
            case getSolvedTaskUsersNum:
                addListIpAddressTask(ipAddress, letter, Event.SOLVE_TASK, task);
                break;
            case getDoneTaskUsersNum:
                addListIpAddressTask(ipAddress, letter, Event.DONE_TASK, task);
                break;
        }
    }

    private static void addListIpAddress(Set<String> ipAddress, String[] letter, Event ev) {
        if(letter[3].split(" ")[0].equals(ev.toString()))
            ipAddress.add(letter[1]);
    }

    private static void addListIpAddressTask(Set<String> ipAddress, String[] letter, Event ev, Integer task) {
        if(letter[3].split(" ")[0].equals(ev.toString()) && letter[3].split(" ")[1].equals(task.toString()))
            ipAddress.add(letter[1]);
    }

}
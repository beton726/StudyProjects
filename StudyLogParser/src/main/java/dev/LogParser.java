package dev;

import dev.query.IPQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static final String ipRegexp = "(\\d{0,3}\\.){3}\\d{0,3}";
    private static final String dateRegexp = "((\\d{0,2}\\.){2}\\d{0,4} (\\d{0,2}\\:){2}\\d{0,2})";

    private static List<String> listLogs;

    public LogParser(Path logDir) {
        try {
            listLogs = Files.readAllLines(logDir);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
            e.printStackTrace();
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> ipAddress = new HashSet<>();

        if(after == null && before == null) {
            // Обработать абсолютно все записи.
            for (String word : listLogs) {
                Matcher pattern = Pattern.compile(ipRegexp).matcher(word);
                if(pattern.find()) {
                    ipAddress.add(pattern.group());
                }
            }
        } else if(after == null) {
            // Записи, у которых дата меньше или равна before.
            for (String word : listLogs) {
                Matcher pattern = Pattern.compile(dateRegexp).matcher(word);
                try {
                    if(pattern.find() && (sdf.parse(pattern.group()).getTime() <= sdf.parse(sdf.format(before)).getTime()))
                        ipAddress.add(pattern.group());
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге after == null.");
                    e.printStackTrace();
                }
            }
        } else if(before == null) {
            // Записи, у которых дата больше или равна after.
            for (String word : listLogs) {
                Matcher pattern = Pattern.compile(dateRegexp).matcher(word);
                try {
                    if(pattern.find() && (sdf.parse(pattern.group()).getTime() >= sdf.parse(sdf.format(after)).getTime()))
                        ipAddress.add(pattern.group());
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        } else {
            // Записи за данный период, включая даты after и before.
            for (String word : listLogs) {
                Matcher pattern = Pattern.compile(dateRegexp).matcher(word);
                try {
                    if(pattern.find() && (sdf.parse(pattern.group()).getTime() >= sdf.parse(sdf.format(after)).getTime() && sdf.parse(pattern.group()).getTime() <= sdf.parse(sdf.format(before)).getTime()))
                        ipAddress.add(pattern.group());
                } catch (ParseException e) {
                    System.out.println("Ошибка при парсинге before == null.");
                    e.printStackTrace();
                }
            }
        }

        return ipAddress.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return null;
    }
}
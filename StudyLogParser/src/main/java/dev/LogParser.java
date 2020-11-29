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
        // Количество уникальных IP адресов за выбранный период. Включая даты.
        // Before - After
        Set<String> ipAddress = new HashSet<>();

//        System.out.println(sdf.format(after));
        System.out.println(sdf.format(before));

        if(after == null && before == null) {
            // Обработать абсолютно все записи.
            for (String word : listLogs) {
                Matcher patternIp = Pattern.compile(ipRegexp).matcher(word);
                if(patternIp.find()) {
                    ipAddress.add(patternIp.group());
                }
            }
        } else if(after == null) {
            // Записи, у которых дата меньше или равна before.


        } else if(before == null) {
            // Записи, у которых дата больше или равна after.
        } else {
            // Записи за данный период, включая даты after и before.
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
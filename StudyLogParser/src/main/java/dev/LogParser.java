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

public class LogParser extends BaseClass implements IPQuery {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

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
        return getAllIpList(after, before, listLogs).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getAllIpList(after, before, listLogs);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> ipAddress = new HashSet<>();

        for (String word : listLogs) {
            String[] letter = word.split("\t");
            if(user.equals(letter[1]))
                ipAddress.add(letter[0]);
        }

        return ipAddress;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> ipAddress = new HashSet<>();

        for (String word : listLogs) {
            String[] letter = word.split("\t");
            if(event.toString().equals(letter[3].split(" ")[0]))
                ipAddress.add(letter[0]);
        }

        return ipAddress;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> ipAddress = new HashSet<>();

        for (String word : listLogs) {
            String[] letter = word.split("\t");
            if(status.toString().equals(letter[4]))
                ipAddress.add(letter[0]);
        }

        return ipAddress;
    }

}
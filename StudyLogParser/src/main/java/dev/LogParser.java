package dev;

import dev.query.IPQuery;
import dev.query.UserQuery;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser extends BaseClass implements IPQuery, UserQuery {

    private static List<String> listLogs = new ArrayList<>();

    private static String IpQuery = "IpQuery";
    private static String UserQuery = "UserQuery";

    public LogParser(Path logDir) {

        try(Stream<Path> walk = Files.walk(logDir)) {
            List<String> allPath = walk.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());

            for (String path : allPath) {
                if(path.endsWith(".log")) {
                    try(BufferedReader bf = new BufferedReader(new FileReader(path))) {
                        while(bf.ready())
                            listLogs.add(bf.readLine());
                    } catch (IOException e) {
                        System.out.println("Ошибка считывания данных из файла.");
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при поиске файлов в каталоге.");
            e.printStackTrace();
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, null, null, null, null).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, null, null, null, null);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, user, null, null, null, null);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, event, null, null, null);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, null, status, null, null);
    }

    @Override
    public Set<String> getAllUsers() {
        return getAllIpList(UserQuery, null, null, listLogs, null, null, null, null, null);
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, null).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, user, null, null, null, null).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, ip, null);
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getLoggedUsers);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return null;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return null;
    }
}
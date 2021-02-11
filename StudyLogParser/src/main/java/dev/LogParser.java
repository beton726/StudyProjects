package dev;

import dev.query.DateQuery;
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

public class LogParser extends BaseClass implements IPQuery, UserQuery, DateQuery {

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
        return getAllIpList(IpQuery, after, before, listLogs, null, null, null, null, null, null).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, null, null, null, null, null);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, user, null, null, null, null, null);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, event, null, null, null, null);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getAllIpList(IpQuery, after, before, listLogs, null, null, status, null, null, null);
    }

    @Override
    public Set<String> getAllUsers() {
        return getAllIpList(UserQuery, null, null, listLogs, null, null, null, null, null, null);
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, null, null).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, user, null, null, null, AllMethods.getNumberOfUserEvents, null).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, ip, null, null);
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getLoggedUsers, null);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getDownloadedPluginUsers, null);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getWroteMessageUsers, null);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getSolvedTaskUsers, null);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getSolvedTaskUsersNum, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getDoneTaskUsers, null);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getAllIpList(UserQuery, after, before, listLogs, null, null, null, null, AllMethods.getDoneTaskUsersNum, task);
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getAllDateList(after, before, listLogs, user, event, null, null, AllMethods.getDatesForUserAndEvent, null);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getAllDateList(after, before, listLogs, null, null, null, null, AllMethods.getDatesWhenSomethingFailed, null);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getAllDateList(after, before, listLogs, null, null, null, null, AllMethods.getDatesWhenErrorHappened, null);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> dateSet = getAllDateList(after, before, listLogs, user, null, null, null, AllMethods.getDateWhenUserLoggedFirstTime, null);
        Date date = null;
        for (Date dates : dateSet)
            date = dates;
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date>dateSet = getAllDateList(after, before, listLogs, user, null, null, null, AllMethods.getDateWhenUserSolvedTask, task);
        Date date = null;
        for (Date dates : dateSet)
            date = dates;
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date>dateSet = getAllDateList(after, before, listLogs, user, null, null, null, AllMethods.getDateWhenUserSolvedTask, task);
        Date date = null;
        for (Date dates : dateSet)
            date = dates;
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getAllDateList(after, before, listLogs, user, null, null, null, AllMethods.getDatesWhenErrorHappened, null);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getAllDateList(after, before, listLogs, user, null, null, null, AllMethods.getDatesWhenErrorHappened, null);
    }

}
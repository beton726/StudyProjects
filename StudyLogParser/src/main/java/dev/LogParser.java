package dev;

import dev.query.IPQuery;

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

public class LogParser extends BaseClass implements IPQuery {

    private static List<String> listLogs = new ArrayList<>();

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
        return getAllIpList(after, before, listLogs, null, null, null).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getAllIpList(after, before, listLogs, null, null, null);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getAllIpList(after, before, listLogs, user, null, null);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getAllIpList(after, before, listLogs, null, event, null);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getAllIpList(after, before, listLogs, null, null, status);
    }

}
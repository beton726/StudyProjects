package dev;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\Programming\\Java\\Java_Programm\\StudyProjects\\StudyLogParser\\src\\main\\java\\dev\\logs\\"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
//        System.out.println(logParser.getIPsForUser("Vasya Pupkin", new Date(1346284800000L), new Date(1393632000000L)));
//        System.out.println(logParser.getIPsForEvent(Event.LOGIN, new Date(1346284800000L), new Date(1393632000000L)));
//        System.out.println(logParser.getIPsForStatus(Status.OK, null, null).size());
//        System.out.println(logParser.getAllUsers());
//        System.out.println(logParser.getNumberOfUserEvents("Vasya Pupkin", null, null));
        System.out.println(logParser.getLoggedUsers( null,  null));



    }
}
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
        System.out.println(logParser.getDateWhenUserSolvedTask("Amigo", 18, null, null));
//        logParser.getDatesForUserAndEvent("Amigo", Event.LOGIN, null, null);
//        System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.LOGIN,null, null));
//        System.out.println(logParser.getIPsForUser("Vasya Pupkin", new Date(1346284800000L), new Date(1393632000000L)));
//        System.out.println(logParser.getIPsForEvent(Event.LOGIN, new Date(1346284800000L), new Date(1393632000000L)));
//        System.out.println(logParser.getIPsForStatus(Status.OK, null, null).size());
//        System.out.println(logParser.getAllUsers());
//        System.out.println(logParser.getNumberOfUserEvents("Amigo", null, null));
//        System.out.println(logParser.getDoneTaskUsers(null, null, 15));
//        System.out.println(logParser.getSolvedTaskUsers( null,  null, 15));


        // 1346284800000 - 30.08.2012
        // 1379030400000 - 13.09.2013
        // 1393632000000 - 01.03.2014

    }
}
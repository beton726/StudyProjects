package dev;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\Programming\\Java\\Java_Programm\\StudyProjects\\StudyLogParser\\src\\main\\java\\dev\\logs\\example.log"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
//        System.out.println(logParser.getIPsForUser("Vasya Pupkin", new Date(1346284800000L), new Date(1393632000000L)));
//        System.out.println(logParser.getIPsForEvent(Event.LOGIN, new Date(1346284800000L), new Date(1393632000000L)));
//        System.out.println(logParser.getIPsForStatus(Status.OK, new Date(1346284800000L), new Date(1393632000000L)));

    }
}
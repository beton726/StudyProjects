package dev;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\Programming\\Java\\Java_Programm\\StudyProjects\\StudyLogParser\\src\\main\\java\\dev\\logs\\example.log"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
    }
}
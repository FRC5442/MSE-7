/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Add your docs here.
 */
public class CSVLogger {
    enum Levels {
        FATAL,
        NORMAL,
        OTHER
    }

    //level is the variable name or the header
    public void log(String level, String stufftolog) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String logStuff = (dtf.format(now) + ',' + level + ',' + stufftolog);

        FileWriter fileWriter = new FileWriter("log.csv", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(logStuff);
        printWriter.close();
    }

    //level is the variable name or the header
    public void fatal(String level, String stufftolog) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String logStuff = (dtf.format(now) + ',' + level + ',' + stufftolog);

        FileWriter fileWriter = new FileWriter("fatal.csv", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(logStuff);
        printWriter.close();
    }

    //level is the variable name or the header
    public void normal(String level, String stufftolog) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String logStuff = (dtf.format(now) + ',' + level + ',' + stufftolog);

        FileWriter fileWriter = new FileWriter("normal.csv", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(logStuff);
        printWriter.close();
    }
}

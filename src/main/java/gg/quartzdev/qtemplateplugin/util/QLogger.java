package gg.quartzdev.qtemplateplugin.util;

import org.bukkit.Bukkit;

public class QLogger {
    /**
     * Logs a general message
     * @param msg - String to log
     */
    public static void info(String msg){
        Sender.message(Bukkit.getConsoleSender(), msg);
    }
    /**
     * Logs a general message
     * @param msg - Message to log
     */
    public static void info(Messages msg){
        Sender.message(Bukkit.getConsoleSender(), msg);
    }

    /**
     * Logs a warning
     * @param msg
     */
    public static void warning(String msg){
        info("<yellow>" + msg);
//        TODO: Optional log warnings to file
    }

    /**
     * Logs a warning
     * @param msg
     */
    public static void warning(Messages msg){
        info("<yellow>" + msg.get());
//        TODO: Optional log warnings to file
    }

    /**
     * Logs error
     * @param msg
     */
    public static void error(String msg){
        info("<red>" + msg);
//        TODO: Optional log errors to file
    }

    public static void error(Messages msg){
        info("<red>" + msg.get());
//        TODO: Optional log errors to file
    }

    public static void error(StackTraceElement[] error){
        for(StackTraceElement e : error){
            error(e.toString());
        }
    }
}

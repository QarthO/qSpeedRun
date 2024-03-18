package gg.quartzdev.qspeedrun.util;

public enum Messages {

    //    Plugin Prefix
    CONSOLE_PREFIX("<gray>[<red>q<aqua>SpeedRun<gray>]"),
    CHAT_PREFIX("<red>q<aqua>SpeedRun <bold><gray>></bold>"),

    //    Generic Plugin
    PLUGIN_INFO("<prefix> <green>Running version <gray><version>"),
    RELOAD_COMPLETE("<prefix> <green>Config reloaded"),
    PLUGIN_DISABLE("<prefix> Disabling..."),
    PLUGIN_UNSAFE_DISABLE("<prefix> <yellow>Caution: Reloading the server might cause issues. Try restarting the server next time"),
    ERROR_CMD_NOT_FOUND("<prefix> <red>Error: Command not found: <yellow><cmd>"),
    ERROR_NO_PERMISSION("<prefix> <red>Error: Insufficient permission"),
    ERROR_PLAYER_ONLY_COMMAND("<prefix> <red>Error: You must be a player to run this command"),
    ERROR_CONSOLE_ONLY_COMMAND("<prefix> <red>Error: This command can only be ran from the console"),
    //      Dev Errors
    ERROR_PLUGIN_ENABLE("<prefix> <red>Error: Plugin already enabled. Most likely caused by unsupported plugin managers or an addon"),

    //      Generic File
    FILE_CREATED("<prefix> <green>Created file: <yellow><file>"),
    ERROR_CREATE_FILE("<prefix> <red>Error creating file: <yellow><file>"),
    ERROR_SAVE_FILE("<prefix> <red>Error saving file: <yellow><file>"),
    ERROR_CORRUPT_FILE("<prefix> <red>Error: Corrupt file: <yellow><file></yellow><newline>Please reset the file."),

//          Example Messages
    CONFIG_SET_SETTING("<prefix> <yellow><setting><green> has been set to <yellow><value>"),
    BROADCAST_BOSS_KILLED("<prefix> <green><player_color><player_name> <green>killed a <dark_purple><boss_type>"),
    BROADCAST_BOSS_DEATH("<prefix> Someone killed a <dark_purple><boss_type>"),
    LOG_BROADCAST_SOUND("<prefix> Broadcating sound to all players: <yellow><sound>"),

    END("");


    private final String message;
    private String parsedMessage;

    Messages(String msg){
        this.message = msg;
        this.parsedMessage = msg;
    }

    @Override
    public String toString(){
        return this.message;
    }

    public String get(){
        String result = this.parsedMessage;
        this.parsedMessage = this.message;
        return result;
    }

    public Messages parse(String placeholder, String value){
        this.parsedMessage = this.parsedMessage.replaceAll("<" + placeholder + ">", value);
        return this;
    }


}

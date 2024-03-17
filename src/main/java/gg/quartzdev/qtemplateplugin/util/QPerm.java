package gg.quartzdev.qtemplateplugin.util;

public enum QPerm {

    COMMAND,
    GROUP_BASIC,
    GROUP_ADMIN;

    private final String permissionPrefix = "qplugin";

    @Override
    public String toString(){
        return get();
    }

    public String cmd(String commandName){
        return get() + "." + commandName.toLowerCase();
    }

    public String get() {
        return (permissionPrefix + "." + this.name().replaceAll("_", ".")).toLowerCase();
    }

}

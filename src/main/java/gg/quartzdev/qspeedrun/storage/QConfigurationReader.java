package gg.quartzdev.qspeedrun.storage;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;

public class QConfigurationReader {

    protected static @Nullable Sound sound(Object data){
        if(data == null){
            return null;
        }

        if(data instanceof Sound){
            return (Sound) data;
        }
//
        final String rawData = data.toString();
        if(rawData.equalsIgnoreCase("none")){
            return null;
        }
        try{
            return Sound.valueOf(rawData);
        } catch(IllegalArgumentException e){
            return null;
        }
    }

    protected static @Nullable EntityType entityType(Object data){
//       If data isn't found
        if(data == null){
            return null;
        }

//        Convert to string and try parsing
        String rawData = data.toString();

        try {
            return EntityType.valueOf(rawData);
        } catch(IllegalArgumentException e){
            return null;
        }
    }

}

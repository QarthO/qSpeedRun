package gg.quartzdev.qspeedrun.storage;

import gg.quartzdev.qspeedrun.util.QLogger;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Config extends QConfiguration {

    private final String PATH_DISABLED_WORLDS = "disabled-worlds";
    private final String PATH_BOSS_TYPES = "boss-types";
    private final String PATH_BOSS_ALERT_SOUND = "boss-death-alert.sound";
    private final String PATH_BOSS_ALERT_VOLUME = "boss-death-alert.volume";
    private final String PATH_BOSS_ALERT_PITCH = "boss-death-alert.pitch";
    private List<World> disabledWorlds;
    private List<EntityType> bossTypes;
    private Sound bossAlertSound;
    private float bossAlertVolume;
    private float bossAlertPitch;

    public Config(String fileName) {
        super(fileName);
        disabledWorlds = new ArrayList<>();
        bossTypes = new ArrayList<>();
        loadAllData();
    }

    public void loadAllData(){
        loadDisabledWorlds();
        loadBossTypes();
        loadBossDeathAlert();
    }
    public void saveAllData(){
        saveDisabledWorlds();
        saveBossTypes();
    }

    public void loadDisabledWorlds(){
        disabledWorlds = getWorldList(PATH_DISABLED_WORLDS);
    }
    public boolean isDisabledWorld(World world){
        return disabledWorlds.contains(world);
    }
    public void saveDisabledWorlds(){
        List<String> worldNames = disabledWorlds.stream().map(World::getName).toList();
        yamlConfiguration.set(PATH_DISABLED_WORLDS, worldNames);
        save();
    }

    public void loadBossTypes(){
        bossTypes = getEntityTypeList(PATH_BOSS_TYPES);
    }
    public boolean isBossType(EntityType type){
        return bossTypes.contains(type);
    }
    public void saveBossTypes(){
        List<String> bossNames = bossTypes.stream().map(EntityType::name).toList();;
        yamlConfiguration.set(PATH_BOSS_TYPES, bossNames);
        save();
    }
    public List<EntityType> getBossTypes(){
        return List.copyOf(bossTypes);
    }

    public void loadBossDeathAlert() {
        bossAlertSound = getSound(PATH_BOSS_ALERT_SOUND);
        bossAlertVolume = getNumber(PATH_BOSS_ALERT_VOLUME, 1f).floatValue();
        bossAlertPitch = getNumber(PATH_BOSS_ALERT_PITCH, 1f).floatValue();
    }

    public net.kyori.adventure.sound.Sound getBossAlert(){
        return net.kyori.adventure.sound.Sound.sound(
                bossAlertSound,
                net.kyori.adventure.sound.Sound.Source.RECORD,
                bossAlertVolume,
                bossAlertPitch
        );
    }

}

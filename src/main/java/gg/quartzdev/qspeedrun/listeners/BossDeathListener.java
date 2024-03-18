package gg.quartzdev.qspeedrun.listeners;

import gg.quartzdev.qspeedrun.util.Messages;
import gg.quartzdev.qspeedrun.util.QLogger;
import gg.quartzdev.qspeedrun.util.QPlugin;
import gg.quartzdev.qspeedrun.util.Sender;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class BossDeathListener implements Listener {

    public BossDeathListener(){
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(QPlugin.getConfig().isDisabledWorld(event.getEntity().getWorld())){
            return;
        }

        if(!QPlugin.getConfig().isBossType(event.getEntityType())){
            return;
        }
        final LivingEntity entity = event.getEntity();
        final Player player = entity.getKiller();

        Messages msg = Messages.BROADCAST_BOSS_KILLED;

        if(player == null){
            Player assumedKiller = guessKiller(event.getEntity());
            if(assumedKiller == null) {
                msg = Messages.BROADCAST_BOSS_DEATH;
            }
        }

        msg
                .parse("player_color", "<blue>")
                .parse("player_name", player.getName())
                .parse("boss_type", WordUtils.capitalizeFully(event.getEntityType().name()));

        Sender.broadcast(msg.get());
        Sound sound = Sound.sound(Key.key("block.note_block.banjo"), Sound.Source.BLOCK, 1f, 1f);
        Sender.broadcast(sound);
    }

    private @Nullable Player guessKiller(LivingEntity entity){
        if(entity.getKiller() != null){
            return entity.getKiller();
        }
        Collection<Player> players = entity.getLocation().getNearbyPlayers(100);
        Player closestPlayer = null;
        double closestDistance = 0;
        for(Player player : players){
            if(closestPlayer == null){
                closestPlayer = player;
                continue;
            }
            double distance = entity.getLocation().distanceSquared(player.getLocation());
            if(distance <= closestDistance){
                closestPlayer = player;
                closestDistance = distance;
            }
        }
        return closestPlayer;
    }

}

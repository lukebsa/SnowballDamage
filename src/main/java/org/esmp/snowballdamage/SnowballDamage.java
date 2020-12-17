package org.esmp.snowballdamage;

import java.io.File;

import jdk.internal.instrumentation.Logger;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SnowballDamage extends JavaPlugin implements Listener {

    private Logger logger;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SnowballDamage Version " + getDescription().getVersion() + ChatColor.GREEN + " enabled!");
        this.logger.info("Snowball Damage Enabled");
        if (!(new File(getDataFolder(), "config.yml")).exists()) {
            saveDefaultConfig();
            getConfig().set("Snowballs.Damage", 4.0D);
            saveConfig();
        }
        getServer().getPluginManager().registerEvents(this, (Plugin)this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SnowballDamage Version " + getDescription().getVersion() + ChatColor.GREEN + " disabled!");
        this.logger.info("Snowball Damage Disabled");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof org.bukkit.entity.Snowball) {
            e.setDamage(getConfig().getDouble("Snowballs.Damage"));
            e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, 80, 1);
        }
    }
}

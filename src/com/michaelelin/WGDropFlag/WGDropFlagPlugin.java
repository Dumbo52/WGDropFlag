package com.michaelelin.WGDropFlag;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;

public class WGDropFlagPlugin extends JavaPlugin {
    
    private WGDropFlagListener listener;

    public StateFlag ALLOW_DROPS;

    @Override
    public void onEnable() {
        WGCustomFlagsPlugin wgCustomFlagsPlugin = getPlugin("WGCustomFlags", WGCustomFlagsPlugin.class);
        WorldGuardPlugin worldGuardPlugin = getPlugin("WorldGuard", WorldGuardPlugin.class);

        if (wgCustomFlagsPlugin != null && worldGuardPlugin != null) {
            listener = new WGDropFlagListener(this, worldGuardPlugin);
            getServer().getPluginManager().registerEvents(listener, this);
            
            ALLOW_DROPS = new StateFlag("allow-drops", true);
            wgCustomFlagsPlugin.addCustomFlag(ALLOW_DROPS);
        }
    }

    private <T extends Plugin> T getPlugin(String name, Class<T> mainClass) {
        Plugin plugin = getServer().getPluginManager().getPlugin(name);
        if (plugin == null || !mainClass.isInstance(plugin)) {
            getLogger().warning("[" + getName() + "] " + name + " is required for this plugin to work; disabling.");
            getServer().getPluginManager().disablePlugin(this);
        }
        return mainClass.cast(plugin);
    }

}

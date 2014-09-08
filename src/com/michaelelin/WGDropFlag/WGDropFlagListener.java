package com.michaelelin.WGDropFlag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

public class WGDropFlagListener implements Listener {
    
    private WGDropFlagPlugin plugin;
    private WorldGuardPlugin worldguard;
    
    public WGDropFlagListener(WGDropFlagPlugin plugin, WorldGuardPlugin worldguard) {
        this.plugin = plugin;
        this.worldguard = worldguard;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemSpawn(ItemSpawnEvent event) {
        ApplicableRegionSet setAtLocation = worldguard.getGlobalRegionManager().get(event.getLocation().getWorld()).getApplicableRegions(event.getLocation());
        if (!setAtLocation.allows(plugin.ALLOW_DROPS)) {
            event.setCancelled(true);
        }
    }
}

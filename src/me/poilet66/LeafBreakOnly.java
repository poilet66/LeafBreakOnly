package me.poilet66;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class LeafBreakOnly extends JavaPlugin implements Listener {

	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		Block b = (Block) e.getBlock();
		
		if ((b.getType() == Material.LEAVES) && (p.getGameMode() == GameMode.SURVIVAL)) {
			// Make this an and instead
			b.setType(Material.AIR);
			
		}
		else if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = (Player) e.getPlayer();
		String name = e.getPlayer().getName();
		if (p instanceof Player) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spec on " + name);
					System.out.println("Spectate Sent for " + name);
				}
			}, (5));
		}
	}
}

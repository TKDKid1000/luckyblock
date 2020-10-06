package io.github.tkdkid1000;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LuckyBlock implements Listener, CommandExecutor {
	// lucky block give command
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // run if no arguments provided
            if (args.length == 0) {
            	player.sendMessage(ChatColor.YELLOW + "Giving " + player.getName() + " 1 Lucky Block!");
                ItemStack item = new ItemStack(Material.SPONGE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.YELLOW + "Lucky Block");
                player.getInventory().addItem(item);
            }
            // run if 1 argument provided
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
            	player.sendMessage(ChatColor.YELLOW + "Giving " + target.getName() + " 1 Lucky Block!");
                ItemStack item = new ItemStack(Material.SPONGE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.YELLOW + "Lucky Block");
                target.getInventory().addItem(item);
            }
            // run if 2 arguments provided
            if (args.length == 2) {
            	Player target = Bukkit.getPlayer(args[0]);
                int amount = Integer.parseInt(args[1]);
            	player.sendMessage(ChatColor.YELLOW + "Giving " + target.getName() + " " + amount + " Lucky Blocks!");
                ItemStack item = new ItemStack(Material.SPONGE, amount);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.YELLOW + "Lucky Block");
                target.getInventory().addItem(item);
            }
        }
        return true;
    }
	// define event
	@EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        World world = player.getWorld();
        
        // get action to choose if block is opened, or custom item used
        // lucky block
        if (action.equals(Action.LEFT_CLICK_BLOCK)) {
            if (block.getType().equals(Material.SPONGE)) {
            	Random rand = new Random();
            	int luckyblocktype = rand.nextInt(12);
            	// all possible outcomes of lucky block
            	// luckyblock type 0, zombie summoning
            	if (luckyblocktype == 0) {
            		world.spawnEntity(block.getLocation(), EntityType.ZOMBIE);
            		player.sendMessage(ChatColor.DARK_GREEN + "The Zombie rises from the grave.");
            		player.sendMessage(ChatColor.WHITE + "[Zombie] Grrrrr");
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 1, diamond placing
            	if (luckyblocktype == 1) {
            		block.setType(Material.DIAMOND_BLOCK);
            		player.sendMessage(ChatColor.AQUA + "Shiny!");
            	}
            	// luckyblock type 2, cat
            	if (luckyblocktype == 2) {
            		world.spawnEntity(block.getLocation(), EntityType.CAT);
            		ItemStack fish = new ItemStack(Material.COD, 5);
            		player.getInventory().addItem(fish);
            		player.sendMessage(ChatColor.WHITE + "[Cat] Meow");
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 3, xp bottles
            	if (luckyblocktype == 3) {
            		for (int i=0; i<5; i++) {
            			world.spawnEntity(block.getLocation(), EntityType.THROWN_EXP_BOTTLE);
            		}
            		player.sendMessage(ChatColor.GREEN + "Ca Ching");
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 4, tnt summoning
            	if (luckyblocktype == 4) {
            		world.spawnEntity(block.getLocation(), EntityType.PRIMED_TNT);
            		world.spawnEntity(block.getLocation(), EntityType.PRIMED_TNT);
            		world.spawnEntity(block.getLocation(), EntityType.PRIMED_TNT);
            		player.sendMessage(ChatColor.DARK_RED + "fizzzzzzz");
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 5, diamond sword
            	if (luckyblocktype == 5) {
            		ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD);
            		world.dropItemNaturally(block.getLocation(), diamondsword);
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 6, elytra
            	if (luckyblocktype == 6) {
            		ItemStack elytra = new ItemStack(Material.ELYTRA);
            		world.dropItemNaturally(block.getLocation(), elytra);
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 7, fireball giving
            	if (luckyblocktype == 7) {
            		ItemStack blazerod = new ItemStack(Material.FIRE_CHARGE);
            		for (int x=0; x<5; x++) {
            			world.dropItemNaturally(block.getLocation(), blazerod);
            		}
            		player.sendMessage(ChatColor.GOLD + "Right click to shoot a fireball!");
            		block.setType(Material.AIR);
            	}
            	// luckyblock type 8, bedrock placing
            	if (luckyblocktype == 8) {
            		block.setType(Material.BEDROCK);
            		player.sendMessage(ChatColor.DARK_GRAY + "Welp what are you gonna do now?");
            	}
            	// luckyblock type 9, beacon
            	if (luckyblocktype == 9) {
            		block.setType(Material.BEDROCK);
            		Location loc = block.getLocation();
            		loc.add(0, 0, 0).getBlock().setType(Material.BEACON);
            		loc = block.getLocation();
            		loc.add(0, -1, 0).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(1, -1, 1).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(1, -1, -1).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(1, -1, 0).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(-1, -1, 1).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(-1, -1, -1).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(-1, -1, 0).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(0, -1, 1).getBlock().setType(Material.IRON_BLOCK);
            		loc = block.getLocation();
            		loc.add(0, -1, -1).getBlock().setType(Material.IRON_BLOCK);
            		Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " is located at " + block.getX() + " " + " " + block.getY() + " " + block.getZ());
            	}
            	// luckyblock type 10, mlg water
            	if (luckyblocktype == 10) {
            		player.sendMessage(ChatColor.BLUE + "Quick! MLG Water");
            		ItemStack water = new ItemStack(Material.WATER_BUCKET);
            		player.getInventory().addItem(water);
            		Location loc = block.getLocation().add(0, 100, 0);
            		player.teleport(loc);
            	}
            	if (luckyblocktype == 11) {
            		rand = new Random();
            		double randx = rand.nextInt(50);
            		rand = new Random();
            		double randz = rand.nextInt(50);
            		Location loc = 	new Location(world, block.getX(), 0, block.getZ());
            		loc.add(randx, 10, randz);
            		loc.getBlock().setType(Material.RED_WOOL);
                    loc.subtract(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
                    loc.subtract(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
                    loc.subtract(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
                    loc.subtract(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
                    loc.subtract(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
                    ItemStack compass = new ItemStack(Material.COMPASS);
                    player.getInventory().addItem(compass);
                    player.sendMessage(ChatColor.RED + "Go to Y=10 then follow your compass to find the treasure.");
                    player.setCompassTarget(loc);
            		
            	}
            } else {
                return;
            }
        }
        // custom items
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
        	ItemStack helditem = player.getPlayer().getInventory().getItemInMainHand();
        	if (helditem.getType() == Material.FIRE_CHARGE) {
                player.launchProjectile(Fireball.class);
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        	}
        }
    }
}

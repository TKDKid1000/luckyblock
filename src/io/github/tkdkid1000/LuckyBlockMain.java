package io.github.tkdkid1000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class LuckyBlockMain extends JavaPlugin {
    @Override
    public void onEnable() {
    	// enable events and commands
        getServer().getPluginManager().registerEvents(new LuckyBlock(), this);
        this.getCommand("luckyblock").setExecutor(new LuckyBlock());
        // define recipe itemstack and itemmeta
        ItemStack item = new ItemStack(Material.SPONGE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Lucky Block");
        item.setItemMeta(meta);
        // define custom recipe
        NamespacedKey key = new NamespacedKey(this, "lucky_block");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("GGG", "GDG", "GGG");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('D', Material.DISPENSER);
        Bukkit.addRecipe(recipe);
    }
    
    @Override
    public void onDisable() {

    } 
}


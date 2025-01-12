package loyfael.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CookingManager {
  private static final String INVENTORY_TITLE = "Cooker";
  private final Map<Material, Material> cookingMap = new HashMap<>();

  public CookingManager() {
    initializeCookingMap();
  }

  public void openCookerInventory(Player player) {
    Inventory inventory = Bukkit.createInventory(null, 27, INVENTORY_TITLE);

    ItemStack confirmButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    ItemMeta meta = confirmButton.getItemMeta();
    if (meta != null) {
      meta.setDisplayName("§aConfirmer");
      confirmButton.setItemMeta(meta);
    }
    inventory.setItem(26, confirmButton);

    player.openInventory(inventory);
  }

  public boolean cookItems(Player player, Inventory inventory) {
    boolean itemsCooked = false;

    for (int i = 0; i < 26; i++) {
      ItemStack item = inventory.getItem(i);
      if (item == null || !cookingMap.containsKey(item.getType())) continue;

      Material cookedMaterial = cookingMap.get(item.getType());
      player.getInventory().addItem(new ItemStack(cookedMaterial, item.getAmount()));
      inventory.setItem(i, null);
      itemsCooked = true;
    }

    if (itemsCooked) {
      player.sendMessage("§aVos objets ont été cuits. Vous pourrez refaire la commande dans §2§l15 minutes§a.");
      player.playSound(player.getLocation(), "minecraft:block.lava.extinguish", 1.0f, 1.0f);
    } else {
      player.sendMessage("§cAucun item n'a été cuit !");
    }

    return itemsCooked;
  }

  public Map<Material, Material> getCookingMap() {
    return cookingMap;
  }

  private void initializeCookingMap() {
    /*
     * FOOD
     */
    cookingMap.put(Material.BEEF, Material.COOKED_BEEF);
    cookingMap.put(Material.CHICKEN, Material.COOKED_CHICKEN);
    cookingMap.put(Material.PORKCHOP, Material.COOKED_PORKCHOP);
    cookingMap.put(Material.MUTTON, Material.COOKED_MUTTON);
    cookingMap.put(Material.RABBIT, Material.COOKED_RABBIT);
    cookingMap.put(Material.POTATO, Material.BAKED_POTATO);
    cookingMap.put(Material.KELP, Material.DRIED_KELP);
    cookingMap.put(Material.COD, Material.COOKED_COD);
    cookingMap.put(Material.SALMON, Material.COOKED_SALMON);

    /*
     * ORES
     */
    cookingMap.put(Material.IRON_ORE, Material.IRON_INGOT);
    cookingMap.put(Material.DEEPSLATE_IRON_ORE, Material.IRON_INGOT);
    cookingMap.put(Material.COPPER_ORE, Material.COPPER_INGOT);
    cookingMap.put(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_INGOT);
    cookingMap.put(Material.DEEPSLATE_GOLD_ORE, Material.IRON_INGOT);
    cookingMap.put(Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD);
    cookingMap.put(Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND);
    cookingMap.put(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_LAZULI);
    cookingMap.put(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE);
    cookingMap.put(Material.DEEPSLATE_COAL_ORE, Material.COAL);
    cookingMap.put(Material.GOLD_ORE, Material.GOLD_INGOT);
    cookingMap.put(Material.LAPIS_ORE, Material.LAPIS_LAZULI);
    cookingMap.put(Material.REDSTONE_ORE, Material.REDSTONE);
    cookingMap.put(Material.EMERALD_ORE, Material.EMERALD);
    cookingMap.put(Material.DIAMOND_ORE, Material.DIAMOND);
    cookingMap.put(Material.NETHER_QUARTZ_ORE, Material.QUARTZ);
    cookingMap.put(Material.COAL_ORE, Material.COAL);

    /*
     * BUILD BLOCKS
     */
    cookingMap.put(Material.RAW_IRON, Material.IRON_INGOT);
    cookingMap.put(Material.RAW_COPPER, Material.COPPER_INGOT);
    cookingMap.put(Material.RAW_GOLD, Material.GOLD_INGOT);
    cookingMap.put(Material.RAW_IRON_BLOCK, Material.IRON_BLOCK);
    cookingMap.put(Material.RAW_COPPER_BLOCK, Material.COPPER_BLOCK);
    cookingMap.put(Material.RAW_GOLD_BLOCK, Material.GOLD_BLOCK);
    cookingMap.put(Material.COBBLESTONE, Material.STONE);
    cookingMap.put(Material.SAND, Material.GLASS);
    cookingMap.put(Material.RED_SAND, Material.GLASS);
    cookingMap.put(Material.CLAY_BALL, Material.BRICK);
    cookingMap.put(Material.NETHERRACK, Material.NETHER_BRICK);
    cookingMap.put(Material.WHITE_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
    cookingMap.put(Material.ORANGE_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA);
    cookingMap.put(Material.MAGENTA_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA);
    cookingMap.put(Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
    cookingMap.put(Material.YELLOW_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA);
    cookingMap.put(Material.LIME_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA);
    cookingMap.put(Material.PINK_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA);
    cookingMap.put(Material.GRAY_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA);
    cookingMap.put(Material.LIGHT_GRAY_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
    cookingMap.put(Material.CYAN_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA);
    cookingMap.put(Material.PURPLE_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA);
    cookingMap.put(Material.BLUE_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA);
    cookingMap.put(Material.BROWN_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA);
    cookingMap.put(Material.GREEN_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA);
    cookingMap.put(Material.RED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA);
    cookingMap.put(Material.BLACK_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA);
    cookingMap.put(Material.SANDSTONE, Material.SMOOTH_SANDSTONE);
    cookingMap.put(Material.RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE);
    cookingMap.put(Material.STONE_BRICKS, Material.CRACKED_STONE_BRICKS);

    /*
     * COLORING
     */
    cookingMap.put(Material.CACTUS, Material.GREEN_DYE);
    cookingMap.put(Material.SEA_PICKLE, Material.LIME_DYE);

    /*
     * CHARCOAL
     */
    cookingMap.put(Material.OAK_LOG, Material.CHARCOAL);
    cookingMap.put(Material.BIRCH_LOG, Material.CHARCOAL);
    cookingMap.put(Material.SPRUCE_LOG, Material.CHARCOAL);
    cookingMap.put(Material.JUNGLE_LOG, Material.CHARCOAL);
    cookingMap.put(Material.ACACIA_LOG, Material.CHARCOAL);
    cookingMap.put(Material.DARK_OAK_LOG, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_OAK_LOG, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_BIRCH_LOG, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_SPRUCE_LOG, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_JUNGLE_LOG, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_ACACIA_LOG, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_DARK_OAK_LOG, Material.CHARCOAL);
    cookingMap.put(Material.OAK_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.BIRCH_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.SPRUCE_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.JUNGLE_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.ACACIA_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.DARK_OAK_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_OAK_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_BIRCH_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_SPRUCE_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_JUNGLE_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_ACACIA_WOOD, Material.CHARCOAL);
    cookingMap.put(Material.STRIPPED_DARK_OAK_WOOD, Material.CHARCOAL);
  }
}

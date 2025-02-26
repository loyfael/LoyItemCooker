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
    player.openInventory(inventory);
  }

  public boolean cookItems(Player player, Inventory inventory) {
    boolean itemsCooked = false;
    Map<ItemStack, Integer> unprocessedItems = new HashMap<>();

    for (int i = 0; i < inventory.getSize(); i++) {
      ItemStack item = inventory.getItem(i);
      if (item == null) continue;

      Material type = item.getType();
      if (cookingMap.containsKey(type)) {
        Material cookedMaterial = cookingMap.get(type);
        player.getInventory().addItem(new ItemStack(cookedMaterial, item.getAmount()));
        itemsCooked = true;
      } else {
        unprocessedItems.put(item.clone(), item.getAmount());
      }
      inventory.setItem(i, null);
    }

    if (!unprocessedItems.isEmpty()) {
      for (var entry : unprocessedItems.entrySet()) {
        ItemStack item = entry.getKey();
        item.setAmount(entry.getValue());
        player.getInventory().addItem(item);
      }
      player.sendMessage("§eCertains items n'ont pas pu être cuits et ont été retournés dans votre inventaire.");
    }

    return itemsCooked; // Retourne true si des items ont été cuits
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
    cookingMap.put(Material.RAW_IRON, Material.IRON_INGOT);
    cookingMap.put(Material.RAW_COPPER, Material.COPPER_INGOT);
    cookingMap.put(Material.RAW_GOLD, Material.GOLD_INGOT);
    cookingMap.put(Material.RAW_IRON_BLOCK, Material.IRON_BLOCK);
    cookingMap.put(Material.RAW_COPPER_BLOCK, Material.COPPER_BLOCK);
    cookingMap.put(Material.RAW_GOLD_BLOCK, Material.GOLD_BLOCK);
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
    cookingMap.put(Material.STONE, Material.SMOOTH_STONE);
    cookingMap.put(Material.QUARTZ_BLOCK, Material.SMOOTH_QUARTZ);
    cookingMap.put(Material.BASALT, Material.POLISHED_BASALT);

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

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
  public final Map<Material, Material> COOKING_MAP = new HashMap<>();

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

  public void cookItems(Player player, Inventory inventory) {
    for (int i = 0; i < 26; i++) {
      ItemStack item = inventory.getItem(i);
      if (item == null || !COOKING_MAP.containsKey(item.getType())) continue;

      Material cookedMaterial = COOKING_MAP.get(item.getType());
      player.getInventory().addItem(new ItemStack(cookedMaterial, item.getAmount()));
    }
    player.sendMessage("§aVos items ont été cuits !");
  }

  public boolean isCookable(Material material) {
    return COOKING_MAP.containsKey(material);
  }

  private void initializeCookingMap() {

    COOKING_MAP.put(Material.BEEF, Material.COOKED_BEEF);
    COOKING_MAP.put(Material.CHICKEN, Material.COOKED_CHICKEN);
    COOKING_MAP.put(Material.PORKCHOP, Material.COOKED_PORKCHOP);
    COOKING_MAP.put(Material.MUTTON, Material.COOKED_MUTTON);
    COOKING_MAP.put(Material.RABBIT, Material.COOKED_RABBIT);
    COOKING_MAP.put(Material.POTATO, Material.BAKED_POTATO);
    COOKING_MAP.put(Material.KELP, Material.DRIED_KELP);
    COOKING_MAP.put(Material.COD, Material.COOKED_COD);
    COOKING_MAP.put(Material.SALMON, Material.COOKED_SALMON);

    /*
     * ORES
     */
    COOKING_MAP.put(Material.IRON_ORE, Material.IRON_INGOT);
    COOKING_MAP.put(Material.DEEPSLATE_IRON_ORE, Material.IRON_INGOT);
    COOKING_MAP.put(Material.COPPER_ORE, Material.COPPER_INGOT);
    COOKING_MAP.put(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_INGOT);
    COOKING_MAP.put(Material.DEEPSLATE_GOLD_ORE, Material.IRON_INGOT);
    COOKING_MAP.put(Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD);
    COOKING_MAP.put(Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND);
    COOKING_MAP.put(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_LAZULI);
    COOKING_MAP.put(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE);
    COOKING_MAP.put(Material.DEEPSLATE_COAL_ORE, Material.COAL);
    COOKING_MAP.put(Material.GOLD_ORE, Material.GOLD_INGOT);
    COOKING_MAP.put(Material.LAPIS_ORE, Material.LAPIS_LAZULI);
    COOKING_MAP.put(Material.REDSTONE_ORE, Material.REDSTONE);
    COOKING_MAP.put(Material.EMERALD_ORE, Material.EMERALD);
    COOKING_MAP.put(Material.DIAMOND_ORE, Material.DIAMOND);
    COOKING_MAP.put(Material.NETHER_QUARTZ_ORE, Material.QUARTZ);
    COOKING_MAP.put(Material.COAL_ORE, Material.COAL);

    /*
     * BUILD BLOCKS
     */
    COOKING_MAP.put(Material.COBBLESTONE, Material.STONE);
    COOKING_MAP.put(Material.SAND, Material.GLASS);
    COOKING_MAP.put(Material.RED_SAND, Material.GLASS);
    COOKING_MAP.put(Material.CLAY_BALL, Material.BRICK);
    COOKING_MAP.put(Material.NETHERRACK, Material.NETHER_BRICK);
    COOKING_MAP.put(Material.WHITE_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.ORANGE_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.MAGENTA_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.YELLOW_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.LIME_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.PINK_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.GRAY_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.LIGHT_GRAY_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.CYAN_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.PURPLE_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.BLUE_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.BROWN_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.GREEN_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.RED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.BLACK_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA);
    COOKING_MAP.put(Material.SANDSTONE, Material.SMOOTH_SANDSTONE);
    COOKING_MAP.put(Material.RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE);
    COOKING_MAP.put(Material.STONE_BRICKS, Material.CRACKED_STONE_BRICKS);

    /*
     * COLORING
     */
    COOKING_MAP.put(Material.CACTUS, Material.GREEN_DYE);
    COOKING_MAP.put(Material.SEA_PICKLE, Material.LIME_DYE);

    /*
     * CHARCOAL
     */
    COOKING_MAP.put(Material.OAK_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.BIRCH_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.SPRUCE_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.JUNGLE_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.ACACIA_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.DARK_OAK_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_OAK_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_BIRCH_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_SPRUCE_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_JUNGLE_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_ACACIA_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_DARK_OAK_LOG, Material.CHARCOAL);
    COOKING_MAP.put(Material.OAK_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.BIRCH_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.SPRUCE_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.JUNGLE_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.ACACIA_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.DARK_OAK_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_OAK_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_BIRCH_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_SPRUCE_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_JUNGLE_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_ACACIA_WOOD, Material.CHARCOAL);
    COOKING_MAP.put(Material.STRIPPED_DARK_OAK_WOOD, Material.CHARCOAL);
  }
}

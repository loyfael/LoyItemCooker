package loyfael;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
    private static final String INVENTORY_TITLE = "Cooker";
    private static final Map<Material, Material> COOKING_MAP = new HashMap<>();

    @Override
    public void onEnable() {
        try {
            Bukkit.getPluginManager().registerEvents(this, this);
            Objects.requireNonNull(this.getCommand("furnace")).setExecutor((
                    sender, command, label, args
            ) -> {
                if (sender instanceof Player player) {
                    // Vérifie si le joueur a la permission
                    if (!player.hasPermission("itemcooker.use")) {
                        player.sendMessage("§cVous n'avez pas la permission d'exécuter cette commande.");
                        return true; // Empêche l'affichage du message d'utilisation
                    }

                    // Ouvre l'inventaire du Cooker
                    openCookerInventory(player);
                    player.sendMessage("§aFour virtuel ouvert !");
                    return true;
                }

                sender.sendMessage("Cette commande ne peut être utilisée que par les joueurs.");
                return true; // Empêche l'affichage du message d'utilisation
            });

            getLogger().info("[CookerPlugin] Enabling in progress...");

            /*
            * FOOD
            */
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

            getLogger().info("[CookerPlugin] Enabled successfully!");
        } catch (Exception e) {
            getLogger().severe("[CookerPlugin] An error occurred during plugin initialization: " + e.getMessage());
        }
    }


    @Override
    public void onDisable() {
        try {
            COOKING_MAP.clear();
            getLogger().info("CookerPlugin Disabled!");
        } catch (Exception e) {
            getLogger().severe("An error occurred during plugin shutdown: " + e.getMessage());
        }
    }

    public void openCookerInventory(Player player) {
        try {
            Inventory inventory = Bukkit.createInventory(null, 27, INVENTORY_TITLE);

            // Add a confirm button
            ItemStack confirmButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta meta = confirmButton.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("§aConfirmer");
                confirmButton.setItemMeta(meta);
            }
            inventory.setItem(26, confirmButton);

            player.openInventory(inventory);
        } catch (Exception e) {
            getLogger().severe("An error occurred while opening the inventory for player " + player.getName() + ": " + e.getMessage());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            if (!(event.getWhoClicked() instanceof Player player)) return;

            // Vérifie si l'inventaire est celui du Cooker
            if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

            // Empêche l'interaction avec le bouton de confirmation
            if (event.getSlot() == 26) {
                event.setCancelled(true); // Annule uniquement pour le bouton de confirmation
                Inventory inventory = event.getClickedInventory();
                if (inventory != null) {
                    cookItems(player, inventory);
                }
                player.closeInventory();
                return;
            }

            // Vérifie si un item interdit est glissé dans l'inventaire du Cooker
            if (event.getClickedInventory() == event.getView().getTopInventory() && event.getCursor() != null) {
                ItemStack item = event.getCursor();
                if (!COOKING_MAP.containsKey(item.getType())) {
                    event.setCancelled(true); // Empêche l'ajout de l'item interdit
                    player.sendMessage("§cCet item ne peut pas être cuit !");
                }
            }
        } catch (Exception e) {
            getLogger().severe("An error occurred during inventory click event: " + e.getMessage());
        }
    }



    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        try {
            if (INVENTORY_TITLE.equals(event.getView().getTitle())) {
                getLogger().info("Inventory closed by " + event.getPlayer().getName());
            }
        } catch (Exception e) {
            getLogger().severe("An error occurred during inventory close event: " + e.getMessage());
        }
    }

    private void cookItems(Player player, Inventory inventory) {
        try {
            for (int i = 0; i < 26; i++) { // Process all slots except the confirm button
                ItemStack item = inventory.getItem(i);
                if (item == null || !COOKING_MAP.containsKey(item.getType())) continue;

                Material cookedMaterial = COOKING_MAP.get(item.getType());
                ItemStack cookedItem = new ItemStack(cookedMaterial, item.getAmount());
                player.getInventory().addItem(cookedItem);
            }

            // Play sound and notify player
            player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1.0f, 1.0f);
            player.sendMessage("§aVos items ont été cuits!");
        } catch (Exception e) {
            getLogger().severe("An error occurred while cooking items for player " + player.getName() + ": " + e.getMessage());
        }
    }
}



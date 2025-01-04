package loyfael.listeners;

import loyfael.cooking.CookingManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
  private static final String INVENTORY_TITLE = "Cooker";
  private final CookingManager cookingManager;

  public InventoryListener(CookingManager cookingManager) {
    this.cookingManager = cookingManager;
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player player)) return;

    // Vérifie si l'inventaire est celui du Cooker
    if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

    event.setCancelled(true); // Bloque les interactions par défaut

    // Si le bouton de confirmation est cliqué
    if (event.getSlot() == 26) {
      Inventory inventory = event.getClickedInventory();
      if (inventory != null) {
        cookItems(player, inventory);
        player.closeInventory();
      }
      return;
    }

    // Vérifie si un item interdit est glissé dans l'inventaire du Cooker
    if (event.getClickedInventory() == event.getView().getTopInventory() && event.getCursor() != null) {
      ItemStack item = event.getCursor();
      if (!cookingManager.getCookingMap().containsKey(item.getType())) {
        event.setCancelled(true); // Empêche l'ajout de l'item interdit
        player.sendMessage("§cCet item ne peut pas être cuit !");
      }
    }
  }

  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    if (INVENTORY_TITLE.equals(event.getView().getTitle())) {
      Player player = (Player) event.getPlayer();
      player.sendMessage("§aMerci d'avoir utilisé le Cooker !");
    }
  }

  private void cookItems(Player player, Inventory inventory) {
    boolean itemsCooked = false;

    for (int i = 0; i < 26; i++) { // Traite tous les slots sauf le bouton de confirmation
      ItemStack item = inventory.getItem(i);
      if (item == null || !cookingManager.getCookingMap().containsKey(item.getType())) continue;

      Material cookedMaterial = cookingManager.getCookingMap().get(item.getType());
      ItemStack cookedItem = new ItemStack(cookedMaterial, item.getAmount());
      player.getInventory().addItem(cookedItem);

      inventory.setItem(i, null); // Vide le slot après la cuisson
      itemsCooked = true;
    }

    if (itemsCooked) {
      player.playSound(player.getLocation(), "minecraft:block.lava.extinguish", 1.0f, 1.0f);
      player.sendMessage("§aVos items ont été cuits avec succès !");
    } else {
      player.sendMessage("§cAucun item valide à cuire !");
    }
  }
}

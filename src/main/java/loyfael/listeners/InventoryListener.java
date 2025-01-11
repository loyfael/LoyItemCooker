package loyfael.listeners;

import loyfael.utils.CookingManager;
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

    // Si le joueur clique sur le bouton de confirmation, annuler l'événement
    if (event.getSlot() == 26) {
      event.setCancelled(true); // Bloque l'interaction avec le bouton
      Inventory inventory = event.getClickedInventory();
      if (inventory != null) {
        cookingManager.cookItems(player, inventory);
      }
      player.closeInventory();
      return;
    }

    // Permet de déplacer des items à l'intérieur de l'inventaire sauf le bouton de confirmation
    if (event.getClickedInventory() == event.getView().getTopInventory() && event.getSlot() != 26) {
      return; // Autorise les clics pour déplacer des items
    }

    // Bloque par défaut les interactions non autorisées
    event.setCancelled(true);
  }

  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    if (INVENTORY_TITLE.equals(event.getView().getTitle())) {
      ((Player) event.getPlayer()).sendMessage("§aMerci d'avoir utilisé le Cooker !");
    }
  }
}

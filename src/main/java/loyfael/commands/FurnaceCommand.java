package loyfael.commands;

import loyfael.cooking.CookingManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class FurnaceCommand implements CommandExecutor {
  private static final String PERMISSION_FURNACE = "itemcooker.use";
  private static final long COOLDOWN_TIME = 15; // Cooldown en minutes
  public final CookingManager cookingManager;

  public FurnaceCommand(CookingManager cookingManager) {
    this.cookingManager = cookingManager;
  }

  @Override
  public boolean onCommand(
          @NotNull CommandSender sender,
          @NotNull Command command,
          @NotNull String label,
          @NotNull String[] args
  ) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("Cette commande est réservée aux joueurs.");
      return true;
    }

    LuckPerms luckPerms = LuckPermsProvider.get();
    User user = luckPerms.getUserManager().getUser(player.getUniqueId());

    if (user == null) {
      player.sendMessage("§cImpossible de récupérer vos informations LuckPerms.");
      return true;
    }

    // Vérifie si le joueur est en cooldown
    if (!user.getCachedData().getPermissionData().checkPermission(PERMISSION_FURNACE).asBoolean()) {
      player.sendMessage("§cVous devez attendre avant d'utiliser cette commande.");
      return true;
    }

    // Ajoute un cooldown de 45 minutes
    Node cooldownNode = PermissionNode.builder(PERMISSION_FURNACE)
            .value(false)
            .expiry(COOLDOWN_TIME, TimeUnit.MINUTES)
            .build();
    user.data().add(cooldownNode);

    luckPerms.getUserManager().saveUser(user);

    // Ouvre l'inventaire du four virtuel
    player.sendMessage("§aFour virtuel ouvert !");
    Bukkit.dispatchCommand(player, "furnace");
    return true;
  }
}

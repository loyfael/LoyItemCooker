package loyfael.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LuckPermsUtils {
  private static final LuckPerms luckPerms = LuckPermsProvider.get();

  // Check if the player is in cooldown
  public static boolean isInCooldown(Player player, String permission) {
    User user = luckPerms.getUserManager().getUser(player.getUniqueId());
    // If the user is not found, we consider that he does not have the permission
    if (user == null) return false;

    return !user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
  }

  // Get the remaining time of the cooldown
  public static long getRemainingCooldown(Player player, String permission) {
    User user = luckPerms.getUserManager().getUser(player.getUniqueId());
    if (user == null) return 0;

    // We retrieve the first node that matches the permission and is not expired
    return user.data().toCollection().stream()
            .filter(node -> node.getKey().equalsIgnoreCase(permission) && Objects.requireNonNull(node.getExpiry()).isAfter(Instant.now()))
            .mapToLong(node -> node.getExpiry().getEpochSecond() - System.currentTimeMillis() / 1000).findFirst().orElse(0);
  }

  // Set the cooldown for the player
  public static void setCooldown(Player player, String permission, long minutes) {
    User user = luckPerms.getUserManager().getUser(player.getUniqueId());
    if (user == null) return;

    // We create a new node with the permission, the value false and the expiry time
    Node node = PermissionNode.builder(permission).value(false).expiry(minutes, TimeUnit.MINUTES).build();
    user.data().add(node);
    luckPerms.getUserManager().saveUser(user);
  }
}

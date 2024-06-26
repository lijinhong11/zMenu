package fr.maxlego08.menu.requirement.permissible;

import fr.maxlego08.menu.MenuItemStack;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.enums.ItemVerification;
import fr.maxlego08.menu.api.requirement.Action;
import fr.maxlego08.menu.api.requirement.permissible.ItemPermissible;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.inventory.inventories.InventoryDefault;
import fr.maxlego08.menu.requirement.ZPermissible;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ZItemPermissible extends ZPermissible implements ItemPermissible {

    private final ItemVerification itemVerification;
    private final MenuItemStack menuItemStack;
    private final int amount;

    public ZItemPermissible(MenuItemStack menuItemStack, int amount, List<Action> denyActions, List<Action> successActions, ItemVerification itemVerification) {
        super(denyActions, successActions);
        this.menuItemStack = menuItemStack;
        this.amount = amount;
        this.itemVerification = itemVerification;
    }

    @Override
    public boolean hasPermission(Player player, Button button, InventoryDefault inventoryDefault, Placeholders placeholders) {

        if (this.menuItemStack == null) {
            return true;
        }

        PlayerInventory inventory = player.getInventory();
        int items = 0;
        ItemStack itemStack = menuItemStack.build(player, false, placeholders);

        for (int slot = 0; slot != 36; slot++) {
            ItemStack currentItemStack = inventory.getContents()[slot];
            if (currentItemStack != null) {
                ItemMeta itemMeta = currentItemStack.getItemMeta();
                switch (this.itemVerification) {
                    case SIMILAR:
                        if (currentItemStack.isSimilar(itemStack)) {
                            items += currentItemStack.getAmount();
                        }
                        break;
                    case MODELID:
                        if (itemMeta != null && itemMeta.hasCustomModelData() && String.valueOf(itemMeta.getCustomModelData()).equals(this.menuItemStack.getModelID())) {
                            items += currentItemStack.getAmount();
                        }
                        break;
                }
            }
        }

        return items >= this.amount;
    }

    @Override
    public boolean isValid() {
        return this.menuItemStack != null;
    }

    @Override
    public MenuItemStack getMenuItemStack() {
        return this.menuItemStack;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

}

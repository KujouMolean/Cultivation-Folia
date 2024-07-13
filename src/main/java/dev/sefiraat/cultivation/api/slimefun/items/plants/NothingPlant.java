package dev.sefiraat.cultivation.api.slimefun.items.plants;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import dev.sefiraat.cultivation.api.slimefun.plant.Growth;
import dev.sefiraat.cultivation.api.slimefun.plant.PlantTheme;
import dev.sefiraat.cultivation.implementation.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.papermc.lib.PaperLib;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A plant that does nothing by itself. Used for breeding purposes only.
 */
public class NothingPlant extends CultivationPlant {

    @ParametersAreNonnullByDefault
    public NothingPlant(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Growth growth) {
        super(item, recipeType, recipe, growth);
    }

    @ParametersAreNonnullByDefault
    public NothingPlant(SlimefunItemStack item, Growth growth) {
        super(item, growth);
    }

    @Override
    public void updateGrowthStage(@Nonnull Block block, int growthStage) {
        // todo Fuck numbers
        if (growthStage == 0) {
            PlantTheme theme = growth.getTheme();
            if (theme != null) {
                PlayerHead.setSkin(block, theme.getSeed().getPlayerSkin(), false);
                PaperLib.getBlockState(block, false).getState().update(true, false);
                StorageCacheUtils.setData(block.getLocation(), Keys.FLORA_GROWTH_STAGE, String.valueOf(growthStage));
                growthDisplay(block.getLocation());
            }
        } else if (growthStage == 1) {
            addDisplayPlant(block.getLocation());
            block.setType(Material.AIR);
        }
        StorageCacheUtils.setData(block.getLocation(), Keys.FLORA_GROWTH_STAGE, String.valueOf(growthStage));
    }

    @Override
    protected boolean validateFlora() {
        return true;
    }
}

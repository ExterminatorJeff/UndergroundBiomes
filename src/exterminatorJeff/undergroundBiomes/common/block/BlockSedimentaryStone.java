package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.item.ItemFossilPiece;

public class BlockSedimentaryStone extends BlockMetadataBase
{
    private static final float[] hardness = {0.5f, 0.5f, 0.5f, 0.6f, 0.5f, 0.5f, 1.0f, 0.9f};
    private static final float[] resistance = {0.29f, 0.29f, 0.29f, 0.4f, 0.29f, 0.29f, 1.0f, 0.86f};
    private static final String[] blockName = {
        "limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"
    };

    public BlockSedimentaryStone(int id)
    {
        super(id);
    }
    
    public float getBlockHardness(int meta)
    {
        return 1.5f * hardness[meta];
    }

    public float getBlockExplosionResistance(int meta)
    {
        return 6.0f * resistance[meta];
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y)
    {
        // Rare drops
        if ((metadata < 8) && (random.nextInt(64) <= fortune))
        {
            // Shale drops clay
            if (metadata == 2)
            {
                return new ItemStack(Item.clay, 1, 0);
            }
            // Limestone, chalk, siltstone, lignite and dolomite drop fossil pieces
            if (metadata == 0 || metadata == 1 || metadata == 3 || metadata == 4 || metadata == 5)
            {
                return new ItemStack(UndergroundBiomes.fossilPiece.itemID, 1, random.nextInt(ItemFossilPiece.TYPES));
            }
            // Chert drops flint item
            if (metadata == 7)
            {
                return new ItemStack(Item.flint, 1, 0);
            }
        }
        if ((metadata & 7) == 4) return new ItemStack(UndergroundBiomes.ligniteCoal.itemID, 1, 0);
        return new ItemStack(this.blockID, 1, metadata & 7);
    }

    public boolean hasRareDrops()
    {
        return true;
    }

    public String getBlockTypeName(int index)
    {
        return blockName[index & 7];
    }
}

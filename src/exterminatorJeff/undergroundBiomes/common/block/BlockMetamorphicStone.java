package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStone extends BlockMetadataBase
{
    private static final float[] hardness = {1.1f, 1.0f, 1.1f, 1.3f, 0.7f, 0.7f, 0.4f, 0.9f};
    private static final float[] resistance = {1.11f, 1.0f, 1.11f, 1.26f, 0.54f, 0.54f, 0.2f, 0.86f};
    private static final String[] blockName = {
        "gneiss", "eclogite", "marble", "quartzite", "blueschist", "greenschist", "soapstone", "migmatite"
    };

    public BlockMetamorphicStone(int id, int texture)
    {
        super(id, texture);
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
        // Very rare drops
        if ((metadata < 8) && (random.nextInt(1024) <= fortune))
        {
            if ((y < 31) && (random.nextInt(3) == 0))
            {
                // Lapis lazuli
                return new ItemStack(Item.dyePowder, 1, 4);
            }
            if ((y < 16) && (random.nextInt(3) == 0))
            {
                // Redstone
                return new ItemStack(Item.redstone, 1, 0);
            }
        }
        return new ItemStack(UndergroundBiomes.metamorphicCobblestone.blockID, 1, metadata & 7);
    }

    public boolean hasRareDrops()
    {
        return true;
    }

    public String getBlockTypeName(int index)
    {
        return blockName[index & 7];
    }

    public String getBlockName(int index)
    {
        return getBlockTypeName(index);
    }
}

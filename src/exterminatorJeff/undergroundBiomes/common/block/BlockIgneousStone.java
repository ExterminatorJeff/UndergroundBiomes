package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousStone extends BlockMetadataBase
{
    private static final float[] hardness = {1.7f, 1.6f, 1.3f, 1.4f, 1.0f, 1.4f, 1.5f, 1.2f};
    private static final float[] resistance = {1.42f, 1.39f, 1.26f, 1.31f, 1.0f, 1.31f, 1.35f, 1.2f};
    private static final String[] blockName = {
        "redGranite", "blackGranite", "rhyolite", "andesite", "gabbro", "basalt", "komatiite", "dacite"
    };

    public BlockIgneousStone(int id)
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
        // Very rare drops
        if ((metadata < 8) && (random.nextInt(1024) <= fortune))
        {
            int num = UndergroundBiomes.nuggets.size();
            if (num > 0)
            {
                ItemStack stack = UndergroundBiomes.nuggets.get(random.nextInt(num));
                if ((stack.itemID != Item.goldNugget.itemID) || (y < 32))
                {
                    return stack;
                }
            }
        }
        return new ItemStack(UndergroundBiomes.igneousCobblestone.blockID, 1, metadata & 7);
    }

    public String getBlockTypeName(int index)
    {
        return blockName[index & 7];
    }
}

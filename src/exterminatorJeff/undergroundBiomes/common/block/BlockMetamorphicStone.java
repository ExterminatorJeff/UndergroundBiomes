package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import net.minecraft.item.ItemStack;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStone extends BlockMetadataBase
{
    private static final float[] hardness = {1.1f, 1.0f, 1.1f, 1.3f, 0.7f, 0.7f, 0.4f, 0.9f};
    private static final String[] blockName = {
        "gneiss", "eclogite", "marble", "quartzite", "blueschist", "greenschist", "soapstone", "migmatite"
    };

    public BlockMetamorphicStone(int id)
    {
        super(id);
    }
    
    public float getBlockHardness(int meta)
    {
        return 1.5f * hardness[meta];
    }

    public float getBlockExplosionResistance(int meta)
    {
        return 10.0f * hardness[meta];
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune)
    {
        return new ItemStack(UndergroundBiomes.metamorphicCobblestone.blockID, 1, metadata & 7);
    }

    public String getBlockName(int index)
    {
        return blockName[index & 7];
    }
}

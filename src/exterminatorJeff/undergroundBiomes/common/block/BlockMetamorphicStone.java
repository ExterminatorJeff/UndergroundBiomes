package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStone extends BlockMetadataBase
{
    private static final float[] hardness = {0.8f, 0.8f, 0.6f, 1.2f, 1.0f, 1.0f, 0.5f, 1.0f};
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

    public int idDropped(int metadata, Random random, int par3)
    {
        return UndergroundBiomes.metamorphicCobblestone.blockID;
    }

    public String getBlockName(int index)
    {
        return blockName[index & 7];
    }
}

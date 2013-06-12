package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousStone extends BlockMetadataBase
{
    private static final float[] hardness = {1.5f, 1.4f, 1.1f, 1.2f, 0.8f, 1.2f, 1.3f, 1.0f};
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
        return 10.0f * hardness[meta];
    }

    public int idDropped(int metadata, Random random, int par3)
    {
        return UndergroundBiomes.igneousCobblestone.blockID;
    }

    public String getBlockName(int index)
    {
        return blockName[index & 7];
    }
}

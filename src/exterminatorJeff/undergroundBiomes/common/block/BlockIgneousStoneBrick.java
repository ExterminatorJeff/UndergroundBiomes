package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

public class BlockIgneousStoneBrick extends BlockIgneousStone
{
    public BlockIgneousStoneBrick(int id)
    {
        super(id);
    }
    
    public int idDropped(int metadata, Random random, int par3)
    {
        return this.blockID;
    }

    public String getBlockName(int index)
    {
        return super.getBlockName(index) + "Brick";
    }
}

package exterminatorJeff.undergroundBiomes.common.block;

public class BlockMetamorphicCobblestone extends BlockMetamorphicStone
{
    public BlockMetamorphicCobblestone(int id)
    {
        super(id);
    }
    
    public float getBlockHardness(int meta)
    {
        return super.getBlockHardness(meta)*1.333333f;
    }

    public String getBlockName(int index)
    {
        return super.getBlockName(index) + "Cobble";
    }
}

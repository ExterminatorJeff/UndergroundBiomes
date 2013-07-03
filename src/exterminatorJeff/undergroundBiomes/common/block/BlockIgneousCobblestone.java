package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import net.minecraft.item.ItemStack;

public class BlockIgneousCobblestone extends BlockIgneousStone
{
    public BlockIgneousCobblestone(int id, int texture)
    {
        super(id, texture);
    }
    
    public float getBlockHardness(int meta)
    {
        return super.getBlockHardness(meta)*1.333333f;
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y)
    {
        return new ItemStack(this.blockID, 1, metadata & 7);
    }

    public boolean hasRareDrops()
    {
        return false;
    }

    public String getBlockName(int index)
    {
        return super.getBlockName(index) + "Cobble";
    }
}

package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;

import net.minecraft.item.ItemStack;

public class BlockMetamorphicStoneBrick extends BlockMetamorphicStone
{
    public BlockMetamorphicStoneBrick(int id, int texture)
    {
        super(id, texture);
    }
    
    public ItemStack itemDropped(int metadata, Random random, int fortune, int y)
    {
        return new ItemStack(this.blockID, 1, metadata & 7);
    }

    public String getBlockName(int index)
    {
        return super.getBlockName(index) + "Brick";
    }
}

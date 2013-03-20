package exterminatorJeff.undergroundBiomes.common;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class BlockMetamorphicStoneBrick extends BlockMetamorphicStone{

	public BlockMetamorphicStoneBrick(int id, int texture) {
		super(id, texture);
		// TODO Auto-generated constructor stub
	}
	
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        
        int count = quantityDropped(metadata, fortune, world.rand);
        for(int i = 0; i < count; i++)
        {
            int id = idDropped(metadata, world.rand, 0);
            if (id > 0)
            {
                ret.add(new ItemStack(UndergroundBiomes.metamorphicStoneBrickID, 1, damageDropped(metadata)));
            }
        }
        return ret;
    }
	

}

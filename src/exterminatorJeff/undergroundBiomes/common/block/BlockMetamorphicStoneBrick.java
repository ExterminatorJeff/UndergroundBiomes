package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;


public class BlockMetamorphicStoneBrick extends BlockMetamorphicStone{

	public BlockMetamorphicStoneBrick(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public String getBlockName(int index){
    	return super.getBlockName(index) + "Brick";
    }
	

}

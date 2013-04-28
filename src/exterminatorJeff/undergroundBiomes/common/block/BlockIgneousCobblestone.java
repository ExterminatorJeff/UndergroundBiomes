package exterminatorJeff.undergroundBiomes.common.block;

import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousCobblestone extends BlockIgneousStone{

	public BlockIgneousCobblestone(int par1, int texture) {
		super(par1, texture);
	}
	
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		return UndergroundBiomes.igneousStone.getBlockHardness(par1World, par2, par3, par4)-0.25f;
	}
	
    @Override
    public String getBlockName(int index){
    	return super.getBlockName(index) + "Cobble";
    }
	
	

}

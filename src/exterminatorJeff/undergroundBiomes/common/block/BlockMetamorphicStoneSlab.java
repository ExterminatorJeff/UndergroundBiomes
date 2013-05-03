package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStoneSlab extends BlockIgneousStoneSlab{

	public BlockMetamorphicStoneSlab(int par1, boolean par2) {
		super(par1, par2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		return UndergroundBiomes.metamorphicStoneBrick.getBlockHardness(par1World, par2, par3, par4)+0.25f;
	}
	
	@Override
    public int idPicked(World par1World, int par2, int par3, int par4){
    	return UndergroundBiomes.metamorphicBrickSlabHalfID;
    }
	
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if(isFullSlab){
        	ret.add(new ItemStack(UndergroundBiomes.metamorphicBrickSlabHalfID, 2, damageDropped(metadata)%8));
        }else{
        	ret.add(new ItemStack(UndergroundBiomes.metamorphicBrickSlabHalfID, 1, damageDropped(metadata)%8));
        }
        return ret;
    }
    
    public String getBlockName(int index){
    	String name = "";
    	switch(index){
		    case(0): name = "gneiss";
				break;
			case(1): name = "eclogite";
				break;
			case(2): name = "marble";
				break;
			case(3): name = "quartzite";
				break;
			case(4): name = "blueschist";
				break;
			case(5): name = "greenschist";
				break;
			case(6): name = "soapstone";
				break;
			case(7): name = "migmatite";
				break;
			default: name="default";
			
	    }
    	return name + "Brick";
	}

}

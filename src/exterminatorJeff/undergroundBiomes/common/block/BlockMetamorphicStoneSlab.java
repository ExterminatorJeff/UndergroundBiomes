package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStoneSlab extends BlockIgneousStoneSlab{

	public BlockMetamorphicStoneSlab(int par1, boolean par2, int texture) {
		super(par1, par2, texture);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		return UndergroundBiomes.metamorphicStoneBrick.getBlockHardness(par1World, par2, par3, par4)+0.25f;
	}
	
	@Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving){
    	if(par1World.getBlockId(par2, par3 - 1, par4) == UndergroundBiomes.metamorphicBrickSlabHalf.blockID){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4) & 7;
    		if(par1World.getBlockMetadata(par2, par3 - 1, par4) == metadata ){
	    		par1World.setBlockWithNotify(par2, par3, par4, 0);
	    		par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, UndergroundBiomes.metamorphicBrickSlabFull.blockID, metadata);
    		}
    	}
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

}

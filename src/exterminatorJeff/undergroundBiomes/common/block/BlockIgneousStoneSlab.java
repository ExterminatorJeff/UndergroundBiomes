package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockStep;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockIgneousStoneSlab extends BlockStep{
	
	boolean isFullSlab = false;
	
	public BlockIgneousStoneSlab(int par1, boolean par2, int texture){
		super(par1, par2);
		isFullSlab = par2;
		if(!par2){
			this.setCreativeTab(UndergroundBiomes.tabModBlocks);
		}else{
			this.setCreativeTab(null);
		}
		this.setRequiresSelfNotify();
		this.blockIndexInTexture = texture;
		this.useNeighborBrightness[par1] = true;
	}

	@Override
	public String getFullSlabName(int var1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getTextureFile(){
		return UndergroundBiomes.blockTextures;
	}
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		return UndergroundBiomes.igneousStoneBrick.getBlockHardness(par1World, par2, par3, par4)+0.25f;
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0; i < 8; i++){
    		par3List.add(new ItemStack(par1, 1, i));
    	}
       
    }
    
    @Override
	public int damageDropped (int metadata) {
		return metadata;
	}
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return this.blockIndexInTexture + (par2 & 7);
    }
    
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving){
    	if(par1World.getBlockId(par2, par3 - 1, par4) == UndergroundBiomes.igneousBrickSlabHalf.blockID){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4) & 7;
    		if(par1World.getBlockMetadata(par2, par3 - 1, par4) == metadata ){
	    		par1World.setBlockWithNotify(par2, par3, par4, 0);
	    		par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, UndergroundBiomes.igneousBrickSlabFull.blockID, metadata);
    		}
    	}
    }
    
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4){
    	return UndergroundBiomes.igneousBrickSlabHalfId;
    }
    
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMetadata(par2, par3, par4) & 7;
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if(isFullSlab){
        	ret.add(new ItemStack(UndergroundBiomes.igneousBrickSlabHalfId, 2, damageDropped(metadata)%8));
        }else{
        	ret.add(new ItemStack(UndergroundBiomes.igneousBrickSlabHalfId, 1, damageDropped(metadata)%8));
        }
        return ret;
    }

}

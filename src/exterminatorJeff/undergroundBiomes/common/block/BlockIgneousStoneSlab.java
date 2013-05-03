package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockStep;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousStoneSlab extends BlockStep{
	
	boolean isFullSlab = false;
	
	protected Icon[] textures = {null, null, null, null, null, null, null, null};
	
	public BlockIgneousStoneSlab(int par1, boolean par2){
		super(par1, par2);
		isFullSlab = par2;
		if(!par2){
			this.setCreativeTab(UndergroundBiomes.tabModBlocks);
		}else{
			this.setCreativeTab(null);
		}
		this.useNeighborBrightness[par1] = true;
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
	protected ItemStack createStackedBlock(int metadata) {
		return new ItemStack(UndergroundBiomes.igneousBrickSlabHalfId, 2, metadata & 7);
	}
    
    @Override
	public int damageDropped (int metadata) {
		return metadata;
	}
    
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return textures[par2 & 7];
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
    
	@Override
	public void registerIcons(IconRegister iconRegister){
    	for(int i = 0; i < 8; i++){
    		textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + getBlockName(i));
    	}
    }
	
	public String getBlockName(int index){
		String name = "";
    	switch(index){
    		case(0): name = "redGranite";
    			break;
    		case(1): name = "blackGranite";
    			break;
    		case(2): name = "rhyolite";
    			break;
    		case(3): name = "andesite";
    			break;
    		case(4): name = "gabbro";
    			break;
    		case(5): name = "basalt";
    			break;
    		case(6): name = "komatiite";
    			break;
    		case(7): name = "epidote";
    			break;
    		default: name="default";
    		
	    }
    	return name + "Brick";
	}

}

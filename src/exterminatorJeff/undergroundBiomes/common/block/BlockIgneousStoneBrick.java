package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousStoneBrick extends BlockIgneousStone{

	public BlockIgneousStoneBrick(int id, int texture) {
		super(id, texture);
	}
	
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		return UndergroundBiomes.igneousStone.getBlockHardness(par1World, par2, par3, par4)+0.25f;
	}

	
    
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0; i < 8; i++){
    		par3List.add(new ItemStack(par1, 1, i));
    	}
       
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }

    @Override
	public int damageDropped (int metadata) {
		return metadata;
	}
    
    @Override
    public String getBlockName(int index){
    	return super.getBlockName(index) + "Brick";
    }
    

}

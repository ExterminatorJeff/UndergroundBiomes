package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockIgneousStoneBrick extends BlockIgneousStone{

	public BlockIgneousStoneBrick(int id, int texture) {
		super(id, texture);
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.blockTextures;
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
	public int damageDropped (int metadata) {
		return metadata;
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
                ret.add(new ItemStack(UndergroundBiomes.igneousStoneBrickID, 1, damageDropped(metadata)));
            }
        }
        return ret;
    }
    

}

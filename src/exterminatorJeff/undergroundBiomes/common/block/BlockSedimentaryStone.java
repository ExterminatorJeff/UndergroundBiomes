package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockSedimentaryStone extends BlockMetadataBase{
	
	public BlockSedimentaryStone(int id){
		super(id, Material.rock);
		this.setHardness(1.5f);
		this.setResistance(10.0f);
		this.setCreativeTab(UndergroundBiomes.tabModBlocks);
	}
	
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
		switch(getDamageValue(world, x, y, z)){
		
			case 0: return UndergroundBiomes.resistanceFactor * 3;
					
			case 1: return UndergroundBiomes.resistanceFactor * 3;
					
			case 2: return UndergroundBiomes.resistanceFactor * 3;
					
			case 3: return UndergroundBiomes.resistanceFactor * 4;
					
			case 4: return UndergroundBiomes.resistanceFactor * 3;
					
			case 5: return UndergroundBiomes.resistanceFactor * 7;
					
			case 6: return UndergroundBiomes.resistanceFactor * 6;
					
			case 7: return UndergroundBiomes.resistanceFactor * 6;
					
			default: getExplosionResistance(par1Entity);
		}
		return getExplosionResistance(par1Entity);
    }
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		int meta = this.getDamageValue(par1World, par2, par3, par4);
		switch(meta){
		case 0: return UndergroundBiomes.hardnessFactor * 3;
		
		case 1: return UndergroundBiomes.hardnessFactor * 3;
		
		case 2: return UndergroundBiomes.hardnessFactor * 3;
		
		case 3: return UndergroundBiomes.hardnessFactor * 4;
		
		case 4: return UndergroundBiomes.hardnessFactor * 3;
		
		case 5: return UndergroundBiomes.hardnessFactor * 7;
		
		case 6: return UndergroundBiomes.hardnessFactor * 6;
		
		case 7: return UndergroundBiomes.hardnessFactor * 6;
		
		default: return 1.5f;
		}
	}

    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0; i < 8; i++){
    		par3List.add(new ItemStack(par1, 1, i));
    	}
       
    }
    
    @Override
    public int idDropped(int metadata, Random par2Random, int par3)
    {
        return metadata == 4 ? UndergroundBiomes.ligniteCoal.itemID : this.blockID;
    }

    @Override
	public int damageDropped (int metadata) {
		return metadata == 4 ? 0 : metadata;
	}
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (fortune == 0 || meta != 4) return 1;
        // Lignite is affected by fortune: Fortune III gives up to 4 items
        int j = random.nextInt(fortune + 2);
        return (j < 1) ? 1 : j;
    }

    public String getBlockName(int index) {
    	String name = "";
    	switch(index){
    		case(0): name = "limestone";
    			break;
    		case(1): name = "chalk";
    			break;
    		case(2): name = "shale";
    			break;
    		case(3): name = "siltstone";
    			break;
    		case(4): name = "ligniteBlock";
    			break;
    		case(5): name = "flint";
    			break;
    		case(6): name = "greywacke";
    			break;
    		case(7): name = "chert";
    			break;
    		default: name="default";
    		
	    }
    	return name;
	}

}

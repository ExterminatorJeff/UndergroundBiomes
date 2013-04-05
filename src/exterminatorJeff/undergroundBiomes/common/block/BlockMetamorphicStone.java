package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStone extends BlockMetadataBase{
	
	public BlockMetamorphicStone(int id){
		super(id, Material.rock);
		this.setHardness(1.5f);
		this.setCreativeTab(UndergroundBiomes.tabModBlocks);
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.blockTextures;
	}
	
	public boolean isGenMineableReplaceable(World world, int x, int y, int z)
    {
        return true;
    }
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4){
		int meta = this.getDamageValue(par1World, par2, par3, par4);
		switch(meta){
		case 0: return UndergroundBiomes.hardnessFactor * 5;
		
		case 1: return UndergroundBiomes.hardnessFactor * 5;
		
		case 2: return UndergroundBiomes.hardnessFactor * 4;
		
		case 3: return UndergroundBiomes.hardnessFactor * 7;
		
		case 4: return UndergroundBiomes.hardnessFactor * 6;
		
		case 5: return UndergroundBiomes.hardnessFactor * 6;
		
		case 6: return UndergroundBiomes.hardnessFactor * 3;
		
		case 7: return UndergroundBiomes.hardnessFactor * 6;
		
		default: return 1.5f;
		}
	}

	

    
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int metadata){
        return textures[metadata];
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
                ret.add(new ItemStack(UndergroundBiomes.metamorphicCobblestoneID, 1, damageDropped(metadata)));
            }
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
    	return name;
	}

}

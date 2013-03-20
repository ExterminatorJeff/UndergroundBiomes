package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMetamorphicStoneSlab extends ItemIgneousStoneSlab{

	public ItemMetamorphicStoneSlab(int par1, Block block) {
		super(par1, block);
		// TODO Auto-generated constructor stub
	}
	
    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int par1)
    {
        return UndergroundBiomes.metamorphicBrickSlabHalf.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemNameIS(ItemStack itemstack) {
    	String name = "";
    	switch(itemstack.getItemDamage()){
			case(0): name = "gneissBrickSlab";
				break;
			case(1): name = "eclogiteBrickSlab";
				break;
			case(2): name = "marbleBrickSlab";
				break;
			case(3): name = "quartziteBrickSlab";
				break;
			case(4): name = "blueschistBrickSlab";
				break;
			case(5): name = "greenschistBrickSlab";
				break;
			case(6): name = "soapstoneBrickSlab";
				break;
			case(7): name = "migmatiteBrickSlab";
				break;
			default: name="default";
    		
	    }
    	return getItemName() + "." + name;
	}

}

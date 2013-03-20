package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemMetamorphicStoneBlock extends ItemBlock{
	
    public ItemMetamorphicStoneBlock(int par1, Block block)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int par1)
    {
        return UndergroundBiomes.metamorphicStone.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemNameIS(ItemStack itemstack) {
    	String name = "";
    	switch(itemstack.getItemDamage()){
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
    	return getItemName() + "." + name;
	}

}

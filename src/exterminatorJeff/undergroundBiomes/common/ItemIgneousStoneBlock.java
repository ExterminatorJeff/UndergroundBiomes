package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIgneousStoneBlock extends ItemBlock{

    public ItemIgneousStoneBlock(int par1, Block block)
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
        return UndergroundBiomes.igneousStone.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemNameIS(ItemStack itemstack) {
    	String name = "";
    	switch(itemstack.getItemDamage()){
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
    	return getItemName() + "." + name;
	}
    
    
    

	
}

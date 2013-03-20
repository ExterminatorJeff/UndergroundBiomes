package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMetamorphicCobblestoneBlock extends ItemBlock{
	
    public ItemMetamorphicCobblestoneBlock(int par1, Block block)
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
        return UndergroundBiomes.metamorphicCobblestone.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemNameIS(ItemStack itemstack) {
    	String name = "";
    	switch(itemstack.getItemDamage()){
    		case(0): name = "gneissCobble";
    			break;
    		case(1): name = "eclogiteCobble";
    			break;
    		case(2): name = "marbleCobble";
    			break;
    		case(3): name = "quartziteCobble";
    			break;
    		case(4): name = "blueschistCobble";
    			break;
    		case(5): name = "greenschistCobble";
    			break;
    		case(6): name = "soapstoneCobble";
    			break;
    		case(7): name = "migmatiteCobble";
    			break;
    		default: name="default";
    		
	    }
    	return getItemName() + "." + name;
	}

}


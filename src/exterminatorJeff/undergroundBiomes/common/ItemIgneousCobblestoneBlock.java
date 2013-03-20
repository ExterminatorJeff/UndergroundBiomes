package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIgneousCobblestoneBlock extends ItemBlock{
	
	public ItemIgneousCobblestoneBlock(int par1, Block block)
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
        return UndergroundBiomes.igneousCobblestone.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemNameIS(ItemStack itemstack) {
    	String name = "";
    	switch(itemstack.getItemDamage()){
    		case(0): name = "redGraniteCobble";
    			break;
    		case(1): name = "blackGraniteCobble";
    			break;
    		case(2): name = "rhyoliteCobble";
    			break;
    		case(3): name = "andesiteCobble";
    			break;
    		case(4): name = "gabbroCobble";
    			break;
    		case(5): name = "basaltCobble";
    			break;
    		case(6): name = "komatiiteCobble";
    			break;
    		case(7): name = "epidoteCobble";
    			break;
    		default: name="default";
    		
	    }
    	return getItemName() + "." + name;
	}
}

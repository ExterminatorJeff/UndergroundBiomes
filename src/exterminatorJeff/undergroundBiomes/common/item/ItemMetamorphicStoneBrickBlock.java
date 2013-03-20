package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemMetamorphicStoneBrickBlock extends ItemBlock{
	
    public ItemMetamorphicStoneBrickBlock(int par1, Block block)
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
        return UndergroundBiomes.metamorphicStoneBrick.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemNameIS(ItemStack itemstack) {
    	String name = "";
    	switch(itemstack.getItemDamage()){
    		case(0): name = "gneissBrick";
    			break;
    		case(1): name = "eclogiteBrick";
    			break;
    		case(2): name = "marbleBrick";
    			break;
    		case(3): name = "quartziteBrick";
    			break;
    		case(4): name = "blueschistBrick";
    			break;
    		case(5): name = "greenschistBrick";
    			break;
    		case(6): name = "soapstoneBrick";
    			break;
    		case(7): name = "migmatiteBrick";
    			break;
    		default: name="default";
    		
	    }
    	return getItemName() + "." + name;
	}

}

package exterminatorJeff.undergroundBiomes.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemIgneousCobblestoneBlock extends ItemBlockBase{
	
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
    public Icon getIconFromDamage(int par1)
    {
        return UndergroundBiomes.igneousCobblestone.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemName(int index){
    	String name = "";
    	
    	switch(index){
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
    	return super.getUnlocalizedName() + "." + name;
    }
    
	/*public String getItemNameIS(ItemStack itemstack) {
		String s = getItemName(itemstack.getItemDamage());
    	return getItemName(itemstack.getItemDamage());
	}*/
}

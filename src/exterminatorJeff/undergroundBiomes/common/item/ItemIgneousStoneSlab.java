package exterminatorJeff.undergroundBiomes.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemIgneousStoneSlab extends ItemBlockBase{

	public ItemIgneousStoneSlab(int par1, Block block) {
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
        return UndergroundBiomes.igneousBrickSlabHalf.getBlockTextureFromSideAndMetadata(0, par1);
    }

    public String getItemName(int index){
    	String name = "";
    	switch(index){
			case(0): name = "redGraniteBrickSlab";
				break;
			case(1): name = "blackGraniteBrickSlab";
				break;
			case(2): name = "rhyoliteBrickSlab";
				break;
			case(3): name = "andesiteBrickSlab";
				break;
			case(4): name = "gabbroBrickSlab";
				break;
			case(5): name = "basaltBrickSlab";
				break;
			case(6): name = "komatiiteBrickSlab";
				break;
			case(7): name = "epidoteBrickSlab";
				break;
			default: name="default";
			
	    }
    	return getUnlocalizedName() + "." + name;
    }

}

package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemIgneousStoneBrickBlock extends ItemBlockBase{
	
	public ItemIgneousStoneBrickBlock(int par1, Block block)
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
        return UndergroundBiomes.igneousStoneBrick.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemName(int index){
    	String name = "";
    	switch(index){
			case(0): name = "redGraniteBrick";
				break;
			case(1): name = "blackGraniteBrick";
				break;
			case(2): name = "rhyoliteBrick";
				break;
			case(3): name = "andesiteBrick";
				break;
			case(4): name = "gabbroBrick";
				break;
			case(5): name = "basaltBrick";
				break;
			case(6): name = "komatiiteBrick";
				break;
			case(7): name = "epidoteBrick";
				break;
			default: name="default";
			
	    }
    	return getUnlocalizedName() + "." + name;
    }
}

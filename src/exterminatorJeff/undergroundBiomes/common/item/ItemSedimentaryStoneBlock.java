package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemSedimentaryStoneBlock extends ItemBlockBase{
	
	
	
    public ItemSedimentaryStoneBlock(int par1, Block block)
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
        return UndergroundBiomes.sedimentaryStone.getBlockTextureFromSideAndMetadata(0, par1);
    }
    
    public String getItemName(int index) {
    	String name = "";
    	switch(index){
    		case(0): name = "limestone";
    			break;
    		case(1): name = "chalk";
    			break;
    		case(2): name = "shale";
    			break;
    		case(3): name = "siltstone";
    			break;
    		case(4): name = "ligniteBlock";
    			break;
    		case(5): name = "flint";
    			break;
    		case(6): name = "greywacke";
    			break;
    		case(7): name = "chert";
    			break;
    		default: name="default";
    		
	    }
    	return getUnlocalizedName() + "." + name;
	}

}

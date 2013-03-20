package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabModBlocks extends CreativeTabs
{
	public int iconID;
	String label;
    public CreativeTabModBlocks(int par1, String par2Str){
    	super(par1, par2Str);
    	label = par2Str;
    }
    
    public void setIcon(int iconID){
    	this.iconID = iconID;
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex(){
    	
    	return iconID;                       
    }

    public String getTranslatedTabLabel()
    {
            return label;
    }
}

package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabModBlocks extends CreativeTabs
{
    public int iconID;

    public CreativeTabModBlocks(String s)
    {
        super(s);
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return iconID;                       
    }
}

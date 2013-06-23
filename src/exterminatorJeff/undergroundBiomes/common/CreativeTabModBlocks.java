package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabModBlocks extends CreativeTabs
{
    public int iconID;
    String label;
    public CreativeTabModBlocks(String s, int iconID)
    {
        super(s);
        this.iconID = iconID;
        label = s;
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return iconID;                       
    }

    public String getTabLabel()
    {
        return label;
    }
}

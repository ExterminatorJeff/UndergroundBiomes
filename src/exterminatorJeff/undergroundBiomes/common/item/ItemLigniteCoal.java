package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemLigniteCoal extends Item{

    public ItemLigniteCoal(int par1) {
        super(par1);
        this.setCreativeTab(UndergroundBiomes.tabModItems);
        // TODO Auto-generated constructor stub
    }
    
    public String getTextureFile()
    {
        return UndergroundBiomes.textures;
    }

    public int getIconFromDamage(int damage)
    {
        return 128;
    }
}

package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemLigniteCoal extends Item
{
    Icon texture;
    
    public ItemLigniteCoal(int par1)
    {
        super(par1);
        setUnlocalizedName("ligniteCoal");
        setCreativeTab(UndergroundBiomes.tabModItems);
    }
    
    public String getTextureFile()
    {
        return UndergroundBiomes.textures;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        texture = iconRegister.registerIcon("undergroundbiomes:lignite");
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
        return texture;
    }
}

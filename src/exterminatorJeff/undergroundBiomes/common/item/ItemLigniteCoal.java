package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemLigniteCoal extends Item{

    Icon texture;
    
    public ItemLigniteCoal(int par1) {
        super(par1);
        this.setCreativeTab(UndergroundBiomes.tabModItems);
        // TODO Auto-generated constructor stub
    }
    
    @SideOnly(Side.CLIENT)
    public String getTextureFile(){
        return UndergroundBiomes.itemTextures;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister){
        texture = iconRegister.registerIcon(UndergroundBiomes.texturePath + "lignite");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int damage){
        return texture;
    }

}

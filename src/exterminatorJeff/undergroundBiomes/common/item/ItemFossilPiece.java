package exterminatorJeff.undergroundBiomes.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemFossilPiece extends Item
{
    private Icon[] textures = {null, null, null, null};
    private String[] names = {"shell", "bone1", "bone2", "bone3"};

    public ItemFossilPiece(int id)
    {
        super(id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(UndergroundBiomes.tabModItems);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        for(int i = 0; i < 4; i++)
        {
            textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + "fossilPiece_" + i);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int damage)
    {
        return textures[damage & 3];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 4; i++)
        {
            list.add(new ItemStack(id, 1, i));
        }
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + names[stack.getItemDamage() & 3];
    }
}

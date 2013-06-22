package exterminatorJeff.undergroundBiomes.common.item;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemFossilPiece extends Item
{
    private String[] names = {"ammonite", "shell", "rib", "bone", "skull", "bone", "shell", "boneshard"};
    public static final int TYPES = 8;

    public ItemFossilPiece(int id)
    {
        super(id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(UndergroundBiomes.tabModItems);
    }
    
    public int getIconFromDamage(int damage)
    {
        if (damage > TYPES) damage = 0;
        return damage + 129;
    }

    public String getTextureFile()
    {
        return UndergroundBiomes.textures;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < TYPES; i++)
        {
            list.add(new ItemStack(id, 1, i));
        }
    }

    public String getItemNameIS(ItemStack stack)
    {
        int damage = stack.getItemDamage();
        if (damage > TYPES) damage = 0;
        return super.getItemName() + "." + names[damage];
    }
}

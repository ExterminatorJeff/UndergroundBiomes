package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemBlockBase extends ItemBlock {

	public ItemBlockBase(int par1) {
		super(par1);
		
	}
	
	public String getItemName(int index) {
		return "";
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
    	return getItemName(itemstack.getItemDamage());
	}

}

package exterminatorJeff.undergroundBiomes.common.item;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockBase extends ItemBlock {

	private Icon[] textures = {null, null, null, null, null, null, null, null};
	
	public ItemBlockBase(int par1) {
		super(par1);
		
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister){
    	for(int i = 0; i < 8; i++){
    		textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + getItemName(i));
    	}
    }
	
	public String getItemName(int index) {
		return "";
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
    	return getItemName(itemstack.getItemDamage());
	}

}

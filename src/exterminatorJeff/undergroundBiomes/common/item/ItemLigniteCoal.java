package exterminatorJeff.undergroundBiomes.common.item;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.item.Item;

public class ItemLigniteCoal extends Item{

	public ItemLigniteCoal(int par1) {
		super(par1);
		this.setCreativeTab(UndergroundBiomes.tabModItems);
		// TODO Auto-generated constructor stub
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.itemTextures;
	}

}

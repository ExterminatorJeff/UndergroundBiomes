package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.item.Item;

public class ItemLigniteCoal extends Item{

	protected ItemLigniteCoal(int par1) {
		super(par1);
		this.setCreativeTab(UndergroundBiomes.tabModItems);
		// TODO Auto-generated constructor stub
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.itemTextures;
	}

}

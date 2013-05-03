package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemLigniteCoal extends Item{

	Icon texture;
	
	public ItemLigniteCoal(int par1) {
		super(par1);
		this.setCreativeTab(UndergroundBiomes.tabModItems);
		// TODO Auto-generated constructor stub
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.itemTextures;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		texture = iconRegister.registerIcon(UndergroundBiomes.texturePath + "lignite");
	}
	
	@Override
	public Icon getIconFromDamage(int damage){
		return texture;
	}

}

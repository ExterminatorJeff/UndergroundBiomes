package exterminatorJeff.undergroundBiomes.common.block;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockAnthracite extends Block{

	public BlockAnthracite(int ID) {
		super(ID, net.minecraft.block.material.Material.rock);
		this.setCreativeTab(UndergroundBiomes.tabModBlocks);
	}
	
	private Icon texture;
	
	@Override
	public void registerIcons(IconRegister iconRegister){
    	texture = iconRegister.registerIcon(UndergroundBiomes.texturePath + "anthracite");
    }

}

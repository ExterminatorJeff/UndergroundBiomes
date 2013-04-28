package exterminatorJeff.undergroundBiomes.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

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
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int metadata){
		return texture;
	}

}

package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.block.Block;

public class BlockAnthracite extends Block{

	public BlockAnthracite(int ID, int texture) {
		super(ID, texture, net.minecraft.block.material.Material.rock);
		this.setCreativeTab(UndergroundBiomes.tabModBlocks);
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.blockTextures;
	}

}

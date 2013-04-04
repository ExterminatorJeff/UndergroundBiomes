package exterminatorJeff.undergroundBiomes.common.block;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;

public class BlockAnthracite extends Block{

	public BlockAnthracite(int ID, int texture) {
		super(ID, net.minecraft.block.material.Material.rock);
		this.setCreativeTab(UndergroundBiomes.tabModBlocks);
	}
	
	public String getTextureFile(){
		return UndergroundBiomes.blockTextures;
	}

}

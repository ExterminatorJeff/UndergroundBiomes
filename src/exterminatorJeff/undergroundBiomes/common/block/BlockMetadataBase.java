package exterminatorJeff.undergroundBiomes.common.block;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockMetadataBase extends Block{

	protected Icon[] textures = {null, null, null, null, null, null, null, null};
	
	public BlockMetadataBase(int par1, Material par2Material) {
		super(par1, par2Material);
		
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
    	for(int i = 0; i < 8; i++){
    		textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + getBlockName(i));
    	}
    }
	
	public String getBlockName(int index){
		return "";
	}

}

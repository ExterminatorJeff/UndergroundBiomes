package exterminatorJeff.undergroundBiomes.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockAnthracite extends Block{

	public BlockAnthracite(int ID) {
		super(ID, net.minecraft.block.material.Material.rock);
		this.setCreativeTab(UndergroundBiomes.tabModBlocks);
		setHardness(1.5f);
		setResistance(30.0f);
	}
	
	private Icon texture;
	
	@Override
	public void registerIcons(IconRegister iconRegister){
    	texture = iconRegister.registerIcon(UndergroundBiomes.texturePath + "anthracite");
    }
	
	@Override
	public Icon getIcon(int side, int metadata){
		return texture;
	}

}

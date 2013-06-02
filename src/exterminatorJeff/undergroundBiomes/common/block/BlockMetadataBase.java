package exterminatorJeff.undergroundBiomes.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetadataBase extends Block{

	protected Icon[] textures = {null, null, null, null, null, null, null, null};
	
	public BlockMetadataBase(int par1, Material par2Material) {
		super(par1, par2Material);
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister){
    	for(int i = 0; i < 8; i++){
    		textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + getBlockName(i));
    	}
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata){
        return textures[metadata & 7];
    }
	
	public String getBlockName(int index){
		return "";
	}
	
	public String getBlockName(int index, boolean toCaps){
		String name = getBlockName(index);
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

}

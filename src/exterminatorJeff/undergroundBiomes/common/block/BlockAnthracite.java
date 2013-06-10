package exterminatorJeff.undergroundBiomes.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockAnthracite extends Block
{
    public BlockAnthracite(int id)
    {
        super(id, Material.rock);
        this.setHardness(1.5f);
        this.setResistance(30.0f);
        this.setCreativeTab(UndergroundBiomes.tabModBlocks);
    }
    
    private Icon texture;
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        texture = iconRegister.registerIcon(UndergroundBiomes.texturePath + "anthracite");
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return texture;
    }
}

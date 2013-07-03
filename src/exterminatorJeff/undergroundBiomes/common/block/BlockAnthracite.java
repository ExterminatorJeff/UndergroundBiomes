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
        setHardness(1.5f);
        setResistance(30.0f);
        setUnlocalizedName("anthraciteCoal");
        func_111022_d("undergroundbiomes:anthraciteCoal");
        setCreativeTab(UndergroundBiomes.tabModBlocks);
    }
    
    private Icon texture;
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        texture = iconRegister.registerIcon("undergroundbiomes:anthracite");
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return texture;
    }
}

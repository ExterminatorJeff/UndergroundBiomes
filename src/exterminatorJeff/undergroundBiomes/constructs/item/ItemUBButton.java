package exterminatorJeff.undergroundBiomes.constructs.item;

/**
 *
 * @author zeno410
 */

import net.minecraft.block.Block;

public class ItemUBButton extends ItemUndergroundBiomesConstruct{

    public ItemUBButton(Block block){
        super(block);
    }

    public ItemUBButton(int ID, Block block){
        super(ID,block);
    }

    @Override
    public String groupName() {return "button";}

}

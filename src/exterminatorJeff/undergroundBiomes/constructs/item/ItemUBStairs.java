/*
 * Author Zeno410
 */

package exterminatorJeff.undergroundBiomes.constructs.item;


import net.minecraft.block.Block;


public class ItemUBStairs extends ItemUndergroundBiomesConstruct {
    //private Zeno410Logger logger = new Zeno410Logger("ItemMetadataStairs");

    public ItemUBStairs(Block block){
        super(block);
    }

    public ItemUBStairs(int ID, Block block){
        super(ID,block);
    }

    @Override
    public String groupName() {return "stairs";}

}

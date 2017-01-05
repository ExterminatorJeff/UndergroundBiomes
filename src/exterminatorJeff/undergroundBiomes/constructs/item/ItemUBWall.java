package exterminatorJeff.undergroundBiomes.constructs.item;
import net.minecraft.block.Block;


/**
 *
 * @author Zeno410
 */
public class ItemUBWall extends ItemUndergroundBiomesConstruct {
    //private Zeno410Logger logger = new Zeno410Logger("ItemMetadataWall");

    public ItemUBWall(Block block){
        super(block);
    }

    public ItemUBWall(int ID, Block block){
        super(ID,block);
    }

    @Override
    public String groupName() {return "wall";}

}

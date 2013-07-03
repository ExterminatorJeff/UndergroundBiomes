package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelManager implements IFuelHandler
{
    public int getBurnTime(ItemStack fuel)
    {
        if(fuel.itemID == UndergroundBiomes.ligniteCoal.itemID)
        {
            return 200;
        }
        else if(fuel.itemID == UndergroundBiomes.anthracite.blockID)
        {
            return 16000;
        } else {
            return 0;
        }
    }
}

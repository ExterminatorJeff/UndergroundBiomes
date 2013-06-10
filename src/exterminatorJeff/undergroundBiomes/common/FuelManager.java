package exterminatorJeff.undergroundBiomes.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelManager implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel){
		if(fuel.itemID == UndergroundBiomes.ligniteCoal.itemID){
			return 200;
		}else if(fuel.itemID == UndergroundBiomes.anthracite.blockID){
			return 6400;
		}else{
			return 0;
		}
		
	}

}

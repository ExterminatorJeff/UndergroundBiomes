/*
 * Author Zeno410
 */

package exterminatorJeff.undergroundBiomes.constructs;

import exterminatorJeff.undergroundBiomes.constructs.block.UBButtonGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallGroup;

import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 *
 * @author curtisadams
 */
//@Mod(modid = "UndergroundBiomesConstructs", name = "Underground Biomes Constructs", version = "0.0.4")
//@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class UndergroundBiomesConstructs {

    public static int subdivisionBlockCount = 4; // 8 types, 2 per class

    private UBStairsGroup stoneStair;
    private int stoneStairID;

    private UBWallGroup stoneWall;
    private int stoneWallID;

    private UBButtonGroup stoneButtons;
    private int stoneButtonID;

    public Configuration config;
     

    private UndergroundBiomesBlockList ubBlockList;

    //@PreInit
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
         
        stoneStair = new UBStairsGroup();
        stoneStairID = config.getBlock("Universal Biomes Stairs ID:", 2012).getInt();

        preInitWalls();
        preInitButtons();
    }

    public void preInitWalls() {
        stoneWall = new UBWallGroup();
        stoneWallID = config.getBlock("Universal Biomes Wall ID:", 2013).getInt();
    }

    public void preInitButtons() {
        stoneButtons = new UBButtonGroup();
        stoneButtonID = config.getBlock("Universal Biomes Button ID:", 2014).getInt();
    }
    //@Init
    public void load(FMLInitializationEvent event) {

        ubBlockList = new UndergroundBiomesBlockList();
        GameRegistry.registerTileEntity(UndergroundBiomesTileEntity.class, "UndergroundBiomesTileEntity");

        loadStairs();
        loadWalls();
        loadButtons();
    }

    private void loadStairs(){
        stoneStair.baseBlock = ubBlockList.sedimentaryStone;
        stoneStair.baseID = ubBlockList.sedimentaryStone.blockID;
        stoneStair.define(stoneStairID);

    }
    private void loadWalls() {
        stoneWall.baseBlock = ubBlockList.sedimentaryStone;
        stoneWall.baseID = ubBlockList.sedimentaryStone.blockID;
        stoneWall.define(stoneWallID);
    }

    private void loadButtons() {
        stoneButtons.baseBlock = ubBlockList.sedimentaryStone;
        stoneButtons.baseID = ubBlockList.sedimentaryStone.blockID;
        stoneButtons.define(stoneButtonID);
    }
    public void postInit(FMLPostInitializationEvent event) throws Exception {
        
    }

}

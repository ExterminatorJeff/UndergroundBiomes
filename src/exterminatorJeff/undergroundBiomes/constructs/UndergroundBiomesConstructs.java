/*
 * Author Zeno410
 */

package exterminatorJeff.undergroundBiomes.constructs;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

import exterminatorJeff.undergroundBiomes.constructs.block.UndergroundBiomesButton;
import exterminatorJeff.undergroundBiomes.constructs.block.UBButtonGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallGroup;

import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraftforge.common.Configuration;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

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

    //@PreIni
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        if (UndergroundBiomes.buttonsOn()) preInitButtons();
        if (UndergroundBiomes.stairsOn()) preInitStairs();
        if (UndergroundBiomes.wallsOn()) preInitWalls();
    }

    public void preInitStairs() {
        stoneStair = new UBStairsGroup();
        stoneStairID = config.getBlock("Universal Biomes Stairs ID:", 2012).getInt();
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

        if (UndergroundBiomes.buttonsOn()) loadButtons();
        if (UndergroundBiomes.stairsOn()) loadStairs();
        if (UndergroundBiomes.wallsOn()) loadWalls();
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

    public static boolean overridesRecipe(IRecipe recipe) {
        // this determines if oredictifying a recipe will override a constructs recipe
        // right now it's overly simplistic and assumes anything producing a construct being made
        // is an override
        if (recipe == null) return false;
        Object output = null;
        try {
            output = recipe.getRecipeOutput().getItem();
        } catch (Exception e) {
            return false;
        }
        if (!(output instanceof ItemBlock)) {return false;}
        Block blockMade = Block.blocksList[((ItemBlock)output).getBlockID()];
        if (UndergroundBiomes.buttonsOn()) {
            if (blockMade instanceof BlockButton) return true;
        }
        if (UndergroundBiomes.stairsOn()) {
            if (blockMade instanceof BlockStairs) {return true;}
        }
        if (UndergroundBiomes.wallsOn()) {
            if (blockMade instanceof BlockWall) return true;
        }
        return false;
    }
}

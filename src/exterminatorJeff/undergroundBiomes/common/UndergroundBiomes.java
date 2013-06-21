package exterminatorJeff.undergroundBiomes.common;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.*;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.Loader;

import exterminatorJeff.undergroundBiomes.common.block.*;
import exterminatorJeff.undergroundBiomes.common.item.*;
import exterminatorJeff.undergroundBiomes.common.command.*;

@Mod(modid = "UndergroundBiomes", name = "Underground Biomes", version = "0.4.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class UndergroundBiomes
{
    public static World world;
    public static boolean addOreDictRecipes = true;
    public static boolean vanillaStoneBiomes = false;
    
    public Configuration config;
    
    public static WorldGenManager worldGen;
    
    public static String blockTextures = "/exterminatorJeff/undergroundBiomes/textures/BlockTextures.png";
    public static String itemTextures = "/exterminatorJeff/undergroundBiomes/textures/Items.png";
    public static String texturePath = "UndergroundBiomes:";
    
    public static CreativeTabs tabModBlocks;
    public static CreativeTabs tabModItems;
    
    public static long worldSeed;
    private boolean gotWorldSeed;
    
    public static Block igneousStone;
    public static Block igneousCobblestone;
    public static Block igneousStoneBrick;
    public static Block metamorphicStone;
    public static Block metamorphicCobblestone;
    public static Block metamorphicStoneBrick;
    public static Block sedimentaryStone;
    public static Item ligniteCoal;
    public static Item fossilPiece;
    public static Block anthracite;
    public static BlockHalfSlab igneousBrickSlabHalf;
    public static BlockHalfSlab igneousBrickSlabFull;
    public static BlockHalfSlab metamorphicBrickSlabHalf;
    public static BlockHalfSlab metamorphicBrickSlabFull;
    
    public static ArrayList<Integer> fortuneAffected;
    public static ArrayList<ItemStack> nuggets;

    private static String[] nuggetStrings = {
      "nuggetIron", "nuggetCopper", "nuggetTin", "nuggetSilver", "nuggetLead", "nuggetAluminium",
      "nuggetNaturalAluminium", "nuggetNickel", "nuggetPlatinum", "nuggetElectrum", "nuggetZinc",
    };

    private int igneousStoneID;
    private int igneousCobblestoneID;
    private int igneousStoneBrickID;
    private int metamorphicStoneID;
    private int metamorphicCobblestoneID;
    private int metamorphicStoneBrickID;
    private int sedimentaryStoneID;
    private int ligniteCoalID;
    private int fossilPieceID;
    private int anthraciteCoalID;
    private int igneousBrickSlabHalfId;
    private int igneousBrickSlabFullId;
    private int metamorphicBrickSlabHalfID;
    private int metamorphicBrickSlabFullID;
    private List<Integer> includeDimensionIDs;
    private List<Integer> excludeDimensionIDs;
    private String includeDimensions;
    private String excludeDimensions;

    
    public static int biomeSize = 45;

    @SidedProxy(clientSide = "exterminatorJeff.undergroundBiomes.client.ClientProxy",
                serverSide = "exterminatorJeff.undergroundBiomes.common.CommonProxy")
    public static CommonProxy proxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        igneousStoneID = config.getBlock("Igneous Stone ID:", 2009).getInt();
        metamorphicStoneID = config.getBlock("Metamorphic Stone ID:", 2010).getInt();
        sedimentaryStoneID = config.getBlock("Sedimentary Stone ID:", 2011).getInt();
        
        igneousCobblestoneID = config.getBlock("Igneous Cobblestone ID:", 2000).getInt();
        metamorphicCobblestoneID = config.getBlock("Metamorphic Cobblestone ID:", 2001).getInt();
        
        igneousStoneBrickID = config.getBlock("Igneous Brick ID:", 2002).getInt();
        metamorphicStoneBrickID = config.getBlock("Metamorphic Brick ID:", 2003).getInt();
        
        anthraciteCoalID = config.getBlock("Anthracite Block ID:", 2004).getInt();
        
        igneousBrickSlabHalfId = config.getBlock("Igneous Stone Brick Slab ID (half):", 2005).getInt();
        igneousBrickSlabFullId = config.getBlock("Igneous Stone Brick Slab ID (full):", 2006).getInt();
        
        metamorphicBrickSlabHalfID = config.getBlock("Metamorphic Stone Brick Slab ID (half):", 2007).getInt();
        metamorphicBrickSlabFullID = config.getBlock("Metamorphic Stone Brick Slab ID (full):", 2008).getInt();
        
        // Item read from block category to be backwards-compatible
        ligniteCoalID = config.getItem(Configuration.CATEGORY_BLOCK, "Lignite Item ID:", 5500).getInt();
        fossilPieceID = config.getItem("fossilPiece", 5501).getInt();
        
        biomeSize = config.get(Configuration.CATEGORY_GENERAL, "biomeSize", 45, "Warning: exponential").getInt();
        addOreDictRecipes = config.get(Configuration.CATEGORY_GENERAL, "oreDictifyStone", true, "Modify all recipes to include Underground Biomes blocks").getBoolean(true);
        vanillaStoneBiomes = config.get(Configuration.CATEGORY_GENERAL, "vanillaStoneBiomes", false, "Will cause sharp biome transitions if changed while playing the same world").getBoolean(false);
        excludeDimensions = config.get(Configuration.CATEGORY_GENERAL, "excludeDimensionIDs", "-1,1", "Comma-separated list of dimension IDs, used only if include list is *").getString();
        includeDimensions = config.get(Configuration.CATEGORY_GENERAL, "includeDimensionIDs", "*", "Comma-separated list of dimension IDs, put * to use exclude list").getString();

        if (includeDimensions.equals("*"))
        {
            excludeDimensionIDs = new ArrayList<Integer>();
            for (String v : excludeDimensions.split(","))
            {
                excludeDimensionIDs.add(Integer.parseInt(v));
            }
        }
        else
        {
            includeDimensionIDs = new ArrayList<Integer>();
            for (String v : includeDimensions.split(","))
            {
                includeDimensionIDs.add(Integer.parseInt(v));
            }
        }
        
        config.save();

        fortuneAffected = new ArrayList<Integer>();
        fortuneAffected.add(ligniteCoalID);

        nuggets = new ArrayList<ItemStack>();
        nuggets.add(new ItemStack(Item.goldNugget, 1, 0));
    }
    
    @Init
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderThings();
        
        tabModBlocks = new CreativeTabModBlocks(CreativeTabs.creativeTabArray.length, "Underground Biomes Blocks");
        tabModItems = new CreativeTabModBlocks(CreativeTabs.creativeTabArray.length, "Underground Biomes Items");
        
        igneousStone = new BlockIgneousStone(igneousStoneID).setUnlocalizedName("igneousStone");
        new ItemMetadataBlock(igneousStone);
        
        igneousCobblestone = new BlockIgneousCobblestone(igneousCobblestoneID).setUnlocalizedName("igneousCobblestone");
        new ItemMetadataBlock(igneousCobblestone);
        
        igneousStoneBrick = new BlockIgneousStoneBrick(igneousStoneBrickID).setUnlocalizedName("igneousStoneBrick");
        new ItemMetadataBlock(igneousStoneBrick);
        
        metamorphicStone = new BlockMetamorphicStone(metamorphicStoneID).setUnlocalizedName("metamorphicStone");
        new ItemMetadataBlock(metamorphicStone);
        
        metamorphicCobblestone = new BlockMetamorphicCobblestone(metamorphicCobblestoneID).setUnlocalizedName("metamorphicCobblestone");
        new ItemMetadataBlock(metamorphicCobblestone);
        
        metamorphicStoneBrick = new BlockMetamorphicStoneBrick(metamorphicStoneBrickID).setUnlocalizedName("metamorphicStoneBrick");
        new ItemMetadataBlock(metamorphicStoneBrick);
        
        sedimentaryStone = new BlockSedimentaryStone(sedimentaryStoneID).setUnlocalizedName("sedimentaryStone");
        new ItemMetadataBlock(sedimentaryStone);
        
        anthracite = new BlockAnthracite(anthraciteCoalID).setUnlocalizedName("anthraciteCoal");
        GameRegistry.registerBlock(anthracite, "anthraciteBlock");
        
        igneousBrickSlabHalf = (BlockHalfSlab) new BlockIgneousStoneSlab(igneousBrickSlabHalfId, false).setUnlocalizedName("igneousBrickSlab");
        igneousBrickSlabFull = (BlockHalfSlab) new BlockIgneousStoneSlab(igneousBrickSlabFullId, true).setUnlocalizedName("igneousBrickSlabFull");
        
        ItemIgneousStoneSlab.setSlabs(igneousBrickSlabHalf, igneousBrickSlabFull);
        
        Item.itemsList[igneousBrickSlabFullId] = new ItemIgneousStoneSlab(igneousBrickSlabFullId - 256, igneousBrickSlabFull).setUnlocalizedName("igneousBrickSlabFull");
        Item.itemsList[igneousBrickSlabHalfId] = new ItemIgneousStoneSlab(igneousBrickSlabHalfId - 256, igneousBrickSlabHalf).setUnlocalizedName("igneousBrickSlab");
        
        metamorphicBrickSlabHalf = (BlockHalfSlab) new BlockMetamorphicStoneSlab(metamorphicBrickSlabHalfID, false).setUnlocalizedName("metamorphicBrickSlab");
        metamorphicBrickSlabFull = (BlockHalfSlab) new BlockMetamorphicStoneSlab(metamorphicBrickSlabFullID, true).setUnlocalizedName("metamorphicBrickSlabFull");
        
        ItemMetamorphicStoneSlab.setSlabs(metamorphicBrickSlabHalf, metamorphicBrickSlabFull);
        
        Item.itemsList[metamorphicBrickSlabHalfID] = new ItemMetamorphicStoneSlab(metamorphicBrickSlabHalfID - 256, metamorphicBrickSlabHalf).setUnlocalizedName("metamorphicBrickSlab");
        Item.itemsList[metamorphicBrickSlabFullID] = new ItemMetamorphicStoneSlab(metamorphicBrickSlabFullID - 256, metamorphicBrickSlabFull).setUnlocalizedName("metamorphicBrickSlabFull");
        
        //items

        ligniteCoal = new ItemLigniteCoal(ligniteCoalID).setUnlocalizedName("ligniteCoal");
        fossilPiece = new ItemFossilPiece(fossilPieceID).setUnlocalizedName("fossilPiece");
        
        setUpBlockNames();
        addOreDicts();
        addRecipes();
        
        MinecraftForge.EVENT_BUS.register(this);    
        
        ((CreativeTabModBlocks) tabModBlocks).setIcon(igneousStoneBrick.blockID);
        ((CreativeTabModBlocks) tabModItems).setIcon(ligniteCoal.itemID);
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) throws Exception
    {
        if (addOreDictRecipes)
        {
            oreDictifyStone();
        }

        // Pull nuggets from ore dictionary
        ArrayList<ItemStack> stacks;
        for (String s : nuggetStrings)
        {
            stacks = OreDictionary.getOres(s);
            if (stacks.size() > 0)
            {
                nuggets.add(stacks.get(0));
            }
        }

        if (Loader.isModLoaded("Thaumcraft"))
        {
            try
            {
		//ThaumcraftApi.registerObjectTag(id, meta, (new ObjectTags()).add(EnumTag.VALUABLE, 58).add(EnumTag.LIGHT, 15));
            }
            catch (Exception e)
            {
                System.out.println("[UndergroundBiomes] Error while integrating with Thaumcraft");
                e.printStackTrace(System.err);
            }
        }
    }
    
    @ServerStarting
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandOreDictifyStone());
    }
    
    public void setUpBlockNames()
    {
        LanguageRegistry lr = LanguageRegistry.instance();

        lr.addStringLocalization("tile.igneousStone.redGranite.name", "Red Granite");
        lr.addStringLocalization("tile.igneousStone.blackGranite.name", "Black Granite");
        lr.addStringLocalization("tile.igneousStone.rhyolite.name", "Rhyolite");
        lr.addStringLocalization("tile.igneousStone.andesite.name", "Andesite");
        lr.addStringLocalization("tile.igneousStone.gabbro.name", "Gabbro");
        lr.addStringLocalization("tile.igneousStone.basalt.name", "Basalt");
        lr.addStringLocalization("tile.igneousStone.komatiite.name", "Komatiite");
        lr.addStringLocalization("tile.igneousStone.dacite.name", "Dacite");
        
        lr.addStringLocalization("tile.igneousCobblestone.redGraniteCobble.name", "Red Granite Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.blackGraniteCobble.name", "Black Granite Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.rhyoliteCobble.name", "Rhyolite Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.andesiteCobble.name", "Andesite Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.gabbroCobble.name", "Gabbro Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.basaltCobble.name", "Basalt Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.komatiiteCobble.name", "Komatiite Cobblestone");
        lr.addStringLocalization("tile.igneousCobblestone.daciteCobble.name", "Dacite Cobblestone");
        
        lr.addStringLocalization("tile.igneousStoneBrick.redGraniteBrick.name", "Red Granite Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.blackGraniteBrick.name", "Black Granite Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.rhyoliteBrick.name", "Rhyolite Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.andesiteBrick.name", "Andesite Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.gabbroBrick.name", "Gabbro Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.basaltBrick.name", "Basalt Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.komatiiteBrick.name", "Komatiite Bricks");
        lr.addStringLocalization("tile.igneousStoneBrick.daciteBrick.name", "Dacite Bricks");
        
        lr.addStringLocalization("tile.metamorphicStone.gneiss.name", "Gneiss");
        lr.addStringLocalization("tile.metamorphicStone.eclogite.name", "Eclogite");
        lr.addStringLocalization("tile.metamorphicStone.marble.name", "Marble");
        lr.addStringLocalization("tile.metamorphicStone.quartzite.name", "Quartzite");
        lr.addStringLocalization("tile.metamorphicStone.blueschist.name", "Blue Schist");
        lr.addStringLocalization("tile.metamorphicStone.greenschist.name", "Green Schist");
        lr.addStringLocalization("tile.metamorphicStone.soapstone.name", "Soapstone");
        lr.addStringLocalization("tile.metamorphicStone.migmatite.name", "Migmatite");
        
        lr.addStringLocalization("tile.metamorphicCobblestone.gneissCobble.name", "Gneiss Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.eclogiteCobble.name", "Eclogite Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.marbleCobble.name", "Marble Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.quartziteCobble.name", "Quartzite Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.blueschistCobble.name", "Blue Schist Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.greenschistCobble.name", "Green Schist Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.soapstoneCobble.name", "Soapstone Cobblestone");
        lr.addStringLocalization("tile.metamorphicCobblestone.migmatiteCobble.name", "Migmatite Cobblestone");
        
        lr.addStringLocalization("tile.metamorphicStoneBrick.gneissBrick.name", "Gneiss Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.eclogiteBrick.name", "Eclogite Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.marbleBrick.name", "Marble Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.quartziteBrick.name", "Quartzite Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.blueschistBrick.name", "Blue Schist Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.greenschistBrick.name", "Green Schist Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.soapstoneBrick.name", "Soapstone Bricks");
        lr.addStringLocalization("tile.metamorphicStoneBrick.migmatiteBrick.name", "Migmatite Bricks");
        
        lr.addStringLocalization("tile.sedimentaryStone.limestone.name", "Limestone");
        lr.addStringLocalization("tile.sedimentaryStone.chalk.name", "Chalk");
        lr.addStringLocalization("tile.sedimentaryStone.shale.name", "Shale");
        lr.addStringLocalization("tile.sedimentaryStone.siltstone.name", "Siltstone");
        lr.addStringLocalization("tile.sedimentaryStone.ligniteBlock.name", "Lignite Block");
        lr.addStringLocalization("tile.sedimentaryStone.flint.name", "Flint");
        lr.addStringLocalization("tile.sedimentaryStone.greywacke.name", "Greywacke");
        lr.addStringLocalization("tile.sedimentaryStone.chert.name", "Chert");
        
        lr.addStringLocalization("tile.igneousBrickSlab.redGraniteBrickSlab.name", "Red Granite Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.blackGraniteBrickSlab.name", "Black Granite Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.rhyoliteBrickSlab.name", "Rhyolite Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.andesiteBrickSlab.name", "Andesite Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.gabbroBrickSlab.name", "Gabbro Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.basaltBrickSlab.name", "Basalt Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.komatiiteBrickSlab.name", "Komatiite Brick Slab");
        lr.addStringLocalization("tile.igneousBrickSlab.daciteBrickSlab.name", "Dacite Brick Slab");
        
        lr.addStringLocalization("tile.metamorphicBrickSlab.gneissBrickSlab.name", "Gneiss Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.eclogiteBrickSlab.name", "Eclogite Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.marbleBrickSlab.name", "Marble Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.quartziteBrickSlab.name", "Quartzite Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.blueschistBrickSlab.name", "Blue Schist Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.greenschistBrickSlab.name", "Green Schist Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.soapstoneBrickSlab.name", "Soapstone Brick Slab");
        lr.addStringLocalization("tile.metamorphicBrickSlab.migmatiteBrickSlab.name", "Migmatite Brick Slab");
        
        LanguageRegistry.addName(anthracite, "Anthracite Coal Block");
        
        LanguageRegistry.addName(ligniteCoal, "Lignite");

        lr.addStringLocalization("item.fossilPiece.ammonite.name", "Ammonite fossil");
        lr.addStringLocalization("item.fossilPiece.shell.name", "Shell fossil");
        lr.addStringLocalization("item.fossilPiece.bone.name", "Bone fossil");
        lr.addStringLocalization("item.fossilPiece.boneshard.name", "Bone shard fossil");
        lr.addStringLocalization("item.fossilPiece.rib.name", "Rib fossil");
        lr.addStringLocalization("item.fossilPiece.skull.name", "Skull fossil");
    }
    
    public void addRecipes()
    {
        if (!addOreDictRecipes)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.furnaceIdle, 1), "XXX", "X X", "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.lever, 1), "I", "X", 'X', "stoneCobble", 'I', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CIC", "CRC", 'W', Block.planks, 'C', "stoneCobble", 'I', Item.ingotIron, 'R', Item.redstone));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stairsCobblestone, 4), "X  ", "XX ", "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestoneWall, 1), "XXX", "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.axeStone, 1), "XX ", "XW ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.pickaxeStone, 1), "XXX", " W ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.hoeStone, 1), "XX ", " W ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.shovelStone, 1), " X ", " W ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.swordStone, 1), "X", "X", "W", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.brewingStand, 1), " B ", "XXX", 'X', "stoneCobble", 'B', Item.blazeRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.dispenser, 1), "XXX", "XBX", "XRX", 'X', "stoneCobble", 'B', Item.bow, 'R', Item.redstone));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.pressurePlateStone, 1), "XX", 'X', "stoneSmooth"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), "XXX", 'X', "stoneSmooth"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.redstoneRepeater, 1), "TRT", "XXX", 'X', "stoneSmooth", 'T', Block.torchRedstoneActive, 'R', Item.redstone));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stairsStoneBrick, 4), "X  ", "XX ", "XXX", 'X', "stoneBricks"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.stoneButton, 1), "stoneSmooth"));
        }
        
        GameRegistry.addRecipe(new ItemStack(anthracite, 1), "XX", "XX", 'X', Item.coal);
        GameRegistry.addRecipe(new ItemStack(Item.coal, 1), "XXX", "XXX", "XXX", 'X', ligniteCoal);
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 15), new ItemStack(fossilPiece, 1, WILDCARD_VALUE));
        
        //vanilla cobblestone
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestone, 4), "XX", "XX", 'X', "stoneCobble"));
        
        for (int i = 0; i < 8; i++)
        {
            GameRegistry.addRecipe(new ItemStack(igneousBrickSlabHalf, 6, i), "XXX", 'X', new ItemStack(igneousStoneBrick, 1, i));
            GameRegistry.addRecipe(new ItemStack(metamorphicBrickSlabHalf, 6, i), "XXX", 'X', new ItemStack(metamorphicStoneBrick, 1, i));

            FurnaceRecipes.smelting().addSmelting(metamorphicCobblestone.blockID, i, new ItemStack(metamorphicStone, 1, i), 0.1f);
            FurnaceRecipes.smelting().addSmelting(igneousCobblestone.blockID, i, new ItemStack(igneousStone, 1, i), 0.1f);

            GameRegistry.addRecipe(new ItemStack(metamorphicStoneBrick, 4, i), "xx", "xx", 'x', new ItemStack(metamorphicStone, 1, i));
            GameRegistry.addRecipe(new ItemStack(igneousStoneBrick, 4, i), "xx", "xx", 'x', new ItemStack(igneousStone, 1, i));
        }
        
        GameRegistry.registerFuelHandler(new FuelManager());
    }
    
    public void addOreDicts()
    {
        OreDictionary.registerOre("stoneSmooth", new ItemStack(igneousStone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneSmooth", new ItemStack(metamorphicStone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneCobble", new ItemStack(igneousCobblestone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneCobble", new ItemStack(metamorphicCobblestone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneBricks", new ItemStack(igneousStoneBrick, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneBricks", new ItemStack(metamorphicStoneBrick, 1, WILDCARD_VALUE));
    }
    
    public static int oreDictifyStone() throws Exception
    {
        int numReplaced = 0;
        Map<ItemStack, String> replacements = new HashMap<ItemStack, String>();
        replacements.put(new ItemStack(Block.stone, 1, WILDCARD_VALUE), "stoneSmooth");
        replacements.put(new ItemStack(Block.cobblestone, 1, WILDCARD_VALUE), "stoneCobble");
        replacements.put(new ItemStack(Block.stoneBrick, 1, WILDCARD_VALUE), "stoneBricks");
        ItemStack[] replaceStacks = replacements.keySet().toArray(new ItemStack[replacements.keySet().size()]);

        // Ignore recipes for the following items
        ItemStack[] exclusions = new ItemStack[]
        {
            new ItemStack(Block.stairsStoneBrick),
            new ItemStack(Block.stoneBrick),
            new ItemStack(Block.stoneSingleSlab, 1, 5),
        };
        List recipes = CraftingManager.getInstance().getRecipeList();
        Constructor shapedConstr = ShapedOreRecipe.class.getDeclaredConstructor(ShapedRecipes.class, Map.class);
        Constructor shapelessConstr = ShapelessOreRecipe.class.getDeclaredConstructor(ShapelessRecipes.class, Map.class);
        shapedConstr.setAccessible(true);
        shapelessConstr.setAccessible(true);

        // Add ore dictionary entries for replaced blocks
        for (ItemStack stack : replacements.keySet())
        {
            OreDictionary.registerOre(replacements.get(stack), stack);
        }

        // Search stone recipes for recipes to replace
        for (int i = 0; i < recipes.size(); i++)
        {
            Object obj = recipes.get(i);
            ItemStack output = ((IRecipe)obj).getRecipeOutput();
            if (output != null && containsMatch(false, exclusions, output))
            {
                continue;
            }
            if (obj instanceof ShapedRecipes)
            {
                ShapedRecipes recipe = (ShapedRecipes)obj;
                if (containsMatch(true, recipe.recipeItems, replaceStacks))
                {
                    recipes.set(i, (ShapedOreRecipe)shapedConstr.newInstance(recipe, replacements));
                    numReplaced++;
                    System.out.println("Changed shaped recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapelessRecipes)
            {
                ShapelessRecipes recipe = (ShapelessRecipes)obj;
                if (containsMatch(true, (ItemStack[])recipe.recipeItems.toArray(new ItemStack[recipe.recipeItems.size()]), replaceStacks))
                {
                    recipes.set(i, (ShapelessOreRecipe)shapelessConstr.newInstance(recipe, replacements));
                    numReplaced++;
                    System.out.println("Changed shapeless recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapedOreRecipe)
            {
                ShapedOreRecipe recipe = (ShapedOreRecipe)obj;
                if (containsMatchReplaceInplace(true, recipe.getInput(), replaceStacks, replacements))
                {
                    numReplaced++;
                    System.out.println("Changed shaped ore recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapelessOreRecipe)
            {
                ShapelessOreRecipe recipe = (ShapelessOreRecipe)obj;
                if (containsMatchReplaceInplace(true, recipe.getInput(), replaceStacks, replacements))
                {
                    numReplaced++;
                    System.out.println("Changed shapeless ore recipe for " + output.getItemName());
                }
            }
        }
        return numReplaced;
    }
    
    private static boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack... targets)
    {
        for (ItemStack input : inputs)
        {
            for (ItemStack target : targets)
            {
                if (OreDictionary.itemMatches(target, input, strict))
                {
                    return true;
                }
            }
        }
        return false;
    }

    // Doing what Forge tells not to do
    private static boolean containsMatchReplaceInplace(boolean strict, Object inputArrayOrList, ItemStack[] targets, Map<ItemStack, String> replacements)
    {
        boolean replaced = false;
        if (inputArrayOrList instanceof ArrayList)
        {
            ArrayList inputList = (ArrayList)inputArrayOrList;
            for (int i = 0; i < inputList.size(); i++)
            {
                Object input = inputList.get(i);
                if (input instanceof ItemStack)
                {
                    for (ItemStack target : targets)
                    {
                        if (OreDictionary.itemMatches(target, (ItemStack)input, strict))
                        {
                            inputList.set(i, OreDictionary.getOres(replacements.get(target)));
                            replaced = true;
                        }
                    }
                }
            }
        } else {
            // Expect array
            Object[] inputArray = (Object[])inputArrayOrList;
            for (int i = 0; i < inputArray.length; i++)
            {
                Object input = inputArray[i];
                if (input instanceof ItemStack)
                {
                    for (ItemStack target : targets)
                    {
                        if (OreDictionary.itemMatches(target, (ItemStack)input, strict))
                        {
                            inputArray[i] = OreDictionary.getOres(replacements.get(target));
                            replaced = true;
                        }
                    }
                }
            }
        }
        return replaced;
    }
    
    public static long getWorldSeed()
    {
        return worldSeed;
    }
    
    public static World getWorld()
    {
        return world;
    }
    
    public static WorldGenManager getWorldGenManager()
    {
        return worldGen;
    }
    
    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event)
    {
        if (gotWorldSeed)
        {
            return;
        }
        world = event.world;
        worldSeed = world.getSeed();
        gotWorldSeed = true;
        worldGen = new WorldGenManager(worldSeed, world.getWorldInfo().getTerrainType(), world);
    }
    
    @ForgeSubscribe
    public void onWorldUnload(WorldEvent.Unload event)
    {
        gotWorldSeed = false;
    }
    
    @ForgeSubscribe
    public void onBiomeDecorate(DecorateBiomeEvent.Post event)
    {
        int id = event.world.provider.dimensionId;
        if (includeDimensions.equals("*"))
        {
            if (excludeDimensionIDs.contains(id)) return;
        } else {
            if (!includeDimensionIDs.contains(id)) return;
        }
        // Sometimes can get called before onWorldLoad, wtf?
        if (worldGen == null)
        {
            System.out.println("UndergroundBiomes warning: onBiomeDecorate before onWorldLoad! Ignoring.");
            return;
        }
        worldGen.onBiomeDecorate(event);
    }

    @ForgeSubscribe
    public void registerOre(OreDictionary.OreRegisterEvent event)
    {
        if (Arrays.asList(nuggets).contains(event.Name))
        {
            nuggets.add(event.Ore);
        }
    }
}

package exterminatorJeff.undergroundBiomes.common;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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

import exterminatorJeff.undergroundBiomes.common.block.*;
import exterminatorJeff.undergroundBiomes.common.item.*;
import exterminatorJeff.undergroundBiomes.common.command.*;

@Mod(modid = "UndergroundBiomes", name = "Underground Biomes", version = "0.3.9a")
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
    public static Block anthracite;
    public static BlockHalfSlab igneousBrickSlabHalf;
    public static BlockHalfSlab igneousBrickSlabFull;
    public static BlockHalfSlab metamorphicBrickSlabHalf;
    public static BlockHalfSlab metamorphicBrickSlabFull;
    
    private int igneousStoneID;
    private int igneousCobblestoneID;
    private int igneousStoneBrickID;
    private int metamorphicStoneID;
    private int metamorphicCobblestoneID;
    private int metamorphicStoneBrickID;
    private int sedimentaryStoneID;
    private int ligniteCoalID;
    private int anthraciteCoalID;
    private int igneousBrickSlabHalfId;
    private int igneousBrickSlabFullId;
    private int metamorphicBrickSlabHalfID;
    private int metamorphicBrickSlabFullID;

    public static int biomeSize = 45;
    
    //no grass
    public static boolean testMode1 = false;
    //no stone
    public static boolean testMode2 = false;
    

    @SidedProxy(clientSide = "exterminatorJeff.undergroundBiomes.client.ClientProxy",
                serverSide = "exterminatorJeff.undergroundBiomes.common.CommonProxy")
    public static CommonProxy proxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        igneousStoneID = config.get(Configuration.CATEGORY_BLOCK, "Igneous Stone ID:", 250).getInt();
        metamorphicStoneID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Stone ID:", 251).getInt();
        sedimentaryStoneID = config.get(Configuration.CATEGORY_BLOCK, "Sedimentary Stone ID:", 252).getInt();
        
        igneousCobblestoneID = config.get(Configuration.CATEGORY_BLOCK, "Igneous Cobblestone ID:", 2000).getInt();
        metamorphicCobblestoneID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Cobblestone ID:", 2001).getInt();
        
        igneousStoneBrickID = config.get(Configuration.CATEGORY_BLOCK, "Igneous Brick ID:", 2002).getInt();
        metamorphicStoneBrickID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Brick ID:", 2003).getInt();
        
        anthraciteCoalID = config.get(Configuration.CATEGORY_BLOCK, "Anthracite Block ID:", 2004).getInt();
        
        igneousBrickSlabHalfId = config.get(Configuration.CATEGORY_BLOCK, "Igneous Stone Brick Slab ID (half):", 2005).getInt();
        igneousBrickSlabFullId = config.get(Configuration.CATEGORY_BLOCK, "Igneous Stone Brick Slab ID (full):", 2006).getInt();
        
        metamorphicBrickSlabHalfID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Stone Brick Slab ID (half):", 2007).getInt();
        metamorphicBrickSlabFullID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Stone Brick Slab ID (full):", 2008).getInt();
        
        ligniteCoalID = config.get(Configuration.CATEGORY_BLOCK, "Lignite Item ID:", 550).getInt();
        
        biomeSize = config.get(Configuration.CATEGORY_GENERAL, "Biome size (warning: exponential): ", 45).getInt();
        addOreDictRecipes = config.get(Configuration.CATEGORY_GENERAL, "Modify all recipes to include Underground Biomes blocks (could be buggy)", true).getBoolean(true);
        vanillaStoneBiomes = config.get(Configuration.CATEGORY_GENERAL, "Generate Vanilla Stone Biomes (Could cause sharp biome transitions is changed while playing the same world", false).getBoolean(false);
        
        config.save();
    }
    
    @Init
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderThings();
        
        tabModBlocks = new CreativeTabModBlocks(CreativeTabs.creativeTabArray.length, "Underground Biomes Blocks");
        tabModItems = new CreativeTabModBlocks(CreativeTabs.creativeTabArray.length, "Underground Biomes Items");
        
        //rocks
        
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
        };
        List recipes = CraftingManager.getInstance().getRecipeList();
        List<IRecipe> recipesToRemove = new ArrayList<IRecipe>();
        List<IRecipe> recipesToAdd = new ArrayList<IRecipe>();
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
        for (Object obj : recipes)
        {
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
                    recipesToRemove.add(recipe);
                    recipesToAdd.add((ShapedOreRecipe)shapedConstr.newInstance(recipe, replacements));
                    numReplaced++;
                    System.out.println("Changed shaped recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapelessRecipes)
            {
                ShapelessRecipes recipe = (ShapelessRecipes)obj;
                if (containsMatch(true, (ItemStack[])recipe.recipeItems.toArray(new ItemStack[recipe.recipeItems.size()]), replaceStacks))
                {
                    recipesToRemove.add((IRecipe)obj);
                    recipesToAdd.add((ShapelessOreRecipe)shapelessConstr.newInstance(recipe, replacements));
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

        recipes.removeAll(recipesToRemove);
        recipes.addAll(recipesToAdd);
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
        if (worldGen == null)
        {
            worldGen = new WorldGenManager(worldSeed, world.getWorldInfo().getTerrainType(), world);
        }
        worldGen.onBiomeDecorate(event);
    }
}

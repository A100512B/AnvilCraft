package dev.dubhe.anvilcraft.init;

import static dev.dubhe.anvilcraft.AnvilCraft.REGISTRATE;
import static dev.dubhe.anvilcraft.api.power.IPowerComponent.OVERLOAD;
import static dev.dubhe.anvilcraft.api.power.IPowerComponent.SWITCH;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.api.power.IPowerComponent;
import dev.dubhe.anvilcraft.block.AutoCrafterBlock;
import dev.dubhe.anvilcraft.block.BlockPlacerBlock;
import dev.dubhe.anvilcraft.block.ChargeCollectorBlock;
import dev.dubhe.anvilcraft.block.ChuteBlock;
import dev.dubhe.anvilcraft.block.CorruptedBeaconBlock;
import dev.dubhe.anvilcraft.block.CrabTrapBlock;
import dev.dubhe.anvilcraft.block.CreativeGeneratorBlock;
import dev.dubhe.anvilcraft.block.FerriteCoreMagnetBlock;
import dev.dubhe.anvilcraft.block.HeaterBlock;
import dev.dubhe.anvilcraft.block.HollowMagnetBlock;
import dev.dubhe.anvilcraft.block.JewelCraftingTable;
import dev.dubhe.anvilcraft.block.LavaCauldronBlock;
import dev.dubhe.anvilcraft.block.LoadMonitorBlock;
import dev.dubhe.anvilcraft.block.MagnetBlock;
import dev.dubhe.anvilcraft.block.MeltGemCauldron;
import dev.dubhe.anvilcraft.block.MobAmberBlock;
import dev.dubhe.anvilcraft.block.OverseerBlock;
import dev.dubhe.anvilcraft.block.PiezoelectricCrystalBlock;
import dev.dubhe.anvilcraft.block.PowerConverterBigBlock;
import dev.dubhe.anvilcraft.block.PowerConverterMiddleBlock;
import dev.dubhe.anvilcraft.block.PowerConverterSmallBlock;
import dev.dubhe.anvilcraft.block.RemoteTransmissionPoleBlock;
import dev.dubhe.anvilcraft.block.ResentfulAmberBlock;
import dev.dubhe.anvilcraft.block.ResinBlock;
import dev.dubhe.anvilcraft.block.RoyalAnvilBlock;
import dev.dubhe.anvilcraft.block.RoyalGrindstone;
import dev.dubhe.anvilcraft.block.RoyalSmithingTableBlock;
import dev.dubhe.anvilcraft.block.SimpleChuteBlock;
import dev.dubhe.anvilcraft.block.StampingPlatformBlock;
import dev.dubhe.anvilcraft.block.TransmissionPoleBlock;
import dev.dubhe.anvilcraft.block.state.Half;
import dev.dubhe.anvilcraft.data.generator.AnvilCraftDatagen;
import dev.dubhe.anvilcraft.data.generator.recipe.SmashBlockRecipesLoader;
import dev.dubhe.anvilcraft.data.recipe.anvil.AnvilRecipe;
import dev.dubhe.anvilcraft.data.recipe.anvil.RecipeOutcome;
import dev.dubhe.anvilcraft.data.recipe.anvil.RecipePredicate;
import dev.dubhe.anvilcraft.data.recipe.anvil.outcome.SelectOne;
import dev.dubhe.anvilcraft.data.recipe.anvil.outcome.SpawnItem;
import dev.dubhe.anvilcraft.data.recipe.anvil.predicate.HasItem;
import dev.dubhe.anvilcraft.data.recipe.anvil.predicate.HasItemIngredient;
import dev.dubhe.anvilcraft.item.CursedBlockItem;
import dev.dubhe.anvilcraft.item.EndDustBlockItem;
import dev.dubhe.anvilcraft.item.HasMobBlockItem;
import dev.dubhe.anvilcraft.item.OverseerBlockItem;
import dev.dubhe.anvilcraft.item.PlaceInWaterBlockItem;
import dev.dubhe.anvilcraft.item.RemoteTransmissionPoleBlockItem;
import dev.dubhe.anvilcraft.item.ResinBlockItem;
import dev.dubhe.anvilcraft.item.TransmissionPoleBlockItem;
import java.util.Map;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;

public class ModBlocks {

    public static final BlockEntry<? extends Block> STAMPING_PLATFORM = REGISTRATE
        .block("stamping_platform", StampingPlatformBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
                .pattern("BAB")
                .pattern("B B")
                .pattern("B B")
                .define('A', ModItemTags.IRON_PLATES)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_" + ModItemTags.IRON_PLATES.location().getPath(),
                    AnvilCraftDatagen.has(ModItemTags.IRON_PLATES))
                .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_INGOT),
                    AnvilCraftDatagen.has(Items.IRON_INGOT))
                .save(provider);
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
                .pattern("BAB")
                .pattern("B B")
                .pattern("B B")
                .define('A', ModItemTags.IRON_PLATES_FORGE)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_" + ModItemTags.IRON_PLATES_FORGE.location().getPath(),
                    AnvilCraftDatagen.has(ModItemTags.IRON_PLATES_FORGE))
                .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_INGOT),
                    AnvilCraftDatagen.has(Items.IRON_INGOT))
                .save(provider, BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_forge");
        })
        .register();
    public static final BlockEntry<? extends Block> CORRUPTED_BEACON = REGISTRATE
        .block("corrupted_beacon", CorruptedBeaconBlock::new)
        .initialProperties(() -> Blocks.BEACON)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> ROYAL_ANVIL = REGISTRATE
        .block("royal_anvil", RoyalAnvilBlock::new)
        .initialProperties(() -> Blocks.ANVIL)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.ANVIL, ModBlockTags.CANT_BROKEN_ANVIL, BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> MAGNET_BLOCK = REGISTRATE
        .block("magnet_block", MagnetBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(ModBlockTags.MAGNET, BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .unlockedBy("hasitem", RegistrateRecipeProvider.has(ModItems.MAGNET_INGOT))
            .save(provider)
        )
        .register();
    public static final BlockEntry<? extends Block> HOLLOW_MAGNET_BLOCK = REGISTRATE
        .block("hollow_magnet_block", HollowMagnetBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(ModBlockTags.MAGNET, BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
            .pattern("AAA")
            .pattern("A A")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .unlockedBy("hasitem", RegistrateRecipeProvider.has(ModItems.MAGNET_INGOT))
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> FERRITE_CORE_MAGNET_BLOCK = REGISTRATE
        .block("ferrite_core_magnet_block", FerriteCoreMagnetBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::randomTicks)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(ModBlockTags.MAGNET, BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .define('B', Items.IRON_INGOT)
            .unlockedBy("has_magnet_ingot", RegistrateRecipeProvider.has(ModItems.MAGNET_INGOT))
            .unlockedBy("has_iron_ingot", RegistrateRecipeProvider.has(Items.IRON_INGOT))
            .save(provider)
        )
        .register();
    public static final BlockEntry<? extends Block> CHUTE = REGISTRATE
        .block("chute", ChuteBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate((ctx, provider) -> {
        })
        .item(BlockItem::new)
        .onRegister(blockItem -> Item.BY_BLOCK.put(ModBlocks.SIMPLE_CHUTE.get(), blockItem))
        .build()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.CHUTE)
            .pattern("A A")
            .pattern("ABA")
            .pattern(" A ")
            .define('A', Items.IRON_INGOT)
            .define('B', Items.DROPPER)
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_INGOT),
                AnvilCraftDatagen.has(Items.IRON_INGOT))
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.DROPPER),
                AnvilCraftDatagen.has(Items.DROPPER))
            .save(provider)
        )
        .register();
    public static final BlockEntry<SimpleChuteBlock> SIMPLE_CHUTE = REGISTRATE
        .block("simple_chute", SimpleChuteBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate((ctx, provider) -> {
        })
        .loot((tables, block) -> tables.dropOther(block, ModBlocks.CHUTE))
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE)
        .register();
    public static final BlockEntry<? extends Block> AUTO_CRAFTER = REGISTRATE
        .block("auto_crafter", AutoCrafterBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
            .pattern("ABA")
            .pattern("CDA")
            .pattern("AEA")
            .define('A', Items.IRON_INGOT)
            .define('B', Items.CRAFTING_TABLE)
            .define('C', Items.DROPPER)
            .define('D', ModItems.MAGNETOELECTRIC_CORE)
            .define('E', ModItems.CIRCUIT_BOARD)
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_INGOT),
                AnvilCraftDatagen.has(Items.IRON_INGOT))
            .unlockedBy(
                AnvilCraftDatagen.hasItem(Items.CRAFTING_TABLE),
                AnvilCraftDatagen.has(Items.CRAFTING_TABLE)
            )
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.DROPPER),
                AnvilCraftDatagen.has(Items.DROPPER))
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE)
            )
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.CIRCUIT_BOARD),
                AnvilCraftDatagen.has(ModItems.CIRCUIT_BOARD)
            )
            .save(provider)
        )
        .register();
    public static final BlockEntry<? extends Block> ROYAL_GRINDSTONE = REGISTRATE
        .block("royal_grindstone", RoyalGrindstone::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> ROYAL_SMITHING_TABLE = REGISTRATE
        .block("royal_smithing_table", RoyalSmithingTableBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> ROYAL_STEEL_BLOCK = REGISTRATE
        .block("royal_steel_block", Block::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(properties -> properties.explosionResistance(15.0F))
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS, ModBlockTags.OVERSEER_BASE)
        .recipe((ctx, provider) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ROYAL_STEEL_INGOT)
                .unlockedBy("hasitem", RegistrateRecipeProvider.has(ModItems.ROYAL_STEEL_INGOT))
                .save(provider);
            AnvilRecipe.Builder.create(RecipeCategory.MISC)
                .icon(ModItems.ROYAL_STEEL_INGOT)
                .hasBlock(ModBlocks.HEATER.get(), new Vec3(0.0, -2.0, 0.0),
                    Map.entry(OVERLOAD, false))
                .hasBlock(Blocks.CAULDRON)
                .hasItemIngredient(new Vec3(0.0, -1.0, 0.0), 3, Items.IRON_BLOCK)
                .hasItemIngredient(new Vec3(0.0, -1.0, 0.0), 1, Items.DIAMOND_BLOCK)
                .hasItemIngredient(new Vec3(0.0, -1.0, 0.0), 3, Items.AMETHYST_BLOCK)
                .hasItemIngredient(new Vec3(0.0, -1.0, 0.0), 1, ModItemTags.GEM_BLOCKS)
                .spawnItem(new Vec3(0.0, -1.0, 0.0), ctx.get().asItem(), 1)
                .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_BLOCK),
                    AnvilCraftDatagen.has(Items.IRON_BLOCK))
                .unlockedBy(AnvilCraftDatagen.hasItem(Items.DIAMOND_BLOCK),
                    AnvilCraftDatagen.has(Items.DIAMOND_BLOCK))
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(Items.AMETHYST_BLOCK),
                    AnvilCraftDatagen.has(Items.AMETHYST_BLOCK)
                )
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(ModItemTags.GEM_BLOCKS),
                    AnvilCraftDatagen.has(ModItemTags.GEM_BLOCKS)
                )
                .save(provider, AnvilCraft.of("heating/"
                    + BuiltInRegistries.ITEM.getKey(ctx.get().asItem()).getPath()));
        })
        .register();
    public static final BlockEntry<? extends Block> SMOOTH_ROYAL_STEEL_BLOCK = REGISTRATE
        .block("smooth_royal_steel_block", Block::new)
        .tag(ModBlockTags.OVERSEER_BASE)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(properties -> properties.explosionResistance(15.0F))
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> CUT_ROYAL_STEEL_BLOCK = REGISTRATE
        .block("cut_royal_steel_block", Block::new)
        .tag(ModBlockTags.OVERSEER_BASE)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(properties -> properties.explosionResistance(15.0F))
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get(), 4)
            .pattern("AA")
            .pattern("AA")
            .define('A', ModBlocks.ROYAL_STEEL_BLOCK)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModBlocks.ROYAL_STEEL_BLOCK.asItem()),
                AnvilCraftDatagen.has(ModBlocks.ROYAL_STEEL_BLOCK))
            .save(provider, AnvilCraft.of("craft/cut_royal_steel_block"))
        )
        .register();
    public static final BlockEntry<? extends Block> CUT_ROYAL_STEEL_SLAB = REGISTRATE
        .block("cut_royal_steel_slab", SlabBlock::new)
        .tag(ModBlockTags.OVERSEER_BASE)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(properties -> properties.explosionResistance(15.0F))
        .blockstate((ctx, provider) -> provider.slabBlock(ctx.get(),
            AnvilCraft.of("block/cut_royal_steel_block"),
            AnvilCraft.of("block/cut_royal_steel_block")))
        .simpleItem()
        .loot((tables, block) -> tables.add(block, tables::createSlabItemTable))
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get(), 6)
            .pattern("AAA")
            .define('A', ModBlocks.CUT_ROYAL_STEEL_BLOCK)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModBlocks.CUT_ROYAL_STEEL_BLOCK.asItem()),
                AnvilCraftDatagen.has(ModBlocks.CUT_ROYAL_STEEL_BLOCK))
            .save(provider, AnvilCraft.of("craft/cut_royal_steel_slab")))
        .register();
    public static final BlockEntry<? extends Block> CUT_ROYAL_STEEL_STAIRS = REGISTRATE
        .block("cut_royal_steel_stairs", (properties) ->
            new StairBlock(ModBlocks.CUT_ROYAL_STEEL_BLOCK.getDefaultState(), properties))
        .tag(ModBlockTags.OVERSEER_BASE)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(properties -> properties.explosionResistance(15.0F))
        .blockstate((ctx, provider) -> provider.stairsBlock(ctx.get(),
            AnvilCraft.of("block/cut_royal_steel_block")))
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get(), 4)
            .pattern("A  ")
            .pattern("AA ")
            .pattern("AAA")
            .define('A', ModBlocks.CUT_ROYAL_STEEL_BLOCK)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModBlocks.CUT_ROYAL_STEEL_BLOCK.asItem()),
                AnvilCraftDatagen.has(ModBlocks.CUT_ROYAL_STEEL_BLOCK))
            .save(provider, AnvilCraft.of("craft/cut_royal_steel_stairs")))
        .register();
    public static final BlockEntry<? extends Block> LAVA_CAULDRON = REGISTRATE
        .block("lava_cauldron", LavaCauldronBlock::new)
        .initialProperties(() -> Blocks.LAVA_CAULDRON)
        .properties(properties -> properties.lightLevel(blockState ->
            blockState.getValue(LayeredCauldronBlock.LEVEL) * 5))
        .blockstate((ctx, provider) -> {
        })
        .loot((tables, block) -> tables.dropOther(block, Items.CAULDRON))
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> CURSED_GOLD_BLOCK = REGISTRATE
        .block("cursed_gold_block", Block::new)
        .initialProperties(() -> Blocks.GOLD_BLOCK)
        .item(CursedBlockItem::new)
        .build()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModItems.CURSED_GOLD_INGOT)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModItems.CURSED_GOLD_INGOT),
                AnvilCraftDatagen.has(ModItems.CURSED_GOLD_INGOT))
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> TOPAZ_BLOCK = REGISTRATE
        .block("topaz_block", Block::new)
        .initialProperties(() -> Blocks.EMERALD_BLOCK)
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModItems.TOPAZ)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModItems.TOPAZ),
                AnvilCraftDatagen.has(ModItems.TOPAZ))
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> RUBY_BLOCK = REGISTRATE
        .block("ruby_block", Block::new)
        .initialProperties(() -> Blocks.EMERALD_BLOCK)
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModItems.RUBY)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModItems.RUBY),
                AnvilCraftDatagen.has(ModItems.RUBY))
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> SAPPHIRE_BLOCK = REGISTRATE
        .block("sapphire_block", Block::new)
        .initialProperties(() -> Blocks.EMERALD_BLOCK)
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModItems.SAPPHIRE)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModItems.SAPPHIRE),
                AnvilCraftDatagen.has(ModItems.SAPPHIRE))
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> RESIN_BLOCK = REGISTRATE
        .block("resin_block", ResinBlock::new)
        .initialProperties(() -> Blocks.SLIME_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .properties(properties -> properties.sound(SoundType.HONEY_BLOCK))
        .item(ResinBlockItem::new)
        .build()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModItems.RESIN)
            .unlockedBy(AnvilCraftDatagen.hasItem(ModItems.RESIN),
                AnvilCraftDatagen.has(ModItems.RESIN))
            .save(provider))
        .register();

    public static final BlockEntry<? extends Block> AMBER_BLOCK = REGISTRATE
        .block("amber_block", HalfTransparentBlock::new)
        .initialProperties(() -> Blocks.EMERALD_BLOCK)
        .blockstate((ctx, provider) -> {
        })
        .properties(BlockBehaviour.Properties::noOcclusion)
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.AMBER)
                .unlockedBy(AnvilCraftDatagen.hasItem(ModItems.AMBER),
                    AnvilCraftDatagen.has(ModItems.AMBER))
                .save(provider);
            ItemPredicate.Builder item = ItemPredicate.Builder
                .item()
                .of(ModBlocks.RESIN_BLOCK)
                .withCount(MinMaxBounds.Ints.atLeast(1));
            HasItem hasItem = new HasItemIngredient(new Vec3(0.0, -1.0, 0.0),
                item.build()).notHasTag("entity");
            AnvilRecipe.Builder.create(RecipeCategory.MISC)
                .icon(ctx.get())
                .hasBlock(
                    ModBlocks.CORRUPTED_BEACON.get(),
                    new Vec3(0.0, -2.0, 0.0),
                    Map.entry(CorruptedBeaconBlock.LIT, true)
                )
                .hasBlock(Blocks.CAULDRON)
                .addPredicates(hasItem)
                .spawnItem(new Vec3(0.0, -1.0, 0.0), ctx.get(), 1)
                .unlockedBy(AnvilCraftDatagen.hasItem(ctx.get()),
                    AnvilCraftDatagen.has(ModBlocks.RESIN_BLOCK))
                .save(
                    provider,
                    AnvilCraft.of(
                        "timewarp/" + BuiltInRegistries.ITEM.getKey(ctx.get().asItem()).getPath())
                );
        })
        .register();

    public static final BlockEntry<MobAmberBlock> MOB_AMBER_BLOCK = REGISTRATE
        .block("mob_amber_block", MobAmberBlock::new)
        .blockstate((ctx, provider) -> {
        })
        .item(HasMobBlockItem::new)
        .recipe((ctx, provider) -> {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("is_monster", false);
            ItemPredicate.Builder item = ItemPredicate.Builder
                .item()
                .of(ModBlocks.RESIN_BLOCK)
                .withCount(MinMaxBounds.Ints.atLeast(1))
                .hasNbt(tag);
            tag = new CompoundTag();
            tag.putBoolean("is_monster", true);
            ItemPredicate.Builder monster = ItemPredicate.Builder
                .item()
                .of(ModBlocks.RESIN_BLOCK)
                .withCount(MinMaxBounds.Ints.atLeast(1))
                .hasNbt(tag);
            RecipePredicate hasItem = new HasItemIngredient(new Vec3(0.0, -1.0, 0.0), item.build())
                .hasTag("entity")
                .saveItemData("resin");
            RecipePredicate hasItemMonster = new HasItemIngredient(new Vec3(0.0, -1.0, 0.0),
                monster.build())
                .hasTag("entity")
                .saveItemData("resin");
            RecipeOutcome spawnItem0 = new SpawnItem(
                new Vec3(0.0, -1.0, 0.0),
                1.0,
                ctx.get().getDefaultInstance()
            ).loadItemData("resin");
            RecipeOutcome spawnItem1 = new SpawnItem(
                new Vec3(0.0, -1.0, 0.0),
                0.95,
                ctx.get().getDefaultInstance()
            ).loadItemData("resin");
            RecipeOutcome spawnItem2 = new SpawnItem(
                new Vec3(0.0, -1.0, 0.0),
                0.05,
                ModBlocks.RESENTFUL_AMBER_BLOCK.asItem().getDefaultInstance()
            ).loadItemData("resin");
            AnvilRecipe.Builder.create(RecipeCategory.MISC)
                .icon(ctx.get())
                .hasBlock(
                    ModBlocks.CORRUPTED_BEACON.get(),
                    new Vec3(0.0, -2.0, 0.0),
                    Map.entry(CorruptedBeaconBlock.LIT, true)
                )
                .hasBlock(Blocks.CAULDRON)
                .addPredicates(hasItem)
                .addOutcomes(spawnItem0)
                .unlockedBy(AnvilCraftDatagen.hasItem(ctx.get()),
                    AnvilCraftDatagen.has(ModBlocks.RESIN_BLOCK))
                .save(
                    provider,
                    AnvilCraft.of(
                        "timewarp/" + BuiltInRegistries.ITEM.getKey(ctx.get().asItem()).getPath())
                );
            AnvilRecipe.Builder.create(RecipeCategory.MISC)
                .icon(ctx.get())
                .hasBlock(
                    ModBlocks.CORRUPTED_BEACON.get(),
                    new Vec3(0.0, -2.0, 0.0),
                    Map.entry(CorruptedBeaconBlock.LIT, true)
                )
                .hasBlock(Blocks.CAULDRON)
                .addPredicates(hasItemMonster)
                .addOutcomes(new SelectOne().add(spawnItem1).add(spawnItem2))
                .unlockedBy(AnvilCraftDatagen.hasItem(ctx.get()),
                    AnvilCraftDatagen.has(ModBlocks.RESIN_BLOCK))
                .save(
                    provider,
                    AnvilCraft.of(
                        "timewarp/"
                            + BuiltInRegistries.ITEM.getKey(ctx.get().asItem()).getPath()
                            + "_resentful"
                    )
                );
        })
        .build()
        .initialProperties(ModBlocks.AMBER_BLOCK)
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();

    public static final BlockEntry<ResentfulAmberBlock> RESENTFUL_AMBER_BLOCK = REGISTRATE
        .block("resentful_amber_block", ResentfulAmberBlock::new)
        .blockstate((ctx, provider) -> {
        })
        .item(HasMobBlockItem::new)
        .build()
        .initialProperties(ModBlocks.AMBER_BLOCK)
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();

    public static final BlockEntry<? extends Block> CREATIVE_GENERATOR = REGISTRATE
        .block("creative_generator", CreativeGeneratorBlock::new)
        .initialProperties(ModBlocks.MAGNET_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> HEATER = REGISTRATE
        .block("heater", HeaterBlock::new)
        .initialProperties(ModBlocks.MAGNET_BLOCK)
        .properties(properties -> properties.noOcclusion()
            .lightLevel(state -> state.getValue(OVERLOAD) ? 0 : 15))
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("ABA")
            .pattern("BCB")
            .pattern("BBB")
            .define('A', Items.TERRACOTTA)
            .define('B', Items.IRON_INGOT)
            .define('C', ModItems.MAGNETOELECTRIC_CORE)
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.TERRACOTTA),
                AnvilCraftDatagen.has(Items.TERRACOTTA))
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_INGOT),
                AnvilCraftDatagen.has(Items.IRON_INGOT))
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE)
            )
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> TRANSMISSION_POLE = REGISTRATE
        .block("transmission_pole", TransmissionPoleBlock::new)
        .initialProperties(ModBlocks.MAGNET_BLOCK)
        .properties(properties -> properties.noOcclusion()
            .lightLevel(
                state -> {
                    if (state.getValue(TransmissionPoleBlock.HALF) != Half.TOP)
                        return 0;
                    if (state.getValue(SWITCH) == IPowerComponent.Switch.OFF)
                        return 0;
                    if (state.getValue(OVERLOAD))
                        return 6;
                    return 15;
                }
            )
        )
        .blockstate((ctx, provider) -> {
        })
        .item(TransmissionPoleBlockItem::new)
        .model((ctx, provider) -> {
        })
        .build()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("A")
            .pattern("B")
            .pattern("C")
            .define('A', ModItems.MAGNETOELECTRIC_CORE)
            .define('B', Items.LIGHTNING_ROD)
            .define('C', Items.IRON_BLOCK)
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE)
            )
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.LIGHTNING_ROD),
                AnvilCraftDatagen.has(Items.LIGHTNING_ROD))
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.IRON_BLOCK),
                AnvilCraftDatagen.has(Items.IRON_BLOCK))
            .save(provider))
        .register();
    public static final BlockEntry<CrabTrapBlock> CRAB_TRAP = REGISTRATE
        .block("crab_trap", CrabTrapBlock::new)
        .properties(p -> p.sound(SoundType.SCAFFOLDING).strength(2))
        .blockstate((ctx, provider) -> {
        })
        .properties(BlockBehaviour.Properties::noOcclusion)
        .item(PlaceInWaterBlockItem::new)
        .build()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_AXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("ABA")
            .pattern("B B")
            .pattern("ABA")
            .define('A', Items.STICK)
            .define('B', Items.STRING)
            .unlockedBy("hasitem", RegistrateRecipeProvider.has(Items.STRING))
            .save(provider))
        .register();
    public static final BlockEntry<? extends Block> CINERITE = REGISTRATE
        .block("cinerite", GravelBlock::new)
        .initialProperties(() -> Blocks.SAND)
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_SHOVEL)
        .register();
    public static final BlockEntry<? extends Block> QUARTZ_SAND = REGISTRATE
        .block("quartz_sand", GravelBlock::new)
        .initialProperties(() -> Blocks.SAND)
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_SHOVEL)
        .register();
    public static final BlockEntry<? extends Block> TEMPERING_GLASS = REGISTRATE
        .block("tempering_glass", GlassBlock::new)
        .initialProperties(() -> Blocks.GLASS)
        .properties(properties -> properties.explosionResistance(15.0F))
        .blockstate((ctx, provider) -> {
            provider.simpleBlock(ctx.get());
            provider.models().cubeAll(ctx.getName(), provider.modLoc("block/" + ctx.getName()))
                .renderType("translucent");
        })
        .simpleItem()
        .defaultLoot()
        .register();
    public static final BlockEntry<JewelCraftingTable> JEWEL_CRAFTING_TABLE = REGISTRATE
        .block("jewelcrafting_table", JewelCraftingTable::new)
        .initialProperties(() -> Blocks.STONE)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate((ctx, provider) -> {
        })
        .simpleItem()
        .defaultLoot()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .lang("Jewel Crafting Table")
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("ABC")
            .pattern("DDD")
            .pattern("F F")
            .define('A', Blocks.GRINDSTONE)
            .define('B', Blocks.GLASS)
            .define('C', Blocks.GRINDSTONE)
            .define('D', Blocks.SMOOTH_STONE)
            .define('F', ItemTags.PLANKS)
            .unlockedBy(AnvilCraftDatagen.hasItem(Blocks.GRINDSTONE),
                AnvilCraftDatagen.has(Blocks.GRINDSTONE))
            .unlockedBy(AnvilCraftDatagen.hasItem(Blocks.STONECUTTER),
                AnvilCraftDatagen.has(Blocks.STONECUTTER))
            .save(provider))
        .register();
    public static final BlockEntry<FallingBlock> NETHER_DUST = REGISTRATE
        .block("nether_dust", FallingBlock::new)
        .simpleItem()
        .initialProperties(() -> Blocks.BLACK_CONCRETE_POWDER)
        .recipe((ctx, provider) -> SmashBlockRecipesLoader.smash(Blocks.NETHERRACK, ctx.get(),
            provider))
        .register();
    public static final BlockEntry<Block> END_DUST = REGISTRATE
        .block("end_dust", Block::new)
        .item(EndDustBlockItem::new)
        .build()
        .initialProperties(() -> Blocks.BLACK_CONCRETE_POWDER)
        .recipe(
            (ctx, provider) -> SmashBlockRecipesLoader.smash(Blocks.END_STONE, ctx.get(), provider))
        .register();
    public static final BlockEntry<PiezoelectricCrystalBlock> PIEZOELECTRIC_CRYSTAL =
        REGISTRATE
            .block("piezoelectric_crystal", PiezoelectricCrystalBlock::new)
            .simpleItem()
            .properties(BlockBehaviour.Properties::noOcclusion)
            .blockstate((ctx, provider) -> {
            })
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .recipe((ctx, provider) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                    .pattern("ABA")
                    .pattern(" B ")
                    .pattern("ABA")
                    .define('A', Items.COPPER_INGOT)
                    .define('B', ModItemTags.QUARTZ_BLOCKS)
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(Items.COPPER_INGOT),
                        AnvilCraftDatagen.has(Items.COPPER_INGOT)
                    )
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(ModItemTags.QUARTZ_BLOCKS),
                        AnvilCraftDatagen.has(ModItemTags.QUARTZ_BLOCKS)
                    )
                    .save(provider);
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                    .pattern("ABA")
                    .pattern(" B ")
                    .pattern("ABA")
                    .define('A', Items.COPPER_INGOT)
                    .define('B', ModItemTags.AMETHYST_BLOCKS)
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(Items.COPPER_INGOT),
                        AnvilCraftDatagen.has(Items.COPPER_INGOT)
                    )
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(ModItemTags.AMETHYST_BLOCKS),
                        AnvilCraftDatagen.has(ModItemTags.AMETHYST_BLOCKS)
                    )
                    .save(
                        provider,
                        BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_amethyst"
                    );
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                    .pattern("ABA")
                    .pattern(" B ")
                    .pattern("ABA")
                    .define('A', Items.COPPER_INGOT)
                    .define('B', ModItemTags.QUARTZ_BLOCKS_FORGE)
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(Items.COPPER_INGOT),
                        AnvilCraftDatagen.has(Items.COPPER_INGOT)
                    )
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(ModItemTags.QUARTZ_BLOCKS_FORGE),
                        AnvilCraftDatagen.has(ModItemTags.QUARTZ_BLOCKS_FORGE)
                    )
                    .save(
                        provider,
                        BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_forge"
                    );
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                    .pattern("ABA")
                    .pattern(" B ")
                    .pattern("ABA")
                    .define('A', Items.COPPER_INGOT)
                    .define('B', ModItemTags.AMETHYST_BLOCKS_FORGE)
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(Items.COPPER_INGOT),
                        AnvilCraftDatagen.has(Items.COPPER_INGOT)
                    )
                    .unlockedBy(
                        AnvilCraftDatagen.hasItem(ModItemTags.AMETHYST_BLOCKS_FORGE),
                        AnvilCraftDatagen.has(ModItemTags.AMETHYST_BLOCKS_FORGE)
                    )
                    .save(
                        provider,
                        BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_amethyst_forge"
                    );
            })
            .register();
    public static final BlockEntry<ChargeCollectorBlock> CHARGE_COLLECTOR =
        REGISTRATE
            .block("charge_collector", ChargeCollectorBlock::new)
            .simpleItem()
            .properties(BlockBehaviour.Properties::noOcclusion)
            .blockstate((ctx, provider) -> {
            })
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern(" A ")
                .pattern("B B")
                .pattern("CCC")
                .define('A', ModItems.MAGNETOELECTRIC_CORE)
                .define('B', Items.COPPER_INGOT)
                .define('C', Items.IRON_INGOT)
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                    AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE)
                )
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(Items.COPPER_INGOT),
                    AnvilCraftDatagen.has(Items.COPPER_INGOT)
                )
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(Items.IRON_INGOT),
                    AnvilCraftDatagen.has(Items.IRON_INGOT)
                )
                .save(provider))
            .register();

    public static final BlockEntry<MeltGemCauldron> MELT_GEM_CAULDRON = REGISTRATE
        .block("melt_gem_cauldron", MeltGemCauldron::new)
        .initialProperties(() -> Blocks.CAULDRON)
        .blockstate((ctx, provider) -> {
        })
        .loot((tables, block) -> tables.dropOther(block, Items.CAULDRON))
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();

    public static final BlockEntry<PowerConverterSmallBlock> POWER_CONVERTER_SMALL = REGISTRATE
        .block("power_converter_small", PowerConverterSmallBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(p -> p.lightLevel(state -> {
            if (state.getValue(OVERLOAD))
                return 6;
            else
                return 15;
        }))
        .blockstate((ctx, provider) -> {
        })
        .recipe((ctx, provider) -> {
            VanillaRecipeProvider.stonecutterResultFromBase(
                provider, RecipeCategory.MISC,
                ctx.get().asItem(), ModBlocks.POWER_CONVERTER_MIDDLE, 3
            );
            VanillaRecipeProvider.stonecutterResultFromBase(
                provider, RecipeCategory.MISC,
                ctx.get().asItem(), ModBlocks.POWER_CONVERTER_BIG, 9
            );
        })
        .defaultLoot()
        .item()
        .model((ctx, provider) -> provider.blockItem(ctx))
        .build()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();

    public static final BlockEntry<PowerConverterMiddleBlock> POWER_CONVERTER_MIDDLE = REGISTRATE
        .block("power_converter_middle", PowerConverterMiddleBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(p -> p.lightLevel(state -> {
            if (state.getValue(OVERLOAD))
                return 6;
            else
                return 15;
        }))
        .blockstate((ctx, provider) -> {
        })
        .recipe((ctx, provider) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.POWER_CONVERTER_SMALL)
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(ModBlocks.POWER_CONVERTER_SMALL),
                    AnvilCraftDatagen.has(ModBlocks.POWER_CONVERTER_SMALL)
                )
                .save(
                    provider,
                    BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_from_small"
                );
            VanillaRecipeProvider.stonecutterResultFromBase(
                provider, RecipeCategory.MISC,
                ctx.get().asItem(), ModBlocks.POWER_CONVERTER_BIG, 3
            );
        })
        .defaultLoot()
        .item()
        .model((ctx, provider) -> provider.blockItem(ctx))
        .build()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();

    public static final BlockEntry<PowerConverterBigBlock> POWER_CONVERTER_BIG = REGISTRATE
        .block("power_converter_big", PowerConverterBigBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(p -> p.lightLevel(state -> {
            if (state.getValue(OVERLOAD))
                return 6;
            else
                return 15;
        }))
        .blockstate((ctx, provider) -> {
        })
        .recipe((ctx, provider) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern("A")
                .pattern("B")
                .define('A', ModItems.MAGNETOELECTRIC_CORE)
                .define('B', Items.COPPER_BLOCK)
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                    AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE)
                )
                .save(provider);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.POWER_CONVERTER_MIDDLE)
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(ModBlocks.POWER_CONVERTER_MIDDLE),
                    AnvilCraftDatagen.has(ModBlocks.POWER_CONVERTER_MIDDLE)
                )
                .save(
                    provider,
                    BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_from_middle"
                );
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.POWER_CONVERTER_SMALL)
                .unlockedBy(
                    AnvilCraftDatagen.hasItem(ModBlocks.POWER_CONVERTER_MIDDLE),
                    AnvilCraftDatagen.has(ModBlocks.POWER_CONVERTER_MIDDLE)
                )
                .save(
                    provider,
                    BuiltInRegistries.ITEM.getKey(ctx.get().asItem()) + "_from_small"
                );
        })
        .defaultLoot()
        .item()
        .model((ctx, provider) -> provider.blockItem(ctx))
        .build()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();
    public static final BlockEntry<? extends Block> REMOTE_TRANSMISSION_POLE = REGISTRATE
        .block("remote_transmission_pole", RemoteTransmissionPoleBlock::new)
        .initialProperties(ModBlocks.MAGNET_BLOCK)
        .properties(properties -> properties.noOcclusion()
            .lightLevel(
                state -> {
                    if (state.getValue(RemoteTransmissionPoleBlock.HALF) != Half.TOP) return 0;
                    if (state.getValue(SWITCH) == IPowerComponent.Switch.OFF) return 0;
                    if (state.getValue(OVERLOAD)) return 6;
                    return 15;
                }
            )
        )
        .blockstate((ctx, provider) -> {
        })
        .item(RemoteTransmissionPoleBlockItem::new)
        .model((ctx, provider) -> {
        })
        .build()
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("A")
            .pattern("B")
            .pattern("C")
            .define('A', ModItems.MAGNETOELECTRIC_CORE)
            .define('B', ModBlocks.TRANSMISSION_POLE)
            .define('C', Items.ANVIL)
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE))
            .unlockedBy(AnvilCraftDatagen.hasItem(ModBlocks.TRANSMISSION_POLE),
                AnvilCraftDatagen.has(ModBlocks.TRANSMISSION_POLE))
            .save(provider))
        .register();

    public static final BlockEntry<LoadMonitorBlock> LOAD_MONITOR = REGISTRATE
        .block("load_monitor", LoadMonitorBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(p -> p.lightLevel(state -> {
            if (state.getValue(OVERLOAD)) return 6;
            else return 15;
        }).noOcclusion())
        .blockstate((ctx, provider) -> {
        })
        .defaultLoot()
        .item()
        .model((ctx, provider) -> provider.blockItem(ctx, "_0"))
        .build()
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get(), 4)
            .pattern("A")
            .pattern("B")
            .define('A', Items.COMPASS)
            .define('B', ModItems.MAGNETOELECTRIC_CORE)
            .unlockedBy(
                AnvilCraftDatagen.hasItem(Items.COMPASS),
                AnvilCraftDatagen.has(Items.COMPASS)
            )
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.MAGNETOELECTRIC_CORE),
                AnvilCraftDatagen.has(ModItems.MAGNETOELECTRIC_CORE)
            )
            .save(provider)
        )
        .register();
    public static final BlockEntry<BlockPlacerBlock> BLOCK_PLACER = REGISTRATE
        .block("block_placer", BlockPlacerBlock::new)
        .simpleItem()
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .blockstate((ctx, provider) -> {
        })
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern("BCD")
            .pattern("AAA")
            .define('A', Items.COBBLESTONE)
            .define('B', ModItems.CRAB_CLAW)
            .define('C', Items.REDSTONE)
            .define('D', Items.HOPPER)
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModItems.CRAB_CLAW),
                AnvilCraftDatagen.has(ModItems.CRAB_CLAW)
            )
            .save(provider))
        .register();

    public static final BlockEntry<OverseerBlock> OVERSEER_BLOCK = REGISTRATE
        .block("overseer", OverseerBlock::new)
        .initialProperties(() -> Blocks.OBSIDIAN)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate((ctx, provider) -> {
        })
        .defaultLoot()
        .item(OverseerBlockItem::new)
        .model((ctx, provider) -> {
        })
        .build()
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("ABA")
            .pattern("ABA")
            .pattern("CBC")
            .define('A', Items.OBSIDIAN)
            .define('B', Items.ENDER_EYE)
            .define('C', ModBlocks.ROYAL_STEEL_BLOCK)
            .unlockedBy(
                AnvilCraftDatagen.hasItem(ModBlocks.ROYAL_STEEL_BLOCK),
                AnvilCraftDatagen.has(ModBlocks.ROYAL_STEEL_BLOCK)
            )
            .unlockedBy(
                AnvilCraftDatagen.hasItem(Items.ENDER_EYE),
                AnvilCraftDatagen.has(Items.ENDER_EYE)
            )
            .save(provider)
        )
        .tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .register();

    public static void register() {
    }
}

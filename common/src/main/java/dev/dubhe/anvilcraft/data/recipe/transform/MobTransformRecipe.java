package dev.dubhe.anvilcraft.data.recipe.transform;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeBuilder.ROOT_RECIPE_ADVANCEMENT;

public class MobTransformRecipe implements Recipe<MobTransformContainer> {

    public static final Codec<MobTransformRecipe> CODEC = RecordCodecBuilder.create(ins -> ins.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(o -> o.id),
            ResourceLocation.CODEC
                    .fieldOf("input")
                    .forGetter(o -> BuiltInRegistries.ENTITY_TYPE.getKey(o.input)),
            TransformResult.CODEC.listOf().fieldOf("results").forGetter(o -> o.results)
    ).apply(ins, MobTransformRecipe::new));

    private final ResourceLocation id;
    @Getter
    private EntityType<?> input;
    @Getter
    private List<TransformResult> results;

    /**
     * 生物转化配方
     */
    public MobTransformRecipe(
            ResourceLocation id,
            ResourceLocation input,
            List<TransformResult> results
    ) {
        this.id = id;
        this.results = results;
        this.input = BuiltInRegistries.ENTITY_TYPE.get(input);
    }

    public MobTransformRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public boolean matches(@NotNull MobTransformContainer container, @NotNull Level level) {
        return container.getEntity().getType() == input;
    }

    @Override
    public @NotNull ItemStack assemble(
            @NotNull MobTransformContainer container,
            @NotNull RegistryAccess registryAccess
    ) {
        return ItemStack.EMPTY;
    }

    /**
     * 结果
     */
    public EntityType<?> result(RandomSource randomSource) {
        if (results.size() == 1) return results.get(0).resultEntityType();
        List<TransformResult> sorted = new ArrayList<>(results.stream()
                .sorted(Comparator.comparingDouble(TransformResult::probability))
                .toList());
        List<Double> probList = new ArrayList<>(List.of(0d));
        for (TransformResult transformResult : sorted) {
            probList.add(probList.get(probList.size() - 1) + transformResult.probability());
        }
        double p = randomSource.nextDouble();
        for (int i = 1; i < probList.size(); i++) {
            double end = probList.get(i);
            double start = probList.get(i - 1);
            if (p >= start && p < end) {
                return sorted.get(i - 1).resultEntityType();
            }
        }
        return sorted.get(sorted.size() - 1).resultEntityType();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static Builder builder(ResourceLocation id) {
        return new Builder(id);
    }

    public static Builder builder(String id) {
        return new Builder(new ResourceLocation("anvilcraft", id));
    }

    public enum Serializer implements RecipeSerializer<MobTransformRecipe> {
        INSTANCE;

        @Override
        public @NotNull MobTransformRecipe fromJson(
                @NotNull ResourceLocation recipeId,
                @NotNull JsonObject serializedRecipe
        ) {
            return MobTransformRecipe.CODEC
                    .parse(JsonOps.INSTANCE, serializedRecipe)
                    .getOrThrow(false, ignored -> {
                    });
        }

        @Override
        public @NotNull MobTransformRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            MobTransformRecipe recipe = new MobTransformRecipe(recipeId);
            ResourceLocation inputMobTypeIdentifier = buffer.readResourceLocation();
            recipe.results = buffer.readList(TransformResult::fromByteBuf);
            recipe.input = BuiltInRegistries.ENTITY_TYPE.get(inputMobTypeIdentifier);
            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MobTransformRecipe recipe) {
            ResourceLocation inputMobTypeIdentifier =
                    BuiltInRegistries.ENTITY_TYPE.getKey(recipe.input);
            buffer.writeResourceLocation(inputMobTypeIdentifier);
            buffer.writeCollection(recipe.results, TransformResult::intoByteBuf);
        }
    }


    public enum Type implements RecipeType<MobTransformRecipe> {
        INSTANCE
    }

    public static class Builder {
        private final ResourceLocation id;
        private EntityType<?> inputEntityType;
        private final List<TransformResult> results = new ArrayList<>();
        private Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
        private RecipeCategory category = RecipeCategory.MISC;

        public Builder(ResourceLocation id) {
            this.id = id;
        }

        public Builder category(RecipeCategory category) {
            this.category = category;
            return this;
        }

        public Builder advancement(Advancement.Builder advancement) {
            this.advancement = advancement;
            return this;
        }

        public Builder input(EntityType<?> inputEntityType) {
            this.inputEntityType = inputEntityType;
            return this;
        }

        public Builder result(EntityType<?> resultEntityType, double d) {
            this.results.add(new TransformResult(resultEntityType, d));
            return this;
        }

        /**
         * 构造
         */
        public MobTransformRecipe build() {
            MobTransformRecipe r = new MobTransformRecipe(id);
            r.input = inputEntityType;
            r.results = results;
            return r;
        }

        /**
         * 完成
         */
        public FinishedMobTransformRecipe finish() {
            this.advancement
                    .parent(ROOT_RECIPE_ADVANCEMENT)
                    .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                    .rewards(AdvancementRewards.Builder.recipe(id))
                    .requirements(RequirementsStrategy.OR);
            return new FinishedMobTransformRecipe(
                    build(),
                    advancement,
                    id.withPrefix("recipes/" + this.category.getFolderName() + "/")
            );
        }

        public void accept(Consumer<FinishedRecipe> provider) {
            provider.accept(finish());
        }
    }
}

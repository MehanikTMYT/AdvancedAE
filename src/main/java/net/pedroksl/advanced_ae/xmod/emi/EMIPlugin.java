package net.pedroksl.advanced_ae.xmod.emi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import net.pedroksl.advanced_ae.AdvancedAE;
import net.pedroksl.advanced_ae.client.AAEClient;
import net.pedroksl.advanced_ae.common.definitions.AAEBlocks;
import net.pedroksl.advanced_ae.common.definitions.AAEText;
import net.pedroksl.advanced_ae.recipes.AAERecipeTypes;
import net.pedroksl.advanced_ae.xmod.emi.recipes.EMIReactionChamberRecipe;
import net.pedroksl.ae2addonlib.recipes.IngredientStack;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;

/**
 * Native EMI integration (the fleet's only recipe viewer; JEI/REI plugins were removed).
 */
@EmiEntrypoint
public class EMIPlugin implements EmiPlugin {
    public static final Identifier TEXTURE = AdvancedAE.makeId("textures/guis/emi.png");

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(EMIReactionChamberRecipe.CATEGORY);
        registry.addWorkstation(EMIReactionChamberRecipe.CATEGORY, EmiStack.of(AAEBlocks.REACTION_CHAMBER.stack()));
        adaptRecipeType(registry, AAERecipeTypes.REACTION_CHAMBER, EMIReactionChamberRecipe::new);
        addInfo(registry, AAEBlocks.ADV_PATTERN_PROVIDER, AAEText.AdvPatternProviderEmiDesc.text());
    }

    private static <C extends RecipeInput, T extends Recipe<C>> void adaptRecipeType(
            EmiRegistry registry, RecipeType<T> recipeType, Function<RecipeHolder<T>, ? extends EmiRecipe> adapter) {
        // Same synced recipe source the former JEI plugin used.
        var recipes = AAEClient.instance().getRecipeMapForType(Minecraft.getInstance().level, recipeType);
        recipes.byType(recipeType).stream().map(adapter).forEach(registry::addRecipe);
    }

    private static void addInfo(EmiRegistry registry, ItemLike item, Component... desc) {
        registry.addRecipe(new EmiInfoRecipe(List.of(EmiStack.of(item)), Arrays.stream(desc).toList(), null));
    }

    public static EmiIngredient stackOf(IngredientStack.Item stack) {
        return !stack.isEmpty() ? EmiIngredient.of(stack.getIngredient(), stack.getAmount()) : EmiStack.EMPTY;
    }

    public static EmiIngredient stackOf(IngredientStack.Fluid stack) {
        List<EmiIngredient> list = stack.getIngredient().fluids().stream()
                .map(fluid -> new FluidStack(fluid, stack.getAmount()))
                .<EmiIngredient>map(fs -> EmiStack.of(fs.getFluid(), fs.getAmount()))
                .toList();
        return EmiIngredient.of(list, stack.getAmount());
    }
}

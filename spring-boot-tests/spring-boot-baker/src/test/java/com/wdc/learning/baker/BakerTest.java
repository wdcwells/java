/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.wdc.learning.baker;

import akka.actor.ActorSystem;
import com.ing.baker.compiler.RecipeCompiler;
import com.ing.baker.il.CompiledRecipe;
import com.ing.baker.runtime.akka.AkkaBaker;
import com.ing.baker.runtime.javadsl.Baker;
import com.ing.baker.runtime.javadsl.InteractionInstance;

import java.util.concurrent.CompletableFuture;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2021-03-18
 */
public class BakerTest {

    public static void main(String[] args) {
//        ActorSystem actorSystem = ActorSystem.create("WebshopSystem");
//        Baker baker = AkkaBaker.javaLocalDefault(actorSystem);
//
//        InteractionInstance reserveItemsInstance = InteractionInstance.from(new ReserveItems());
//        CompiledRecipe compiledRecipe = RecipeCompiler.compileRecipe(JWebshopRecipe.recipe);
//
//        CompletableFuture<String> asyncRecipeId = baker.addInteractionInstance(reserveItemsInstance)
//                .thenCompose(ignore -> baker.addRecipe(compiledRecipe));
//
//        // Blocks, not recommended but useful for testing or trying things out
//        String recipeId = asyncRecipeId.join();
//        CompiledRecipe compiledRecipe = RecipeCompiler.compileRecipe(JWebshopRecipe.recipe);
//        System.out.println(compiledRecipe.getRecipeVisualization());


    }


}

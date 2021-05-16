/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.wdc.learning.baker;

import com.ing.baker.recipe.annotations.FiresEvent;
import com.ing.baker.recipe.annotations.RequiresIngredient;
import com.ing.baker.recipe.javadsl.Interaction;
import com.ing.baker.recipe.javadsl.Recipe;

import java.util.List;

import static com.ing.baker.recipe.javadsl.InteractionDescriptor.of;

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
public class JWebshopRecipe {

    public static class OrderPlaced {

        public final String orderId;
        public final List<String> items;

        public OrderPlaced(String orderId, List<String> items) {
            this.orderId = orderId;
            this.items = items;
        }
    }

    public interface ReserveItems extends Interaction {

        interface ReserveItemsOutcome {
        }

        class OrderHadUnavailableItems implements ReserveItemsOutcome {

            public final List<String> unavailableItems;

            public OrderHadUnavailableItems(List<String> unavailableItems) {
                this.unavailableItems = unavailableItems;
            }
        }

        class ItemsReserved implements ReserveItemsOutcome {

            public final List<String> reservedItems;

            public ItemsReserved(List<String> reservedItems) {
                this.reservedItems = reservedItems;
            }
        }

        // The @FireEvent annotation communicates the reflection API about several possible outcome events.
        @FiresEvent(oneOf = {OrderHadUnavailableItems.class, ItemsReserved.class})
        // The @RequiresIngredient annotation communicates the reflection API about the ingredient names that other events
        // must provide to execute this interaction.
        ReserveItemsOutcome apply(@RequiresIngredient("orderId") String id, @RequiresIngredient("items") List<String> items);
    }

    public final static Recipe recipe = new Recipe("WebshopRecipe")
            .withSensoryEvents(OrderPlaced.class)
            .withInteractions(of(ReserveItems.class));

}

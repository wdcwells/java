/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.wdc.learning.baker;

import com.ing.baker.runtime.javadsl.InteractionInstance;

import java.util.List;

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
public class ReserveItems implements JWebshopRecipe.ReserveItems {

    public static final InteractionInstance reserveItemsInstance = InteractionInstance.from(new ReserveItems());

    // The body of this method is going to be executed by the Baker runtime when the ingredients are available.
    @Override
    public ReserveItemsOutcome apply(String id, List<String> items) {
        return new JWebshopRecipe.ReserveItems.ItemsReserved(items);
    }
}

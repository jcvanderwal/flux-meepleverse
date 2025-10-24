package io.fluxzero.training.meepleverse.orders;

import io.fluxzero.sdk.Fluxzero;
import io.fluxzero.sdk.modeling.EntityId;
import io.fluxzero.sdk.tracking.handling.Association;
import io.fluxzero.sdk.tracking.handling.HandleEvent;
import io.fluxzero.sdk.tracking.handling.Stateful;
import io.fluxzero.training.meepleverse.orders.api.model.Order;
import io.fluxzero.training.meepleverse.payments.api.PaymentAccepted;
import io.fluxzero.training.meepleverse.supplier.BackorderItems;

@Stateful
public record OrderProcess(@EntityId String orderId,
                           @Association String orderProcessKey,
                           Order state) {

    @HandleEvent
    void handle(PaymentAccepted event) {
        var order = Fluxzero.<Order>loadEntityValue(event.reference());
        Fluxzero.sendAndForgetCommand(new BackorderItems(order.details().itemIds(), order.orderId(),
                order.details().addressee().fullName()));
        return new OrderProcess(order.)
    }



}
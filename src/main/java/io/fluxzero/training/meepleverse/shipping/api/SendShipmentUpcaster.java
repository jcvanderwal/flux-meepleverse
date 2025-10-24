package io.fluxzero.training.meepleverse.shipping.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.fluxzero.sdk.common.serialization.casting.Upcast;

class SendShipmentUpcaster {

    @Upcast(type = "io.fluxzero.training.meepleverse.shipping.api.ShipmentSent", revision = 0)
    ObjectNode upcastV0toV1(ObjectNode json) {
        if (json.has("itemId")) {
            var itemId = json.remove("itemId");
            json.set("itemIds", json.arrayNode().add(itemId));
        }
        return json;
    }
}
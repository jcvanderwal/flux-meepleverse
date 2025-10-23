package io.fluxzero.training.meepleverse;

import io.fluxzero.common.MessageType;
import io.fluxzero.sdk.Fluxzero;
import io.fluxzero.sdk.tracking.Consumer;
import io.fluxzero.sdk.tracking.handling.HandleError;
import io.fluxzero.sdk.tracking.handling.Trigger;
import io.fluxzero.training.meepleverse.payments.api.ValidatePayment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Consumer(name = "command-dlq", minIndex = 0) class CommandReplayHandler {

    @HandleError
    void retry(Throwable e, @Trigger ValidatePayment failed) {
        Fluxzero.sendCommand(failed);
        log.info(e.getMessage());
    }
}
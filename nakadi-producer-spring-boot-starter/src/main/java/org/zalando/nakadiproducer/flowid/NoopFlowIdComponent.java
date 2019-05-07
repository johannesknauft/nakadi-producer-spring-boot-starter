package org.zalando.nakadiproducer.flowid;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoopFlowIdComponent implements FlowIdComponent {

    @Override
    public String getXFlowIdValue() {
        return null;
    }

    @Override
    public void startTraceIfNoneExists() {
    }
}

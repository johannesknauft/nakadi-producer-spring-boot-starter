package org.zalando.nakadiproducer.flowid;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.zalando.tracer.Tracer;

@RunWith(MockitoJUnitRunner.class)
public class TracerFlowIdComponentTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Tracer tracer;

    @Test
    public void makeSureItWorks() {
        TracerFlowIdComponent flowIdComponent = new TracerFlowIdComponent(tracer);
        when(tracer.get("X-Flow-ID").getValue()).thenReturn("A_FUNKY_VALUE");

        assertThat(flowIdComponent.getXFlowIdKey(), Matchers.equalTo("X-Flow-ID"));
        assertThat(flowIdComponent.getXFlowIdValue(), Matchers.equalTo("A_FUNKY_VALUE"));
    }

    @Test
    public void makeSureTraceWillBeStartedIfNoneHasBeenStartedBefore() {
        TracerFlowIdComponent flowIdComponent = new TracerFlowIdComponent(tracer);
        when(tracer.get("X-Flow-ID").getValue()).thenThrow(new IllegalStateException());

        flowIdComponent.startTraceIfNoneExists();

        verify(tracer).start();
    }

    @Test
    public void wontFailIfTraceHasNotBeenConfiguredInStartTrace() {
        TracerFlowIdComponent flowIdComponent = new TracerFlowIdComponent(tracer);
        when(tracer.get("X-Flow-ID")).thenThrow(new IllegalArgumentException());

        flowIdComponent.startTraceIfNoneExists();

        // then no exception is thrown
    }

    @Test
    public void makeSureTraceWillNotStartedIfOneExists() {
        TracerFlowIdComponent flowIdComponent = new TracerFlowIdComponent(tracer);
        when(tracer.get("X-Flow-ID").getValue()).thenReturn("A_FUNKY_VALUE");

        flowIdComponent.startTraceIfNoneExists();

        verify(tracer, never()).start();
    }

}
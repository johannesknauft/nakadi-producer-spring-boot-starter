package org.zalando.nakadiproducer;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.Collection;

@Getter
@Setter
@ConfigurationProperties(prefix = "nakadi-producer")
public class NakadiProperties {
    private Long transmissionPollingDelay = 1000L;
    private boolean scheduledTransmissionEnabled = true;
    private URI nakadiBaseUri;
    private URI accessTokenUri;
    private Collection<String> accessTokenScopes;
}

package com.github.khazrak.jdocker.docker_api_1_24.network;

import com.github.khazrak.jdocker.DockerClient;
import com.github.khazrak.jdocker.model.api124.IPAMConfig;
import com.github.khazrak.jdocker.model.api124.NetworkConnectEndpointConfig;
import com.github.khazrak.jdocker.model.api124.requests.NetworkConnectRequest;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.khazrak.jdocker.DefaultDockerClient;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by karl on 9/24/16.
 */
public class NetworkConnectDisconnect {
    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(NetworkConnectDisconnect.class);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().usingFilesUnderClasspath("src/test/resources/1_24").port(9779)); // No-args constructor defaults

    @Before
    public void setup() {
        client = new DefaultDockerClient("http://127.0.0.1:9779");
    }

    @After
    public void tearDown() {
        client.close();
    }



    @Test
    public void connect() {
        final String path = "/v1.24%2Fnetworks%2Ftest1%2Fconnect";

        NetworkConnectRequest request = NetworkConnectRequest.builder().networkName("test1").container("mongo").build();

        client.connectContainerToNetwork(request);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void connectWithEndpoint() {
        final String path = "/v1.24%2Fnetworks%2Ftest1%2Fconnect";

        NetworkConnectRequest request = NetworkConnectRequest.builder()
                .networkName("test1")
                .container("mongo")
                .endpointConfig(NetworkConnectEndpointConfig.builder()
                        .config(IPAMConfig.builder()
                                .ipv4Address("172.18.1.1")
                                .build())
                        .build())
                .build();

        client.connectContainerToNetwork(request);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void disconnect() {
        final String path = "/v1.24%2Fnetworks%2Ftest1%2Fdisconnect";

        client.disconnectContainerFromNetwork("mongo","test1",false);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}

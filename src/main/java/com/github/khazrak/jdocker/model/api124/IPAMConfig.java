/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = IPAMConfig.IPAMConfigBuilder.class)
public class IPAMConfig {

    @JsonProperty("IPv4Address")
    private String ipv4Address;

    @JsonProperty("IPv6Address")
    private String ipv6Address;

    @JsonProperty("LinkLocalIPs")
    private List<String> linkLocalIps;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IPAMConfigBuilder {
        @JsonProperty("IPv4Address")
        private String ipv4Address;

        @JsonProperty("IPv6Address")
        private String ipv6Address;

        @JsonProperty("LinkLocalIPs")
        private List<String> linkLocalIps;

    }

}

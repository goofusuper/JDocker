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
package com.github.khazrak.jdocker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = IOServiceBytes.IOServiceBytesBuilder.class)
public class IOServiceBytes {

    @JsonProperty("major")
    private int major;

    @JsonProperty("minor")
    private int minor;

    @JsonProperty("op")
    private String op;

    @JsonProperty("value")
    private long value;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IOServiceBytesBuilder {

        @JsonProperty("major")
        private int major;

        @JsonProperty("minor")
        private int minor;

        @JsonProperty("op")
        private String op;

        @JsonProperty("value")
        private long value;

    }
}

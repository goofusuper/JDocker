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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class ContainerPort {


    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("IP")
    private String ip;

    @Getter
    @JsonProperty("PrivatePort")
    private int privatePort;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("PublicPort")
    private int publicPort;

    @Getter
    @JsonProperty("Type")
    private String type;


    public static class ContainerPortBuilder {
        private String type = "tcp";
    }

}
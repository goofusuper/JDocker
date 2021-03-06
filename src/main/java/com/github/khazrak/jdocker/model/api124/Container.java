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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import com.github.khazrak.jdocker.utils.DockerImageName;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "Id",
        "Names",
        "ImageInfo",
        "ImageID",
        "Command",
        "Created",
        "State",
        "Status",
        "Ports",
        "Labels",
        "SizeRw",
        "SizeRootFs",
        "HostConfig",
        "NetworkSettings",
        "Mounts"
})
@Getter
public class Container {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Names")
    private List<String> names;

    @JsonProperty("Image")
    private DockerImageName image;

    @JsonProperty("ImageID")
    private String imageId;

    @JsonProperty("Command")
    private String command;

    @JsonProperty("Created")
    private long created;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Status")
    private String status;

    @Singular
    @JsonProperty("Ports")
    private List<ContainerPort> ports;

    @Singular
    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonProperty("SizeRw")
    private long sizeRw;

    @JsonProperty("SizeRootFs")
    private long sizeRootFs;

    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonProperty("NetworkSettings")
    private NetworkSettings networkSettings;

    @JsonProperty("Mounts")
    private List<Mount> mounts;

    @Override
    public String toString() {
        String ports = this.ports.toString();
        String names = this.names.toString();
        return id.substring(0,12) + "\t" +
                image + "\t" +
                command + "\t" +
                getCreatedTime() + "\t" +
                status + "\t" +
                ports.substring(1,ports.length()-1) + "\t" +
                names.substring(1,names.length()-1);
    }

    private String getCreatedTime() {
        Instant now = Instant.now();
        Instant then = Instant.ofEpochSecond(created);

        Duration time = Duration.between(then, now);


        String res = "";

        if(time.toDays() > 0) {
            res += time.toDays() + " days";
        }

        if(time.toHours() > 0) {
            res += time.toHours() + " hours";
        }
        else {
            res = time.toMinutes() + " minutes";
        }

        if(time.getSeconds() < 60) {
            res = time.getSeconds() + " seconds";
        }

        return res + " ago";
    }
}

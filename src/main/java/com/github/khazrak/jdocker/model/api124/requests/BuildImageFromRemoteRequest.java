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
package com.github.khazrak.jdocker.model.api124.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.khazrak.jdocker.model.api124.AuthConfig;
import com.github.khazrak.jdocker.utils.DockerImageName;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
public class BuildImageFromRemoteRequest {

    private static final Logger logger = LoggerFactory.getLogger(BuildImageFromRemoteRequest.class);

    @Singular
    private Map<String, AuthConfig> authConfigs;

    private String dockerFilePath;

    private String remoteUrl;

    private DockerImageName tag;

    /**
     *  Suppress verbose build output.
     */
    private boolean quiet;

    /**
     * Do not use the cache when building the image.
     */
    private boolean nocache;

    /**
     * Attempt to pull the image even if an older image exists locally
     */
    private boolean pull;

    /**
     * Remove intermediate containers after a successful build (default behavior).
     */
    private boolean removeIntermediateContainers;

    /**
     * Always remove intermediate containers (includes removeIntermediateContainers)
     */
    private boolean forceRm;

    /**
     * Set memory limit for build
     */
    private long memory;

    /**
     *  Total memory (memory + swap), -1 to enable unlimited swap
     */
    private long memswap;

    /**
     * CPU shares (relative weight)
     */
    private int cpushares;

    /**
     * CPUs in which to allow execution (e.g., 0-3, 0,1)
     */
    private String cpusetcpus;

    /**
     * The length of a CPU period in microseconds
     */
    private long cpuperiod;

    /**
     * Microseconds of CPU time that the container can get in a CPU period
     */
    private long cpuquota;

    /**
     * Size of /dev/shm in bytes. The size must be greater than 0. If omitted the system uses 64MB
     */
    private long shmsize;

    @Singular
    private Map<String, String> buildargs;

    @Singular
    private Map<String, String> labels;


    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();

        if(remoteUrl == null) {
            throw new NullPointerException("remoteUrl is null in BuildImageFromRemoteRequest");
        }

        queries.put("remote", remoteUrl);

        if(dockerFilePath != null) {
            queries.put("dockerfile", dockerFilePath);
        }
        if(tag != null) {
            queries.put("t", tag.toString());
        }
        if(quiet) {
            queries.put("q", Boolean.toString(true));
        }
        if(nocache) {
            queries.put("nocache", Boolean.toString(true));
        }
        if(pull) {
            queries.put("pull", Boolean.toString(true));
        }
        if(removeIntermediateContainers) {
            queries.put("rm", Boolean.toString(true));
        }
        if(forceRm) {
            queries.put("forcerm", Boolean.toString(true));
        }
        if(memory > 0) {
            queries.put("memory", Long.toString(memory));
        }
        if(memswap > 0 || memswap == -1) {
            queries.put("memswap", Long.toString(memswap));
        }
        if(cpushares > 0) {
            queries.put("cpushares", Integer.toString(cpushares));
        }
        if(cpusetcpus != null) {
            queries.put("cpusetcpus", cpusetcpus);
        }
        if(cpuperiod > 0) {
            queries.put("cpuperiod", Long.toString(cpuperiod));
        }
        if(cpuquota > 0) {
            queries.put("cpuquota", Long.toString(cpuquota));
        }
        if(shmsize > 0) {
            queries.put("shmsize", Long.toString(shmsize));
        }

        ObjectMapper mapper = new ObjectMapper();

        if(buildargs.keySet().size() > 0) {
            try {
                String json = mapper.writeValueAsString(buildargs);
                queries.put("buildargs", json);
            } catch (JsonProcessingException e) {
                logger.error("Exception during json serialization of buildargs");
            }
        }

        if(labels.keySet().size() > 0) {
            try {
                String json = mapper.writeValueAsString(labels);
                queries.put("labels", json);
            } catch (JsonProcessingException e) {
                logger.error("Exception during json serialization of labels");
            }
        }

        return queries;
    }

}

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
package se.codeslasher.docker.docker_api_1_24.image;

import org.junit.Test;
import se.codeslasher.docker.utils.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

public class DockerImageNameTest {

    private static final String SIMPLE = "mongo";
    private static final String VERSION = "mongo:3.2";
    private static final String LATEST = "mongo:latest";
    private static final String REPO = "test/mongo";
    private static final String REGISTRY = "localhost.com/mongo";
    private static final String REGISTRY2 = "localhost:5000/mongo";
    private static final String FULL = "my.registry.com/myrepo/mongo:3.1";


    @Test
    public void simple() {
        DockerImageName mongo = new DockerImageName(SIMPLE);

        assertThat(mongo.toString()).isEqualTo("mongo:latest");
    }

    @Test
    public void version() {
        DockerImageName mongo = new DockerImageName(VERSION);

        assertThat(mongo.toString()).isEqualTo("mongo:3.2");
    }

    @Test
    public void latest() {
        DockerImageName mongo = new DockerImageName(LATEST);

        assertThat(mongo.toString()).isEqualTo("mongo:latest");
    }

    @Test
    public void repo() {
        DockerImageName mongo = new DockerImageName(REPO);

        assertThat(mongo.getImageRepo()).isEqualTo("test");
        assertThat(mongo.getImageName()).isEqualTo("mongo");
        assertThat(mongo.getTag()).isEqualTo("latest");
        assertThat(mongo.toString()).isEqualTo("test/mongo:latest");
    }

    @Test
    public void registry() {
        DockerImageName mongo = new DockerImageName(REGISTRY);
        assertThat(mongo.getImageRegistry()).isEqualTo("localhost.com");
        assertThat(mongo.getImageName()).isEqualTo("mongo");
        assertThat(mongo.getTag()).isEqualTo("latest");
        assertThat(mongo.toString()).isEqualTo("localhost.com/mongo:latest");
    }

    @Test
    public void registry2() {
        DockerImageName mongo = new DockerImageName(REGISTRY2);
        assertThat(mongo.getImageRegistry()).isEqualTo("localhost:5000");
        assertThat(mongo.getImageName()).isEqualTo("mongo");
        assertThat(mongo.getTag()).isEqualTo("latest");
        assertThat(mongo.toString()).isEqualTo("localhost:5000/mongo:latest");
    }

    @Test
    public void full() {
        DockerImageName mongo = new DockerImageName(FULL);
        assertThat(mongo.getImageRegistry()).isEqualTo("my.registry.com");
        assertThat(mongo.getImageRepo()).isEqualTo("myrepo");
        assertThat(mongo.getImageName()).isEqualTo("mongo");
        assertThat(mongo.getTag()).isEqualTo("3.1");
        assertThat(mongo.toString()).isEqualTo("my.registry.com/myrepo/mongo:3.1");
    }

}

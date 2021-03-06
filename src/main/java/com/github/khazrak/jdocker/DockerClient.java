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
package com.github.khazrak.jdocker;

import com.github.khazrak.jdocker.handlers.DockerLogsLineReader;
import com.github.khazrak.jdocker.model.api124.*;
import com.github.khazrak.jdocker.model.api124.parameters.*;
import com.github.khazrak.jdocker.model.api124.requests.*;
import com.github.khazrak.jdocker.utils.DockerImageName;
import com.github.khazrak.jdocker.utils.RequestStreamBody;

import java.io.InputStream;
import java.util.List;

public interface DockerClient {

    void close();

    String ping();

    DockerVersion version();

    SystemInfo info();

    String createContainer(ContainerCreationRequest spec);

    void start(String id);

    void stop(String id);

    void stop(String id, int secondsUntilKill);

    void remove(String id);

    void remove(String id, boolean forceRemove, boolean removeVolume);

    void kill(String id);

    void kill(String id, String signal);

    List<Container> listContainers();

    List<Container> listContainers(ListContainerParams listRequest);

    DockerContainerInspect inspectContainer(String id, boolean size);

    ContainerProcesses top(String id);

    ContainerProcesses top(String id, String arg);

    List<ContainerFileSystemChange> containerFileSystemChanges(String id);

    ContainerStats stats(String id);

    InputStream statsStream(String id);

    void resizeTty(String id, int width, int height);

    List<String> logs(String id, DockerLogsParameters params);

    InputStream logsRawStream(String id, DockerLogsParameters params);

    InputStream logsStream(String id, DockerLogsParameters params);

    DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params);

    InputStream pullImage(DockerImageName image);

    InputStream pullImage(DockerImageName image, AuthConfig authConfig);

    InputStream pullImage(DockerImageName image, String token);

    void restart(String id);

    void restart(String id, int wait);

    Warnings update(String id, ContainerUpdateRequest updateConfig);

    void rename(String originalName, String newName);

    void pause(String id);

    void unpause(String id);

    List<ImageInfo> listImages(boolean all);

    List<ImageInfo> listImages(ListImagesParams params);

    String createNetwork(NetworkCreateRequest request);

    List<Network> listNetworks();

    List<Network> listNetworks(NetworkListParams params);

    void connectContainerToNetwork(NetworkConnectRequest request);

    void disconnectContainerFromNetwork(String containerName, String networkName, boolean force);

    Network inspectNetwork(String id);

    void removeNetwork(String id);

    List<Volume> listVolumes();

    List<Volume> listVolumes(ListVolumeParams params);

    Volume createVolume(VolumeCreateRequest request);

    Volume inspectVolume(String id);

    void removeVolume(String id);

    Image inspectImage(DockerImageName imageName);

    ExecInfo inspectExec(String id);

    String createExec(String containerId, ExecCreateRequest request);

    void resizeExec(String id, int width, int height);

    void startExec(String id, boolean tty);

    InputStream startExec(String id);

    void tagImage(DockerImageName original, DockerImageName newName);

    InputStream pushImage(DockerImageName imageToPush, AuthConfig authConfig);

    InputStream pushImage(DockerImageName imageToPush, String identitytoken);

    String removeImage(DockerImageName name);

    String removeImage(DockerImageName name, boolean force, boolean noprune);

    List<ImageSearchInfo> searchImage(String term);

    List<ImageHistoryInfo> historyOfImage(DockerImageName name);

    InputStream buildImageFromRemote(BuildImageFromRemoteRequest request);

    InputStream buildImageFromArchive(BuildImageFromArchiveRequest request);

    AuthTestResponse auth(AuthTestRequest request);

    void waitForContainerStop(String id);

    String commitContainer(ContainerCommitRequest containerCommitRequest);

    FileSystemInfo fileSystemInfo(String id, String path);

    InputStream fileSystemArchiveDownload(String id, String path);

    void fileSystemArchiveUpload(String id, String pathToTar, RequestStreamBody pathInContainer);

    InputStream getImageTar(DockerImageName repositoryName);

    String importImageTar(InputStream input, boolean quiet);

    String ps(boolean all);
}

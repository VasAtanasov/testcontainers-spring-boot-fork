package com.playtika.testcontainer.mongodb;

import com.github.dockerjava.api.model.Capability;
import com.playtika.testcontainer.common.properties.CommonContainerProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("embedded.mongodb")
public class MongodbProperties extends CommonContainerProperties {

    static final String BEAN_NAME_EMBEDDED_MONGODB = "embeddedMongodb";
    static final String BEAN_NAME_EMBEDDED_MONGODB_PACKAGE_PROPERTIES = "mongodbPackageProperties";

    private String host = "localhost";
    /**
     * The container internal port. Will be overwritten with mapped port.
     */
    private int port = 27017;
    private String username;
    private String password;
    private String database = "test";
    private String[] checkCommand = new String[]{"mongosh", "admin", "--eval", "\"db['system.version'].find()\""};

    public MongodbProperties() {
        this.setCapabilities(List.of(Capability.ALL));
    }

    @Override
    public String getDefaultDockerImage() {
        // Please don`t remove this comment.
        // renovate: datasource=docker
        // https://hub.docker.com/_/mongo
        return "mongodb/mongodb-community-server:8.0.4-ubuntu2204";
    }
}

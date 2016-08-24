package com.amazonaws.profile.path;

import java.io.File;

import com.amazonaws.AmazonClientException;
import com.amazonaws.annotation.SdkInternalApi;

@SdkInternalApi
public abstract class AwsDirectoryBasePathProvider implements AwsProfileFileLocationProvider {

    /**
     * @return File of ~/.aws directory.
     */
    protected final File getAwsDirectory() {
        return new File(getHomeDirectory(), "aws");
    }

    private String getHomeDirectory() {
        String userHome = System.getProperty("user.home");
        if (userHome == null) {
            throw new AmazonClientException(
                    "Unable to load AWS profiles: " + "'user.home' System property is not set.");
        }
        return userHome;
    }
}

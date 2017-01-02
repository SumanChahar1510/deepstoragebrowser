package com.spectralogic.dsbrowser.gui.components.metadata;
import com.google.common.base.Joiner;
import com.spectralogic.ds3client.helpers.MetadataReceivedListener;
import com.spectralogic.ds3client.networking.Metadata;

import java.util.List;

/**
 * Created by linchpin on 7/10/16.
 */
public class MetadataRecievedListenerImpl implements MetadataReceivedListener {

    private String localFilePath = null;
    public MetadataRecievedListenerImpl(String localFilePath)
    {
        this.localFilePath = localFilePath;
    }
    
    @Override
    public void metadataReceived(String filename, Metadata metadata) {
        restoreMetaData(filename,metadata);
    }

    /**
     *  Restore the metadata to local file
    * @param objectName  name of the file to be restored
    * @param metadata  metadata which needs to be set on local file
    *
    */
    private  void restoreMetaData(final String objectName, final Metadata metadata) {
        final StringBuilder builder = new StringBuilder();
        final Joiner joiner = Joiner.on(", ");
        builder.append("Metadata for object ").append(objectName).append(": ");
        for (final String metadataKey : metadata.keys()) {
            final List<String> values = metadata.get(metadataKey);
            builder.append("<Key: ")
                    .append(metadataKey)
                    .append(" Values: ")
                    .append(joiner.join(values))
                    .append("> ");
        }

        System.out.println(builder.toString());
    }
}

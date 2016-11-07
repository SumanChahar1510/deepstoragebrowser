package com.spectralogic.dsbrowser.gui.components.metadata;

import com.google.common.collect.ImmutableMap;
import com.spectralogic.ds3client.helpers.Ds3ClientHelpers;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of MetaDataAcess Interface
 * Used to store meta data on Server
 */
public class MetaDataAccessImpl implements Ds3ClientHelpers.MetadataAccess {

    private ImmutableMap<String, Path> fileMapper = null;

    public MetaDataAccessImpl(ImmutableMap<String, Path> fileMapper)
    {
        this.fileMapper = fileMapper;
    }

    @Override
    public Map<String, String> getMetadataValue(String filename)
    {
        Map<String, String> metadata = new HashMap<>();
        try {
            Path file = fileMapper.get(filename);
            metadata = storeMetaData(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return metadata;
    }

    /*
    * to store the meta data on server
    *
    * */
    private Map<String,String> storeMetaData(Path file)
    {
        final Map<String, String> metadata = new HashMap<>();
        try {
            FileSystem store = file.getFileSystem();
            System.out.println(store.getFileStores().iterator().next().name());
            Set<String> sets = store.supportedFileAttributeViews();
            for (String set : sets) {
                switch (set) {
                    case "basic":
                        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                          metadata.put("x-amz-meta-ds3-creationTime"   , String.valueOf(attr.creationTime()));
                          metadata.put("x-amz-meta-ds3-accessTime"   , String.valueOf(attr.lastAccessTime().toMillis()));
                          metadata.put("x-amz-meta-ds3-lastModifiedTime"   , String.valueOf(attr.lastModifiedTime().toMillis()));
                        metadata.put("x-amz-meta-ds3-isDirectory"   , String.valueOf(attr.isDirectory()));
                        metadata.put("x-amz-meta-ds3-isOther"   , String.valueOf(attr.isOther()));
                        metadata.put("x-amz-meta-ds3-isRegularFile"   , String.valueOf(attr.isRegularFile()));
                        metadata.put("x-amz-meta-ds3-isSymbolicLink"   , String.valueOf(attr.isSymbolicLink()));
                        metadata.put("x-amz-meta-ds3-size"   , String.valueOf(attr.size()));
                        String ext = FilenameUtils.getExtension(file.getParent()+"/"+file.getFileName());
                        metadata.put("x-amz-meta-ds3-fileFormat", ext);
                        break;
                        
                    case "owner":

                        break;

                    case "user":
                        break;

                    case "unix":
                        break;

                    case "posix":
                        PosixFileAttributes attr1 = Files.readAttributes(file, PosixFileAttributes.class);
                        metadata.put("x-amz-meta-ds3-owner"   , attr1.owner().getName());
                        metadata.put("x-amz-meta-ds3-groupName"   , attr1.group().getName());
                        //metadata.put("Permisions: "   , PosixFilePermissions.toString(attr1.permissions()));
                        break;
                }
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return metadata;
    }
}

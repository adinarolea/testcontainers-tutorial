package com.tutorials.ar.localstack.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tutorials.ar.localstack.data.ResourceDetails;
import com.tutorials.ar.localstack.data.UploadResourceRequest;
import com.tutorials.ar.localstack.exception.BucketResourceException;

import java.util.List;

public interface ResourceService {

    <T> T getResource(String path, TypeReference<T> type) throws BucketResourceException;

    <T> T getResource(String path, Class<T> type) throws BucketResourceException;

    <T> List<ResourceDetails<T>> getResourcesFromDir(String bucket, String dir, TypeReference<T> type) throws BucketResourceException;

    void upload(UploadResourceRequest uploadResourceRequest) throws BucketResourceException;

}

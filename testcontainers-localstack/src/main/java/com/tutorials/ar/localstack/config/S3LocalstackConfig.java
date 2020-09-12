package com.tutorials.ar.localstack.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3LocalstackConfig {

    public AmazonS3 amazonS3(String awsRegion,
                             String localstackS3Url) {
        return AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(new ClientConfiguration()
                        .withConnectionTimeout(40000))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("mock", "mock")))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(localstackS3Url, awsRegion))
                .build();
    }
}
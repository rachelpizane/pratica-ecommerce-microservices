package edu.rachelpizane.icompras.faturamento.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class MinioProps {

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}

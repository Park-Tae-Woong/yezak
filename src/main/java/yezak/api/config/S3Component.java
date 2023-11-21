package yezak.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter

public class S3Component {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
}

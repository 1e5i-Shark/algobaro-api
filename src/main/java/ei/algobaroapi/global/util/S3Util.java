package ei.algobaroapi.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import ei.algobaroapi.global.exception.S3Exception;
import ei.algobaroapi.global.exception.common.GlobalErrorCode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Util {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String upload(MultipartFile image) {
        if (image.isEmpty() || Objects.isNull(image.getOriginalFilename())) {
            throw S3Exception.of(GlobalErrorCode.EMPTY_FILE_EXCEPTION);
        }
        return this.uploadImage(image);
    }

    private String uploadImage(MultipartFile image) {
        this.validateFileExtension(image.getOriginalFilename());
        try {
            return this.uploadImageToS3(image);
        } catch (IOException e) {
            throw S3Exception.of(GlobalErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD);
        }
    }

    private void validateFileExtension(String originalFileName) {
        // 파일 확장자 검증
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw S3Exception.of(GlobalErrorCode.INVALID_FILE_EXTENSION);
        }

        String extension = originalFileName.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png");

        if (!allowedExtentionList.contains(extension)) {
            throw S3Exception.of(GlobalErrorCode.INVALID_FILE_EXTENSION);
        }
    }

    private String uploadImageToS3(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename(); //원본 파일 명
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); //확장자 명

        String s3FileName =
                UUID.randomUUID().toString().substring(0, 10) + "-" + originalFilename; //변경된 파일 명

        InputStream is = image.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + extension);
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest); // put image to S3
        } catch (Exception e) {
            throw S3Exception.of(GlobalErrorCode.PUT_OBJECT_EXCEPTION);
        } finally {
            byteArrayInputStream.close();
            is.close();
        }

        return amazonS3.getUrl(bucketName, s3FileName).toString();
    }
}

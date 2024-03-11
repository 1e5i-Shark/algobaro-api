package ei.algobaroapi.global.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode {

    ALREADY_DELETED_ENTITY("E00001", "이미 삭제된 엔티티 입니다."),
    ACCESS_DENIED("E00201", "접근 권한이 없습니다."),
    INVALID_INPUT_VALUE("E99001", null),
    INTERNAL_SERVER_ERROR("E99999", "서버 내부 오류가 발생했습니다."),
    EMPTY_FILE_EXCEPTION("E06401", "파일이 비어있습니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD("E06402", "이미지 업로드 중 오류가 발생했습니다."),
    NO_FILE_EXTENSION("E06403", "올바른 파일 확장자가 없습니다."),
    INVALID_FILE_EXTENSION("E06404", "허용되지 않는 파일 확장자 입니다."),
    PUT_OBJECT_EXCEPTION("E06405", "S3에 파일을 업로드하는 중 오류가 발생했습니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE("E06406", "이미지 삭제 중 오류가 발생했습니다.");

    private final String errorCode;
    private final String errorMessage;
}

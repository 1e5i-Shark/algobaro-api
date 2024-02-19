package ei.algobaroapi.global.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {

    public <T, E> E requestPostWithBasicAuthorization(
            String url,
            T body,
            Class<E> responseType
    ) {
        HttpHeaders httpHeaders = generateHttpHeaders();
        HttpEntity<T> httpEntity = createHttpEntity(httpHeaders, body);

        ResponseEntity<E> response = executePostRequest(
                url,
                responseType,
                httpEntity
        );

        return response.getBody();
    }

    private <T, E> ResponseEntity<E> executePostRequest(
            String url,
            Class<E> responseType,
            HttpEntity<T> httpEntity
    ) {
        return new RestTemplate().exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                responseType
        );
    }

    private HttpHeaders generateHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;
    }

    private <T> HttpEntity<T> createHttpEntity(HttpHeaders httpHeaders, T body) {
        return new HttpEntity<>(body, httpHeaders);
    }
}

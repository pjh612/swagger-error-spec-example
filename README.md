```yaml
springdoc:
    info:
        title: ${spring.application.name}
        description: ${spring.application.name} docs for ${spring.profiles.active}
        contact:
            name: Jinhyung Park
            email: pjh_jn@naver.com
            url: https://github.com/pjh612/swagger-error-spec-example
```
![image](https://github.com/user-attachments/assets/3cc9f2ba-ce81-465e-a9de-06bb8a51a8be)

```JAVA
@RestController
public class SampleController {

    @ApiErrorCodeExample(
            examples = {
                    @ExceptionCodeExample(title = "비밀번호 틀릴 때", codes = {"C-001"}),
                    @ExceptionCodeExample(title = "입력한 아이디가 존재하지 않을 때", codes = {"C-002"})}
    )
    @GetMapping("/sample")
    public String sample(@RequestBody SampleRequest request) {
        return request.toString();
    }
}
```

![image](https://github.com/user-attachments/assets/072f7215-46b7-47e7-90cb-825faad5f43a)


아래와 같이 ExceptionHandler를 작성해 응답 형식 통일
```JAVA
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ExceptionCodeConverter exceptionCodeConverter;

    public GlobalExceptionHandler(ExceptionCodeConverter exceptionCodeConverter) {
        this.exceptionCodeConverter = exceptionCodeConverter;
    }

    @ExceptionHandler(BusinessException.class)
    public ExceptionResponse handleBusinessException(BusinessException e) {
        StatusException[] errors = exceptionCodeConverter.toError(e.getExceptionContent()
                .stream()
                .map(ExceptionContent::getCode)
                .toArray(String[]::new));

        return exceptionCodeConverter.toExceptionResponse(errors);
    }
}
```

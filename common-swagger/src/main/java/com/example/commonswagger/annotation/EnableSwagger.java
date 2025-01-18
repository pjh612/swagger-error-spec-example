package com.example.commonswagger.annotation;


import com.example.commonswagger.DefaultSwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import({DefaultSwaggerConfig.class})
public @interface EnableSwagger {
}

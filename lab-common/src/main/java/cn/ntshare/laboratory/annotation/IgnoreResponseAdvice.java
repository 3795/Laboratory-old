package cn.ntshare.laboratory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于忽略VO层的包装
 */
@Target({ElementType.TYPE, ElementType.METHOD})     // 用于类的声明上和方法上
@Retention(RetentionPolicy.RUNTIME)     // 运行时
public @interface IgnoreResponseAdvice {
}

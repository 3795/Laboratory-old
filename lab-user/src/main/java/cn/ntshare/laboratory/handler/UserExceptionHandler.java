package cn.ntshare.laboratory.handler;

import cn.ntshare.laboratory.enums.ServerResponseEnum;
import cn.ntshare.laboratory.vo.ServerResponseVO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServerResponseVO UnAuthorizedExceptionHandler(UnauthorizedException e) {
        return ServerResponseVO.error(ServerResponseEnum.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponseVO AuthenticationExceptionHandler(AuthenticationException e) {
        return ServerResponseVO.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
    }
}

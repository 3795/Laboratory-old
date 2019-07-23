package cn.ntshare.laboratory.handler;

import cn.ntshare.laboratory.exception.ServerException;
import cn.ntshare.laboratory.vo.ServerResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponseVO serverExceptionHandler(ServerException e) {
        return ServerResponseVO.error(e.getCode(), e.getMessage());
    }
}

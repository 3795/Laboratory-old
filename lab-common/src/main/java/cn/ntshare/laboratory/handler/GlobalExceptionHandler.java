package cn.ntshare.laboratory.handler;

import cn.ntshare.laboratory.exception.AdException;
import cn.ntshare.laboratory.exception.ServerException;
import cn.ntshare.laboratory.vo.CommonResponse;
import cn.ntshare.laboratory.vo.ServerResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponseVO serverExceptionHandler(ServerException e) {
        return ServerResponseVO.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(AdException ex) {
        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(ex.getMessage());
        return response;
    }
}

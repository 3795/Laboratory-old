package cn.ntshare.laboratory.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 7435664802720625226L;
    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse() {
    }

    public CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

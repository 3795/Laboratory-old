package cn.ntshare.laboratory.exception;

import cn.ntshare.laboratory.enums.ServerResponseEnum;

public class ServerException extends RuntimeException {

    private static final long serialVersionUID = -5844369481292296817L;

    private Integer code;

    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServerException(ServerResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

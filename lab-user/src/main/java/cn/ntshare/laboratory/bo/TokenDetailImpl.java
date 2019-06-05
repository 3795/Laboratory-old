package cn.ntshare.laboratory.bo;

public class TokenDetailImpl implements TokenDetail {

    private String username;

    public TokenDetailImpl(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}

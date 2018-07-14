package az.kerimov.mars.pojo;


import az.kerimov.mars.entity.MException;

public class MarsException extends java.lang.Exception {
    private Integer code;
    private String stringMessage;

    public  MarsException (MException ex){
        this.code = ex.getId();
        this.stringMessage = ex.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStringMessage() {
        return stringMessage;
    }

    public void setMessage(String stringMessage) {
        this.stringMessage = stringMessage;
    }
}

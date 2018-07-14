package az.kerimov.mars.pojo;

public class Error {
    private Integer errorCode;
    private String errorMessage;

    public Error(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Error(MarsException e){
        this.errorCode = e.getCode();
        this.errorMessage = e.getStringMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

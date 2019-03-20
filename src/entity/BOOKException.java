package entity;

public class BOOKException extends Exception {
    private String erroCode;
    public BOOKException(String str){
        this.erroCode=str;
    }
}

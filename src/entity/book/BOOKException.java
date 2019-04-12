package entity.book;

public class BOOKException extends Exception {
    private String erroCode;
    public BOOKException(String str){
        this.erroCode=str;
    }

    @Override
    public String toString() {
        return erroCode;
    }
}

package tool;

import java.util.Objects;

public class Isbn {
    private String isbn;
    public static boolean checkIsbn(String isbn){
        isbn=isbn.replace(" ", "");
        int len=isbn.length();
        if (len>17)
            return false;
        StringBuffer isbnBuf=new StringBuffer(len);
        for(int i=0;i<len;i++){
            if(Character.isDigit(isbn.charAt(i))||isbn.charAt(i)=='X'){
                isbnBuf.append(isbn.charAt(i));
            }
            else{
                if(isbn.charAt(i)!='-')
                    return false;
            }
        }
        isbn=isbnBuf.toString();
        if(isbn.length()==10){
            for(int i=0;i<9;i++){
                if(isbn.charAt(i)=='X')
                    return false;
            }
            int sum=0;
            for(int i=0;i<9;i++){
                sum+=(10-i)*(isbn.charAt(i)-'0');
            }
            if(isbn.charAt(9)=='X')
                sum+=10;
            else
                sum+=isbn.charAt(9)-'0';
            if(sum%11==0)
            return true;
        }
        if(isbn.length()==13){
            if(isbn.regionMatches(0, "978", 0, 3)||isbn.regionMatches(0, "979", 0, 3)){
                int sum=0;
                for(int i=0;i<13;i++){
                    if(!Character.isDigit(isbn.charAt(i)))
                        return false;
                    sum+=Math.pow(3, 1-((i+1)%2))*(isbn.charAt(i)-'0');
                }
                if(sum%10==0)
                    return true;
            }
        }
        return false;
    }

    public Isbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        StringBuffer buf=new StringBuffer(this.isbn.trim());
        int i=0;
        while(i<buf.length()){
            if(buf.charAt(i)=='-')
                buf.deleteCharAt(i);
            else i++;
        }
        return buf.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Isbn isbn1 = (Isbn) o;
        return Objects.equals(isbn, isbn1.isbn)||Objects.equals(isbn, o);//使得Isbn对象可以和String进行比较
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

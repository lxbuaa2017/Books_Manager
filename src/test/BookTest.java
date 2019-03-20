package test;

import entity.BOOK;
import tool.Isbn;

import java.util.Scanner;

public class BookTest {
    public static void print(String str){
        System.out.println(str);
    }
    private static void testISBN(BOOK book){
        if(Isbn.checkIsbn(book.getISBN()))
            print("ISBN有效.");
        else
            print("ISBN无效.");
    }
    private static void testPrint(BOOK book){
        book.print();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String title="Java编程思想";
        String[] author={"A君","D君"};
        String price="-9";
        int inventory=0;
        int totalNum=1;
        String isbn;
        while(true){
            isbn=sc.nextLine();
            BOOK book=new BOOK(isbn, title, author, price);
            testISBN(book);
            testPrint(book);
            Isbn newIsbn=new Isbn(book.getISBN());
            print(newIsbn.toString());
        }
    }
}

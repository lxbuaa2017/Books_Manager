package test;

import dao.Library;
import entity.BOOK;
import tool.Isbn;

public class LibraryTest {
    public static void main(String[] args){
        String[] author=new String[]{"A","B"};
        BOOK book1=new BOOK("978711--1275206", "说明书", author , "15.3");
        BOOK book2=new BOOK("9787533158-972", "数学分析", author , "188.3456");
        BOOK book3=new BOOK("9781107626--133", "测试书", author , "198.456");
        Library.addBook(book1);Library.addBook(book2);
        System.out.println(Library.getBookByIsbn(new Isbn("9787111275206")));
        System.out.println(Library.getBookByKeyword("数学"));
        if( Library.updateBook("9787533158972", book1))
            System.out.println("update成功");
        else
            System.out.println("update失败");//重isbn测试，应显示失败
        if( Library.updateBook("9787111275206", book3))
            System.out.println("update成功");
        else
            System.out.println("update失败");//book1被book3替换，应显示成功
        System.out.println(Library.getBookByKeyword("书"));//应只显示book3信息
    }
}

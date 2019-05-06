package main;

import daoImpl.UserDatabase;
import entity.book.BOOK;
import entity.book.BOOKException;
import entity.user.Manager;
import entity.user.RegisteredUser;
import entity.user.SuperManager;
import main.ui.BeginConsole;
import main.ui.RegisteredUserConsole;
import tool.Isbn;

import java.util.NoSuchElementException;

import static daoImpl.Library.library;
import static daoImpl.Library.libraryMap;

public class Main {

    public static void main(String[] args){
        {
            String[] aur={"author"};
            try{
                BOOK[] book=new BOOK[14];
                book[0]=new BOOK("978-71210-56161", "a123", aur, "200");
                book[1]=new BOOK("978-7115431-615", "a234", aur, "201");
                book[2]=new BOOK("978 7-040-40-6641", "a345", aur, "20.33");
                book[3]=new BOOK("9787302-34-  0010", "a456", aur, "20.366544");
                book[4]=new BOOK("9787040473261", "a567", aur, "20.366544");
                book[5]=new BOOK("9787302263142", "a678", aur, "20.366544");
                book[6]=new BOOK("9787505605114", "a456", aur, "20.366544");
                book[7]=new BOOK("9787500788652", "a123", aur, "200");
                book[8]=new BOOK("9787500788706", "a234", aur, "201");
                book[9]=new BOOK("9787505605961", "a345", aur, "20.33");
                book[10]=new BOOK("9787500788683", "a456", aur, "20.366544");
                book[11]=new BOOK("9787500788690", "a567", aur, "20.366544");
                book[12]=new BOOK("9787505605701", "a678", aur, "20.366544");
                book[13]=new BOOK("9787500788805", "a456", aur, "20.366544");

                SuperManager superManager=new SuperManager("super", "123");
                Manager manager=new Manager("ma", "123");
                RegisteredUser registeredUser=new RegisteredUser("user", "123");
                UserDatabase.userHashMap.put("super", superManager);
                UserDatabase.userHashMap.put("ma", manager);
                UserDatabase.userHashMap.put("user", registeredUser);
                for(BOOK each:book){
                    library.add(each);
                    libraryMap.put(new Isbn(each.getISBN()), each);
                }
            }
            catch (BOOKException e){
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
        for(BOOK each:library)
            System.out.println(each.toString());//输出自己的原始书库看看，方便测试
        try {
            new BeginConsole().run();
        }
       catch (NoSuchElementException e){
            System.out.println("输入流意外关闭，程序退出。");
       }
    }
}

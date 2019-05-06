package main.ui;

import daoImpl.Library;
import entity.book.BOOK;
import entity.book.BOOKException;
import entity.user.Manager;

import java.util.Scanner;
import java.util.Vector;


public class ManagerConsole{
    Manager manager;
    static Scanner sc=new Scanner(System.in);
    public void run(Manager manager) {
        this.manager=manager;
        mainMenu();
    }
    public void mainMenu(){
        System.out.println("====请选择操作====\n" +
                "1.检索书籍\n" +
                "2.添加书籍；\n" +
                "3.修改书籍；\n" +
                "4.检索用户；\n" +
                "5.注销");
        String i=sc.nextLine();
        switch (i){
            case "1":
                queryBook();mainMenu();
            case "2":
                addBook();mainMenu();
            case "3":
                updateBook();mainMenu();
            case "4":
                searchRegisteredUser();mainMenu();
            case "5":
                new BeginConsole().run();mainMenu();
            default:
                System.out.println("输入不合法，请重新输入。");
            mainMenu();
        }
    }
    public void queryBook(){
        String name;
        System.out.println("请输入关键词：");
        name=sc.nextLine();
        Vector<BOOK> books=manager.getBookByKeyword(name);
        for(BOOK each:books)
            System.out.println(each);
    }

    public void addBook(){
        System.out.println("请分别换行输入ISBN号，书名，作者（一位即可），价格");
        BOOK book;
        try {
            book=new BOOK(sc.nextLine(), sc.nextLine(), new String[]{sc.nextLine()}, sc.nextLine());
            manager.addBooks(book);
        }
        catch (BOOKException e){
            System.out.println("发生错误，请检查“"+e.toString()+"”"+"信息或格式是否正确");
        }
    }

    public void updateBook(){
        System.out.println("请分别换行输入ISBN号，书名，作者（一位即可），价格");
        BOOK book;
        try {
            String ISBN=sc.nextLine();
            book=new BOOK(ISBN, sc.nextLine(), new String[]{sc.nextLine()}, sc.nextLine());
            manager.updateBook(ISBN,book);
        }
        catch (BOOKException e){
            System.out.println("发生错误，请检查“"+e.toString()+"”"+"信息或格式是否正确");
        }
    }

    public void searchRegisteredUser(){
        System.out.println("请输入用户名：");
        String name=sc.nextLine();
        System.out.println(manager.searchRegisteredUser(name));
    }
}

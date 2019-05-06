package main.ui;

import daoImpl.Library;
import entity.book.BOOK;
import entity.user.RegisteredUser;
import tool.Isbn;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.System.exit;

public class RegisteredUserConsole{
    RegisteredUser registeredUser;
    static Scanner sc=new Scanner(System.in);
    public void run(RegisteredUser registeredUser){
        this.registeredUser=registeredUser;
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("=============主菜单=============\n" +
                "请输入以下数字选择功能：\n" +
                "1.查找书籍\n" +
             /*   "2.添加书籍信息\n" +
                "3.修改原有书籍信息\n" +*/
                "2.注销");
        String i=sc.nextLine();
        switch (i){
            case "1":
                RegisteredUserConsole.queryBook();
                mainMenu();
            /*case 2:
                RegisteredUserConsole.addBook();
                break;
            case 3:
                RegisteredUserConsole.updateBook();
                break;*/
            case "2":
                new BeginConsole().run();
                break;
            default:
                System.out.println("输入不合法，请重新输入。");
                mainMenu();
        }
    }
    public static void queryBook(){
        System.out.println("=============查询=============\n" +
                "请输入以下数字选择查询方式：\n" +
                "1.根据ISBN查找\n" +
                "2.根据书名关键词查找\n"+
                "3.返回主菜单");
        String i=sc.nextLine();//吃掉'\n'
        String str;
        switch (i) {
            case "1":
                System.out.println("请输入isbn：");
                str=sc.nextLine();
                if(!Isbn.checkIsbn(str)){
                    System.out.println("输入不合法，请重新输入。");
                }
                else {
                    BOOK book= Library.getBookByIsbn(new Isbn(str));
                    if(book==null)
                        System.out.println("查无此书。");
                    else
                        System.out.println(book.toString());
                }
                RegisteredUserConsole.queryBook();
                break;
            case "2":
                System.out.println("请输入书名关键词：");
                str =sc.nextLine();
                Vector<BOOK> books;
                if(str==""){
                    books=Library.library; //打印所有书籍
                }
                else {
                    books=Library.getBookByKeyword(str);
                }
                if(books.isEmpty())
                    System.out.println("没有相关书籍。");
                else{
                    books.sort(
                            new Comparator<BOOK>() {
                                @Override
                                public int compare(BOOK o1, BOOK o2) {
                                    return o1.getTitle().compareTo(o2.getTitle());
                                }
                            }
                    );
                    Vector<Vector<BOOK>> resultPage=new Vector<>();
                    int pageSize=(int)Math.ceil(books.size()/10.0);
                    for(int page=1;page<=pageSize;page++){
                        Vector<BOOK> res=new Vector<>();
                        for(int j=(page-1)*10;j<books.size()&&j<page*10;j++){
                            res.add(books.elementAt(j));
                        }
                        resultPage.add(res);
                    }
                    showResult(resultPage, pageSize);
                }
                 RegisteredUserConsole.queryBook();
                break;
            case "3":
                RegisteredUserConsole.mainMenu();
                break;
            default:
                System.out.println("输入不合法。");
                RegisteredUserConsole.queryBook();break;
        }
    }
    public static void pageUI(){
        System.out.println(
                "请输入以下数字进行选择：\n" +
                        "1.上一页\n" +
                        "2.下一页\n" +
                        "3.返回上一级\n" );
    }
    public static void showResult(Vector<Vector<BOOK>> resultPage,int size){
        int page=1;
        for(BOOK each:resultPage.elementAt(0))
            System.out.println(each.toString());
        System.out.println("第1页");
        pageUI();
        boolean returnFlag=false;
        while(!returnFlag){
            String in;
            switch (in=sc.nextLine()){
                case "1":
                    page--;
                    if(page<1){
                        page++;
                        System.out.println("已到第一页。");
                        pageUI();
                    }
                    else {
                        for(BOOK each:resultPage.elementAt(page-1))
                            System.out.println(each.toString());
                        System.out.println("第"+page+"页");
                        pageUI();
                    }
                    break;
                case "2":
                    page++;
                    if(page>size){
                        page--;
                        System.out.println("已到最后一页。");
                        pageUI();
                    }
                    else {
                        for(BOOK each:resultPage.elementAt(page-1))
                            System.out.println(each.toString());
                        System.out.println("第"+page+"页");
                        pageUI();
                    }
                    break;
                case "3":
                    returnFlag=true;
                    break;
                default:
                    System.out.println("输入不合法");
                    pageUI();break;
            }
        }
    }
    /*
    public static void addBook(){

    }
    public static void updateBook(){

    }*/
   /* public static void main(String[] args){
        RegisteredUserConsole.mainMenu();
    }*/
}

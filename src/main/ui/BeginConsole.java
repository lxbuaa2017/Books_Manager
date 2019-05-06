package main.ui;

import java.util.Scanner;

import static java.lang.System.exit;

public class BeginConsole{
    static Scanner sc=new Scanner(System.in);
    public void run(){
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("=============主菜单=============\n" +
                "请输入以下数字选择功能：\n" +
                "1.登陆\n" +
                "2.注册\n"+
                "3.退出程序\n");//注册得普通用户，登陆后得判断是什么用户，跳转到合适地方
        String i=sc.nextLine();
        switch (i){
            case "1":
                new LoginConsole().run();
                break;
            case "2":
                new RegisterConsole().run();
                break;
            case "3":
                sc.close();
                exit(0);
            default:
                System.out.println("输入不合法，请重新输入。");
                BeginConsole.mainMenu();
        }
    }
}

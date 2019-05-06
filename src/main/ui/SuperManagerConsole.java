package main.ui;

import entity.user.Manager;
import entity.user.SuperManager;

import java.io.Console;
import java.util.Scanner;

public class SuperManagerConsole{
    SuperManager superManager;
    static Scanner sc=new Scanner(System.in);
    public void run(SuperManager superManager) {
        this.superManager=superManager;
        mainMenu();
    }
    public void mainMenu() {
        System.out.println("====请选择操作====\n" +
                "1.管理普通管理员\n" +
                "2.普通管理员功能\n" +
                "3.注销");
        String i = sc.nextLine();
        switch (i) {
            case "1":
                crudManager();mainMenu();
            case "2":
                new ManagerConsole().run((Manager)superManager);mainMenu();
            case "3":
                new BeginConsole().run();mainMenu();
            default:
                System.out.println("输入不合法，请重新输入。");
                mainMenu();
        }
    }
    public void crudManager(){
        System.out.println("====请选择操作====\n" +
                "1.增加管理员\n" +
                "2.删除管理员\n" +
                "3.查看管理员\n" +
                "4.注销");
        String i = sc.nextLine();
        switch (i) {
            case "1":
                addManager();break;
            case "2":
                deleteManager();break;
            case "3":
                queryManager();break;
            default:
                System.out.println("输入不合法，请重新输入。");
                crudManager();
        }
    }
    public void addManager(){
        System.out.println("请分行输入账号、密码：");
        String name=sc.nextLine();
        String pwd=sc.nextLine();
        Manager manager=new Manager(name, pwd);
        superManager.addManager(manager);
    }
    public void deleteManager(){
        System.out.println("请输入账号：");
        String name=sc.nextLine();
        superManager.deleteManager(name);
    }

    public void queryManager(){
        superManager.queryManager();
    }
}

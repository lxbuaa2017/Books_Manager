package main.ui;

import daoImpl.UserDatabase;
import entity.user.RegisteredUser;

import java.util.Scanner;

public class RegisterConsole{
    static Scanner sc=new Scanner(System.in);
    public void run(){
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("=============请输入注册账号名（不要使用\"quit\"作为注册名）=============\n"+
               "键入quit可返回开始界面\n" );
        String name=sc.nextLine();
        if (name.equals("quit")){
            new BeginConsole().run();
            return;
        }
        if(UserDatabase.getUser(name)!=null){
            System.out.println("账号已存在！");
            new RegisterConsole().run();
        }
        System.out.println("=============请输入注册密码=============\n");
        String pwd=sc.nextLine();
        RegisteredUser registeredUser=new RegisteredUser(name, pwd);
        UserDatabase.addUser(registeredUser);
        //System.out.println(UserDatabase.userHashMap.values());
        new RegisteredUserConsole().run(registeredUser);//注册后自动登录
    }
}

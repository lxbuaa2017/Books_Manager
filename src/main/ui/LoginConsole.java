package main.ui;

import daoImpl.UserDatabase;
import entity.user.Manager;
import entity.user.RegisteredUser;
import entity.user.SuperManager;
import entity.user.User;

import java.util.Scanner;

public class LoginConsole {
    static Scanner sc=new Scanner(System.in);
    public void run(){
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("=============请输入账号名（键入quit返回开始界面）=============\n");
        String name=sc.nextLine();
        if (name.equals("quit")){
            new BeginConsole().run();
            return;
        }
        User user=UserDatabase.getUser(name);
        if(user==null){
            System.out.println("账号尚未注册！\n");
            new LoginConsole().run();
        }
        else {
            System.out.println("=============请输入密码=============\n");
            String pwd = sc.nextLine();
            if(!user.getPassword().equals(pwd)){
                System.out.println("密码错误！");
                new LoginConsole().run();
            }
            else {
                String className=user.getName();
                switch (className){
                    case "RegisteredUser":
                        new RegisteredUserConsole().run((RegisteredUser)user);break;
                    case "Manager":
                        new ManagerConsole().run((Manager) user);break;
                    case "SuperManager" :
                        new SuperManagerConsole().run((SuperManager)user);break;
                }
            }
        }
    }
}

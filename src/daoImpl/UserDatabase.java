package daoImpl;

import entity.user.Manager;
import entity.user.SuperManager;
import entity.user.User;

import java.util.HashMap;
import java.util.Vector;

public class UserDatabase {
    //增加新用户，如果传入对象为null或者用户已存在，则返回false，成功添加则返回true
    public static HashMap<String, User> userHashMap=new HashMap<>();
    public static boolean addUser(User user){
        if(user==null)
            return false;
        else {
            if(userHashMap.containsKey(user.getUserName()))
                return false;
            userHashMap.put(user.getUserName(), user);
            return true;
        }
    }
    //查找已有用户
    public static User getUser(String name){
        return userHashMap.get(name);
    }

    //添加管理员
    public static boolean addManager(Manager manager){
        String name=manager.getUserName();
        if(getUser(name)!=null)
            return false;
        else {
            userHashMap.put(name, manager);
            return true;
        }
    }

    //查找所有管理员(包括超管本身)
    public static Vector<Manager> queryManager(){
        Vector<Manager> managers=new Vector<>();
        for(User each:userHashMap.values()){
            if(each instanceof Manager)
                managers.add((Manager) each);
        }
        return managers;
    }

    //删除管理员
    public  static boolean deleteManager(String name){
        User user=getUser(name);
        if(user instanceof Manager&&!(user instanceof SuperManager)){
            userHashMap.remove(name);
            return true;
        }
        else
            return false;
    }
}

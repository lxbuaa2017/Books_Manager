package entity.user;

import daoService.user.AddManager;
import daoService.user.DeleteManager;
import daoImpl.UserDatabase;
import daoService.user.QueryManager;

import java.util.Vector;

public class SuperManager extends Manager implements QueryManager, DeleteManager, AddManager {
    public SuperManager(String name, String password) {
        super(name, password);
    }

    @Override
    public boolean deleteManager(String name) {
        return UserDatabase.deleteManager(name);
    }

    @Override
    public Vector<Manager> queryManager() {
        return UserDatabase.queryManager();
    }

    @Override
    public boolean addManager(Manager manager) {
        return UserDatabase.addManager(manager);
    }
    @Override
    public String getName() {
        return "SuperManager";
    }
}

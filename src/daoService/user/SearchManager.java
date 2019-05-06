package daoService.user;

import entity.user.Manager;

import java.util.Vector;

public interface SearchManager {
    Vector<Manager> serchManager(String name);
}

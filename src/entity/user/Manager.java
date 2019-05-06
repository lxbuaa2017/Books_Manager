package entity.user;

import daoService.user.DeleteRegisteredUser;
import daoService.user.SearchRegisteredUser;
import daoImpl.Library;
import daoImpl.UserDatabase;
import entity.book.BOOK;
import entity.book.BOOKException;
import daoService.book.*;
import tool.Isbn;

import java.util.Vector;

/*
对于管理员， 他不具有注册用户的借阅功能，但是可以对馆藏书籍进行管理（CRUD： 增、
删、改、查） ，也可以管理注册用户 。
*/
public class Manager extends User implements AddBooks, DeleteBooks, UpdateBooks, GetBookByIsbn, GetBookByKeyword,
        SearchRegisteredUser, DeleteRegisteredUser {
    public Manager(String name, String password) {
        super(name, password);
    }
    @Override
    public boolean addBooks(BOOK book) throws BOOKException{
        if(book==null)
            return false;
        else {
            Library.addBook(book);
            return true;
        }
    }

    @Override
    public boolean deleteBooks(String isbn) throws BOOKException {
        return Library.deleteBook(isbn);
    }

    @Override
    public boolean updateBook(String isbn, BOOK book) {
        return Library.updateBook(isbn, book);
    }

    @Override
    public BOOK getBookByIsbn(Isbn isbn) {
        return Library.getBookByIsbn(isbn);
    }

    @Override
    public Vector<BOOK> getBookByKeyword(String keyword) {
        return Library.getBookByKeyword(keyword);
    }

    @Override
    public boolean deleteRegisteredUser(RegisteredUser registeredUser) {
        return false;
    }

    @Override
    public RegisteredUser searchRegisteredUser(String name) {
        User user= UserDatabase.getUser(name);
        if(user instanceof RegisteredUser)
            return (RegisteredUser) user;
        else
            return null;
    }
    @Override
    public String getName() {
        return "Manager";
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

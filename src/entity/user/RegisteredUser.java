package entity.user;
/*
对于注册用户，可以检索馆藏书籍、借阅书籍、查看其借阅记录，归还书籍。用户借走
的书籍，必须要定期归还，否则会导致自己信用积分的扣除，当信用积分小于一定值时，他
就没办法再借书了。为了防止健忘或者忙碌的读者被误伤，系统会在书籍借阅期限到达前，
每天向读者发送消息提醒还书。 用户账号只能通过注册获得。
*/

import daoImpl.Library;
import entity.book.BOOK;
import daoService.book.BorrowBooks;
import daoService.book.SerchBookByKeyWord;

import java.util.Vector;

public class RegisteredUser extends User implements SerchBookByKeyWord, BorrowBooks {
    private Vector<BOOK> borrowedBooks;
    private int credit;
    public RegisteredUser(String name, String password) {
        super(name, password);
        this.borrowedBooks =new Vector<>();
        credit=10;//信用初始值为10
    }

    @Override
    public Vector<BOOK> serchBookByKeyWord(String str) {
        return Library.getBookByKeyword(str);
    }

    @Override//待后续实验实现
    public boolean borrowBooks(BOOK book) {
        return false;
    }
    @Override
    public String getName() {
        return "RegisteredUser";
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "borrowedBooks=" + borrowedBooks +
                ", credit=" + credit +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

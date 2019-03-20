package dao;

import entity.BOOK;
import tool.Isbn;
import tool.LibraryMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
public class Library {
    public static LibraryMap<Isbn,BOOK> libraryMap=new LibraryMap<>();//用于ISBN快速索引
    public static Vector<BOOK> library=new Vector<>();//用于关键词模糊查找
    public Library() {
    }
    public static BOOK getBookByIsbn(Isbn isbn){
            try {
                if(!Isbn.checkIsbn(isbn.toString()))
                throw new Exception();
            }
            catch (Exception e){
                return null;
            }
            return libraryMap.get(isbn);
    }

    public static Vector<BOOK> getBookByKeyword(String keyword){
        return libraryMap.get(keyword);     //已overload
    }

    public static void addBook(BOOK book){      //book的构造方法和setter方法已对数据合法作保证
        Isbn isbn=new Isbn(book.getISBN());
        if(!libraryMap.containsKey(isbn)){
            book.setTotalNum(1);book.setInventory(1);       //该书原来没有，故刚加入时总数和可借数都为1
            libraryMap.put(isbn, book);
            library.add(book);
        }
        else {
            BOOK storedBook=libraryMap.get(isbn);   //是引用，可以直接改
            storedBook.setInventory(storedBook.getInventory()+1);
            storedBook.setTotalNum(storedBook.getTotalNum()+1);
            int bookIndex=library.indexOf(storedBook);
            BOOK libBook=library.elementAt(bookIndex);
            libBook.setInventory(libBook.getInventory()+1);
            libBook.setTotalNum(libBook.getTotalNum()+1);
        }
    }
    public static boolean updateBook(String isbn,BOOK book){    //合法性已在BOOK类中得到保证
        try {
            if(!libraryMap.containsKey(new Isbn(isbn)))
                throw new Exception();
        }
        catch (Exception e){
           // System.out.println("书籍不存在");
            return false;
        }
        if(!isbn.equals(book.getISBN()))
            if(libraryMap.containsKey(new Isbn(book.getISBN()))){
             //   System.out.println("ISBN重复");
                return false;
            }
         libraryMap.remove(new Isbn(isbn));libraryMap.put(new Isbn(book.getISBN()),book );
         for(BOOK each:library)
             if(each.getISBN().equals(new Isbn(isbn).toString())){  //始终使用Isbn类，确保规格化，如去掉空格和'-'等
                 library.remove(each);
                 break;
             }
         library.add(book);
         return true;
    }
}

package tool;

import daoImpl.Library;
import entity.book.BOOK;

import java.util.HashMap;
import java.util.Vector;

public class LibraryMap<K,V> extends HashMap<K,V>{
    public V get(Isbn isbn){
        return super.get(isbn);
    }
    public Vector<BOOK> get(String str){
        Vector<BOOK> books=new Vector<>();
        for(BOOK each: Library.library){
            if(each.getTitle().contains(str))
                books.add(each);
        }
        return books;
    }
}

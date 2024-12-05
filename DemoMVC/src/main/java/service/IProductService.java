package service;

import java.util.List;

public interface IProductService <T>{
    void add(T t);

    List<T> getAll();

    void update(int id, T t);

    void delete(int id);

    T findById(int id);

}

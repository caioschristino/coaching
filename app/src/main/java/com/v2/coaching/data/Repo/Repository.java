package com.v2.coaching.data.Repo;
import com.j256.ormlite.dao.Dao;

import java.util.List;
import java.util.Map;

/**
 * Created by CaioSChristino on 15/09/17.
 */

public interface Repository<T> {
    List<T> getAll();

    T findById(Object aId);

    <R>Dao.CreateOrUpdateStatus createOrUpdate(T item);

    void remove(Object aId);

    List<T> query(Map<String, Object> aMap);
}

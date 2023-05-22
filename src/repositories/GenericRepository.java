package repositories;

import java.util.Map;

public interface GenericRepository<TEntity> {
    TEntity create(TEntity entity);

    Map<Integer, TEntity> getAll();

    TEntity getById(Integer id);

    TEntity update(TEntity entity);

    boolean delete(Integer id);
}

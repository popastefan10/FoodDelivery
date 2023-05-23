package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.function.IntPredicate;

public abstract class GenericRepository<TEntity> {
    protected Connection connection = null;

    protected abstract String getTableName();

    public abstract TEntity create(TEntity entity);

    public abstract Map<Integer, TEntity> getAll();

    public abstract TEntity getById(Integer id);

    public abstract TEntity update(TEntity entity);

    IntPredicate delete = null;

    public boolean delete(Integer id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", getTableName());
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            statement.close();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

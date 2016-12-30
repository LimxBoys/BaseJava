package dbutils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract interface RowProcessor
{
  public abstract Object[] toArray(ResultSet paramResultSet)
    throws SQLException;

  public abstract <T> T toBean(ResultSet paramResultSet, Class<T> paramClass)
    throws SQLException;

  public abstract <T> List<T> toBeanList(ResultSet paramResultSet, Class<T> paramClass)
    throws SQLException;

  public abstract Map<String, Object> toMap(ResultSet paramResultSet)
    throws SQLException;
}
package dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract interface ResultSetHandler<T>
{
  public abstract T handle(ResultSet paramResultSet)
    throws SQLException;
}
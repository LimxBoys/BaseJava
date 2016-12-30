package dbutils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BeanListHandler<T>
  implements ResultSetHandler<List<T>>
{
  private final Class<T> type;
  private final RowProcessor convert;

  public BeanListHandler(Class<T> type)
  {
    this(type, ArrayHandler.ROW_PROCESSOR);
  }

  public BeanListHandler(Class<T> type, RowProcessor convert)
  {
    this.type = type;
    this.convert = convert;
  }

  public List<T> handle(ResultSet rs)
    throws SQLException
  {
    return this.convert.toBeanList(rs, this.type);
  }
}
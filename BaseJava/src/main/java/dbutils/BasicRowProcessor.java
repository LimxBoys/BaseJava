package dbutils;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BasicRowProcessor
  implements RowProcessor
{
  private static final BeanProcessor defaultConvert = new BeanProcessor();

  private static final BasicRowProcessor instance = new BasicRowProcessor();
  private final BeanProcessor convert;

  @Deprecated
  public static BasicRowProcessor instance()
  {
    return instance;
  }

  public BasicRowProcessor()
  {
    this(defaultConvert);
  }

  public BasicRowProcessor(BeanProcessor convert)
  {
    this.convert = convert;
  }

  public Object[] toArray(ResultSet rs)
    throws SQLException
  {
    ResultSetMetaData meta = rs.getMetaData();
    int cols = meta.getColumnCount();
    Object[] result = new Object[cols];

    for (int i = 0; i < cols; i++) {
      result[i] = rs.getObject(i + 1);
    }

    return result;
  }

  public <T> T toBean(ResultSet rs, Class<T> type)
    throws SQLException
  {
    return this.convert.toBean(rs, type);
  }

  public <T> List<T> toBeanList(ResultSet rs, Class<T> type)
    throws SQLException
  {
    return this.convert.toBeanList(rs, type);
  }

  public Map<String, Object> toMap(ResultSet rs)
    throws SQLException
  {
    Map result = new CaseInsensitiveHashMap();
    ResultSetMetaData rsmd = rs.getMetaData();
    int cols = rsmd.getColumnCount();

    for (int i = 1; i <= cols; i++) {
      result.put(rsmd.getColumnName(i), rs.getObject(i));
    }

    return result;
  }

  private static class CaseInsensitiveHashMap extends HashMap<String, Object>
  {
    private final Map<String, String> lowerCaseMap = new HashMap();
    private static final long serialVersionUID = -2848100435296897392L;

    public boolean containsKey(Object key)
    {
      Object realKey = this.lowerCaseMap.get(key.toString().toLowerCase());
      return super.containsKey(realKey);
    }

    public Object get(Object key)
    {
      Object realKey = this.lowerCaseMap.get(key.toString().toLowerCase());
      return super.get(realKey);
    }

    public Object put(String key, Object value)
    {
      Object oldKey = this.lowerCaseMap.put(key.toLowerCase(), key);
      Object oldValue = super.remove(oldKey);
      super.put(key, value);
      return oldValue;
    }

    public void putAll(Map<? extends String, ?> m)
    {
      for (Map.Entry entry : m.entrySet()) {
        String key = (String)entry.getKey();
        Object value = entry.getValue();
        put(key, value);
      }
    }

    public Object remove(Object key)
    {
      Object realKey = this.lowerCaseMap.remove(key.toString().toLowerCase());
      return super.remove(realKey);
    }
  }
}
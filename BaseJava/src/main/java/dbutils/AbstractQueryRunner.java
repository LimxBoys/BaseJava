package dbutils;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.sql.DataSource;

public abstract class AbstractQueryRunner
{
  private volatile boolean pmdKnownBroken = false;
  protected final DataSource ds;

  public AbstractQueryRunner()
  {
    this.ds = null;
  }

  public AbstractQueryRunner(boolean pmdKnownBroken)
  {
    this.pmdKnownBroken = pmdKnownBroken;
    this.ds = null;
  }

  public AbstractQueryRunner(DataSource ds)
  {
    this.ds = ds;
  }

  public AbstractQueryRunner(DataSource ds, boolean pmdKnownBroken)
  {
    this.pmdKnownBroken = pmdKnownBroken;
    this.ds = ds;
  }

  public DataSource getDataSource()
  {
    return this.ds;
  }

  public boolean isPmdKnownBroken()
  {
    return this.pmdKnownBroken;
  }

  protected PreparedStatement prepareStatement(Connection conn, String sql)
    throws SQLException
  {
    return conn.prepareStatement(sql);
  }

  protected Connection prepareConnection()
    throws SQLException
  {
    if (getDataSource() == null) {
      throw new SQLException("QueryRunner requires a DataSource to be invoked in this way, or a Connection should be passed in");
    }

    return getDataSource().getConnection();
  }

  public void fillStatement(PreparedStatement stmt, Object[] params)
    throws SQLException
  {
    ParameterMetaData pmd = null;
    if (!this.pmdKnownBroken) {
      pmd = stmt.getParameterMetaData();
      int stmtCount = pmd.getParameterCount();
      int paramsCount = params == null ? 0 : params.length;

      if (stmtCount != paramsCount) {
        throw new SQLException("Wrong number of parameters: expected " + stmtCount + ", was given " + paramsCount);
      }

    }

    if (params == null) {
      return;
    }

    for (int i = 0; i < params.length; i++)
      if (params[i] != null) {
        stmt.setObject(i + 1, params[i]);
      }
      else
      {
        int sqlType = 12;
        if (!this.pmdKnownBroken) {
          try {
            sqlType = pmd.getParameterType(i + 1);
          } catch (SQLException e) {
            this.pmdKnownBroken = true;
          }
        }
        stmt.setNull(i + 1, sqlType);
      }
  }

  public void fillStatementWithBean(PreparedStatement stmt, Object bean, PropertyDescriptor[] properties)
    throws SQLException
  {
    Object[] params = new Object[properties.length];
    for (int i = 0; i < properties.length; i++) {
      PropertyDescriptor property = properties[i];
      Object value = null;
      Method method = property.getReadMethod();
      if (method == null) {
        throw new RuntimeException("No read method for bean property " + bean.getClass() + " " + property.getName());
      }
      try
      {
        value = method.invoke(bean, new Object[0]);
      } catch (InvocationTargetException e) {
        throw new RuntimeException("Couldn't invoke method: " + method, e);
      } catch (IllegalArgumentException e) {
        throw new RuntimeException("Couldn't invoke method with 0 arguments: " + method, e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Couldn't invoke method: " + method, e);
      }
      params[i] = value;
    }
    fillStatement(stmt, params);
  }

  public void fillStatementWithBean(PreparedStatement stmt, Object bean, String[] propertyNames)
    throws SQLException
  {
    PropertyDescriptor[] descriptors;
    try
    {
      descriptors = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
    }
    catch (IntrospectionException e) {
      throw new RuntimeException("Couldn't introspect bean " + bean.getClass().toString(), e);
    }
    PropertyDescriptor[] sorted = new PropertyDescriptor[propertyNames.length];
    for (int i = 0; i < propertyNames.length; i++) {
      String propertyName = propertyNames[i];
      if (propertyName == null) {
        throw new NullPointerException("propertyName can't be null: " + i);
      }
      boolean found = false;
      for (int j = 0; j < descriptors.length; j++) {
        PropertyDescriptor descriptor = descriptors[j];
        if (propertyName.equals(descriptor.getName())) {
          sorted[i] = descriptor;
          found = true;
          break;
        }
      }
      if (!found) {
        throw new RuntimeException("Couldn't find bean property: " + bean.getClass() + " " + propertyName);
      }
    }

    fillStatementWithBean(stmt, bean, sorted);
  }

  protected void rethrow(SQLException cause, String sql, Object[] params)
    throws SQLException
  {
    String causeMessage = cause.getMessage();
    if (causeMessage == null) {
      causeMessage = "";
    }
    StringBuffer msg = new StringBuffer(causeMessage);

    msg.append(" Query: ");
    msg.append(sql);
    msg.append(" Parameters: ");

    if (params == null)
      msg.append("[]");
    else {
      msg.append(Arrays.deepToString(params));
    }

    SQLException e = new SQLException(msg.toString(), cause.getSQLState(), cause.getErrorCode());

    e.setNextException(cause);

    throw e;
  }

  protected ResultSet wrap(ResultSet rs)
  {
    return rs;
  }

  protected void close(Connection conn)
    throws SQLException
  {
    DbUtils.close(conn);
  }

  protected void close(Statement stmt)
    throws SQLException
  {
    DbUtils.close(stmt);
  }

  protected void close(ResultSet rs)
    throws SQLException
  {
    DbUtils.close(rs);
  }
}
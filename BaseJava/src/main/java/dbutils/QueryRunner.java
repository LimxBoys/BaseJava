package dbutils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class QueryRunner extends AbstractQueryRunner
{
  public QueryRunner()
  {
  }

  public QueryRunner(boolean pmdKnownBroken)
  {
    super(pmdKnownBroken);
  }

  public QueryRunner(DataSource ds)
  {
    super(ds);
  }

  public QueryRunner(DataSource ds, boolean pmdKnownBroken)
  {
    super(ds, pmdKnownBroken);
  }

  public int[] batch(Connection conn, String sql, Object[][] params)
    throws SQLException
  {
    return batch(conn, false, sql, params);
  }

  public int[] batch(String sql, Object[][] params)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return batch(conn, true, sql, params);
  }

  private int[] batch(Connection conn, boolean closeConn, String sql, Object[][] params)
    throws SQLException
  {
    if (conn == null) {
      throw new SQLException("Null connection");
    }

    if (sql == null) {
      if (closeConn) {
        close(conn);
      }
      throw new SQLException("Null SQL statement");
    }

    if (params == null) {
      if (closeConn) {
        close(conn);
      }
      throw new SQLException("Null parameters. If parameters aren't need, pass an empty array.");
    }

    PreparedStatement stmt = null;
    int[] rows = null;
    try {
      stmt = prepareStatement(conn, sql);

      for (int i = 0; i < params.length; i++) {
        fillStatement(stmt, params[i]);
        stmt.addBatch();
      }
      rows = stmt.executeBatch();
    }
    catch (SQLException e) {
      rethrow(e, sql, (Object[])params);
    } finally {
      close(stmt);
      if (closeConn) {
        close(conn);
      }
    }

    return rows;
  }

  @Deprecated
  public <T> T query(Connection conn, String sql, Object param, ResultSetHandler<T> rsh)
    throws SQLException
  {
    return query(conn, false, sql, rsh, new Object[] { param });
  }

  @Deprecated
  public <T> T query(Connection conn, String sql, Object[] params, ResultSetHandler<T> rsh)
    throws SQLException
  {
    return query(conn, false, sql, rsh, params);
  }

  public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object[] params)
    throws SQLException
  {
    return query(conn, false, sql, rsh, params);
  }

  public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh)
    throws SQLException
  {
    return query(conn, false, sql, rsh, (Object[])null);
  }

  @Deprecated
  public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return query(conn, true, sql, rsh, new Object[] { param });
  }

  @Deprecated
  public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return query(conn, true, sql, rsh, params);
  }

  public <T> T query(String sql, ResultSetHandler<T> rsh, Object[] params)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return query(conn, true, sql, rsh, params);
  }

  public <T> T query(String sql, ResultSetHandler<T> rsh)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return query(conn, true, sql, rsh, (Object[])null);
  }

  @SuppressWarnings("unchecked")
private <T> T query(Connection conn, boolean closeConn, String sql, ResultSetHandler<T> rsh, Object[] params)
    throws SQLException
  {
    if (conn == null) {
      throw new SQLException("Null connection");
    }

    if (sql == null) {
      if (closeConn) {
        close(conn);
      }
      throw new SQLException("Null SQL statement");
    }

    if (rsh == null) {
      if (closeConn) {
        close(conn);
      }
      throw new SQLException("Null ResultSetHandler");
    }

    PreparedStatement stmt = null;
    ResultSet rs = null;
    Object result = null;
    try
    {
      stmt = prepareStatement(conn, sql);
      fillStatement(stmt, params);
      rs = wrap(stmt.executeQuery());
      result = rsh.handle(rs);
    }
    catch (SQLException e) {
      rethrow(e, sql, params);
    }
    finally {
      try {
        close(rs);
      } finally {
        close(stmt);
        if (closeConn) {
          close(conn);
        }
      }
    }

    return (T) result;
  }

  public int update(Connection conn, String sql)
    throws SQLException
  {
    return update(conn, false, sql, (Object[])null);
  }

  public int update(Connection conn, String sql, Object param)
    throws SQLException
  {
    return update(conn, false, sql, new Object[] { param });
  }

  public int update(Connection conn, String sql, Object[] params)
    throws SQLException
  {
    return update(conn, false, sql, params);
  }

  public int update(String sql)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return update(conn, true, sql, (Object[])null);
  }

  public int update(String sql, Object param)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return update(conn, true, sql, new Object[] { param });
  }

  public int update(String sql, Object[] params)
    throws SQLException
  {
    Connection conn = prepareConnection();

    return update(conn, true, sql, params);
  }

  private int update(Connection conn, boolean closeConn, String sql, Object[] params)
    throws SQLException
  {
    if (conn == null) {
      throw new SQLException("Null connection");
    }

    if (sql == null) {
      if (closeConn) {
        close(conn);
      }
      throw new SQLException("Null SQL statement");
    }

    PreparedStatement stmt = null;
    int rows = 0;
    try
    {
      stmt = prepareStatement(conn, sql);
      fillStatement(stmt, params);
      rows = stmt.executeUpdate();
    }
    catch (SQLException e) {
      rethrow(e, sql, params);
    }
    finally {
      close(stmt);
      if (closeConn) {
        close(conn);
      }
    }

    return rows;
  }
}
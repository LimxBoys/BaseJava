package dbutils;

import java.sql.SQLException;
import java.util.List;

import com.base.vo.BaseArea;

public class QueryTest {	
	private static  QueryRunner queryRunner = new QueryRunner();
	public static void main(String[] args) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select * from base_area");
		List<BaseArea> list=queryList(buffer.toString(), BaseArea.class);
		System.out.println(list.size());
	}
	public static <T> List<T> queryList(String sql, Class<T> cls) {
		 try {
				return queryRunner.query(ConnectionManager.getConnection(), sql, new BeanListHandler<T>(cls));
			} catch (SQLException e) {
			}finally{
				ConnectionManager.closeConnection();
			}
		return null;
	}
}

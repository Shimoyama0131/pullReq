import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql {
	/**
	 * �����ϒ�������
	 * 
	 * @param conn DB�ڑ����
	 * @param userId ���[�UID
	 * @return ��������
	 * 
	 */
	public PreparedStatement orderedOrderSelect(Connection conn, int userId) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE USER_ID="+ userId + " AND ORDER_STATUS='ORDERED'");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * �������X�g����
	 * 
	 * @param conn DB�ڑ����
	 * @return ��������
	 * 
	 */
	public PreparedStatement userIdSelect(Connection conn) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE ORDER_STATUS='ORDERED'");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * ���[�U����
	 * 
	 * @param conn DB�ڑ����
	 * @param userId ���[�UID
	 * @return �ڋq���
	 * 
	 */
	public PreparedStatement customerInf(Connection conn, int userId) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.user WHERE USER_ID=" + userId);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
}

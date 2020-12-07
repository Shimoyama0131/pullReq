import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;

public class Task {

	/**
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		//DB�ڑ��p�萔
        String DATABASE_NAME = "mydb";
        String PROPATIES = "?characterEncoding=UTF-8&serverTimezone=JST";
        String URL = "jdbc:mySQL://localhost/" + DATABASE_NAME+PROPATIES;
        //DB�ڑ��p�E���[�U�萔
        String USER = "tstg";
        String PASS = "";

        try {
            //MySQL �ɐڑ�����
            Class.forName("com.mysql.cj.jdbc.Driver");
            //�f�[�^�x�[�X�ɐڑ�
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            // �f�[�^�x�[�X�ɑ΂��鏈��
            System.out.println("�f�[�^�x�[�X�ɐڑ��ɐ���");
            
            int userId = 1;
            Sql sql = new Sql();
            // SELECT �����ϒ���
            PreparedStatement oos = sql.orderedOrderSelect(conn, userId);
            // ����
            ResultSet rs = oos.executeQuery();

            // API-001 ���茋�ʂ��擾
            Api_001 api = new Api_001();
            System.out.println(api.result(api.excellentOrderList(rs)));
            rs.close();
            oos.close();
            
            // SELECT ���[�UID
            PreparedStatement uis = sql.userIdSelect(conn);
            // ����
            ResultSet rss = uis.executeQuery();
            
            // SELECT �ڋq���
            while(rss.next()) {
            	PreparedStatement customerInf = sql.customerInf(conn, Integer.valueOf(rss.getString("USER_ID")).intValue());
            	ResultSet ci = customerInf.executeQuery();
            }
            
            // Batch-001 �D�ǌڋq���X�g���擾
            Batch_001 bh = new Batch_001();
//            for(Order or : bh.result(bh.excellentCustomerList(rss))) {
//            	System.out.print(or.getUser_id());
//            	System.out.print(",");
//            	System.out.print(or.getOrder_id());
//            	System.out.print(",");
//            	System.out.print(or.getOrder_status());
//            	System.out.print(",");
//            	System.out.print(or.getMoney());
//            	System.out.println();
//            }
            rss.close();
            uis.close();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

	}
	
//	/**
//	 * �����ϒ�������
//	 * 
//	 * @param conn DB�ڑ����
//	 * @param userId ���[�UID
//	 * @return ��������
//	 * 
//	 */
//	private static PreparedStatement orderedOrderSelect(Connection conn, int userId) {
//		PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE USER_ID="+ userId + " AND ORDER_STATUS='ORDERED'");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return ps;
//	}
	
}

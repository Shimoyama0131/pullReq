import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;

public class Sql_Connect {

	/**
	 * 
	 * @author ���R
	 * @deprecated ���� �E�E�E �����ԍ�(0:�f�[�^�폜, 1:�f�[�^�X�V), id, name, money �����
	 * @see deposit
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

            /***** ���M����SQL *****/
//            // DELETE
//            PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID = ?");
//            delete.setInt(1, 2);
//            int del = delete.executeUpdate();
            
//            // INSERT
//            PreparedStatement insert = conn.prepareStatement("INSERT INTO mydb.account VALUES(?, ?, ?)");
//            insert.setInt(1, 2);
//            insert.setString(2, "����");
//            insert.setInt(3, 5000);
//            int ins = insert.executeUpdate();
            
//            // UPDATE
//            PreparedStatement update = conn.prepareStatement("UPDATE mydb.account SET MONEY=? WHERE ID = ?");
//            update.setInt(1, 10000);
//            update.setInt(2, 2);
//            int upd = update.executeUpdate();
            
//          PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID = ?");
//          delete.setInt(1, 3);
//          int del = delete.executeUpdate();
            
            
            // SELECT
            PreparedStatement pstmt = Select(conn);
            ResultSet res = pstmt.executeQuery();
            
        	List<String> id = new ArrayList<String>();
        	List<String> name = new ArrayList<String>();
        	List<String> money = new ArrayList<String>();
            while(res.next()) {            	
            	id.add(res.getString("ID"));
            	name.add(res.getString("NAME"));
            	money.add(res.getString("MONEY"));
            }
        	System.out.print(id);
        	System.out.print(name);
        	System.out.print(money);
        	System.out.println();
            
        	String sqlProcess = args[0];
        	String sqlId = args[1];
        	String sqlName = args[2];
        	String sqlMoney = args[3];
        	
        	if(sqlProcess.equals("0")) {
        		pstmt = DeleteStatus(conn, Integer.parseInt(sqlId));
        		System.out.println("�폜����");
        	}else {
        		if(!id.contains(sqlId)) {
            		System.out.println(sqlId);
            		pstmt = UpdateInsert(conn, Integer.parseInt(sqlId), sqlName, Integer.parseInt(sqlMoney));
            		System.out.println("�V�K�o�^");
            	}else {
            		pstmt = UpdateStatus(conn, Integer.parseInt(sqlId), Integer.parseInt(sqlMoney));
            		System.out.println("�X�V����");
            	}
        	}
            
            // ����
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	System.out.print(rs.getString("ID"));
            	System.out.print(" ");
            	System.out.print(rs.getString("NAME"));
            	System.out.print(" ");
            	System.out.print(rs.getString("MONEY"));
            	System.out.println();
            }
            rs.close();
            pstmt.close();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

	}

	/**
	 * ����
	 */
	private static PreparedStatement Select(Connection conn) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/** 
	 * �o�^
	 */
	private static PreparedStatement UpdateInsert(Connection conn, int id, String name, int money) {
		PreparedStatement ps = null;
		try {
			PreparedStatement insert = conn.prepareStatement("INSERT INTO mydb.account VALUES(?, ?, ?)");
	        insert.setInt(1, id);
	        insert.setString(2, name);
	        insert.setInt(3, money);
	        int ins = insert.executeUpdate();
	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * �X�V
	 * */
	private static PreparedStatement UpdateStatus(Connection conn, int id, int money) {
		PreparedStatement ps = null;
		try {
			PreparedStatement update = conn.prepareStatement("UPDATE mydb.account SET MONEY=? WHERE ID=?");
			update.setInt(1, money);
			update.setInt(2, id);
	        int ins = update.executeUpdate();
	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * �폜
	 * */
	private static PreparedStatement DeleteStatus(Connection conn, int id) {
		PreparedStatement ps = null;
		try {
			PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID=?");
			delete.setInt(1, id);
	        int ins = delete.executeUpdate();
	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
}

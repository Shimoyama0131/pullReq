
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Api_001 {
	
	/**
	 * API-001 DÇÚq»èÊ
	 * @param orderList ­Ï¶Xg
	 * @return »èÊ
	 * 
	 */
	public boolean result(List<Order> orderList) {
		Br_001 br = new Br_001();
		return br.excellentCustomer(orderList);
	}
	
	/**
	 * API-001 DÇÚqÌ¶Xg
	 * @param rs DBõÊ
	 * @return ¶Xg
	 * 
	 */
	public List<Order> excellentOrderList(ResultSet rs) throws ClassNotFoundException {
        List<Order> orderList = new ArrayList<>();
        try {
        	while(rs.next()) {
            	Order order = new Order();
            	order.setUser_id(Integer.valueOf(rs.getString("USER_ID")).intValue());
            	order.setOrder_id(Integer.valueOf(rs.getString("ORDER_ID")).intValue());
            	order.setOrder_status(rs.getString("ORDER_STATUS"));
            	order.setMoney(Integer.valueOf(rs.getString("MONEY")).intValue());            	
            	orderList.add(order);
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return orderList;
	}
}

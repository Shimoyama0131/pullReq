import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Batch_001 {
	
	/**
	 * Batch-001 優良顧客リスト
	 * @param orderList 発注済注文リスト
	 * @return 判定結果
	 * 
	 */
	public List<Customer> result(ResultSet rss) throws ClassNotFoundException{
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			while(rss.next()) {
            	Customer customer = new Customer();
            	customer.setUser_id(Integer.valueOf(rss.getString("USER_ID")).intValue());
            	customer.setUser_name(rss.getString("USER_NAME"));
            	customerList.add(customer);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	/**
	 * Batch-001 優良顧客の注文リスト
	 * @param rs DB検索結果
	 * @return 注文リスト
	 * 
	 */
	public List<Order> excellentCustomerList(ResultSet rss) throws ClassNotFoundException {
        List<Order> orderList = new ArrayList<>();
        try {
        	while(rss.next()) {
            	Order order = new Order();
            	order.setUser_id(Integer.valueOf(rss.getString("USER_ID")).intValue());
            	order.setOrder_id(Integer.valueOf(rss.getString("ORDER_ID")).intValue());
            	order.setOrder_status(rss.getString("ORDER_STATUS"));
            	order.setMoney(Integer.valueOf(rss.getString("MONEY")).intValue());            	
            	orderList.add(order);
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return orderList;
	}
}

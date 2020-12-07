import java.util.List;

public class Br_001 {
	/**
	 * BR-001 �D�ǌڋq���胋�[��
	 * 
	 * @param orderList �����ϒ������X�g
	 * @return �D�ǌڋq���ۂ�
	 * 
	 */
	public boolean excellentCustomer(List<Order> orderList) {
		int amountThreshold = 1000;
		int countThreshold = 3;
        long highOrderCount = orderList.stream().filter(order->order.getMoney()>=amountThreshold).count();
        return highOrderCount >= countThreshold;
	}
}

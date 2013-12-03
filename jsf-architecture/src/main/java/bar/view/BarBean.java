package bar.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import bar.service.OrderService;

@Component("barBean")
@Scope("request")
public class BarBean {

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("unchecked")
	public List getOrders() {
		return orderService.getOrders();
	}

	public int getRowCount() {
		return orderService.getOrders().size();
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}

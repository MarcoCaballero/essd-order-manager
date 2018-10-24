package io.github.marcocaballero;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-manager")
public class OrderManagerController {
	private static final String HOME_INDEX_TEMPLATE = "index";
	private static final String ORDER_DETAIL_TEMPLATE = "order-detail";
	private OrderRepository orderRepository;

	@Autowired
	public OrderManagerController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@PostConstruct
	public void init() {
		OrderEntity order1 = new OrderEntity("Order 1");
		OrderEntity order2 = new OrderEntity("Order 2");
		order1.setItems(Arrays.asList(
			new ItemEntity("Item_1__Order_1"), 
			new ItemEntity("Item_2__Order_1"),
			new ItemEntity("Item_3__Order_1"))
		);
		orderRepository.save(order1);
		orderRepository.save(order2);
	}

	@GetMapping({"/orders", "/", ""})
	public String getOrders(Model model) {

		model.addAttribute("orders", orderRepository.findAll());

		return HOME_INDEX_TEMPLATE;
	}

	@GetMapping("/order/{orderId}")
	public String getOrder(Model model, @PathVariable long orderId) {

		OrderEntity order = (orderRepository.findById(orderId))
											.orElse(new OrderEntity("Not Found 404"));

		model.addAttribute("order", order);

		return ORDER_DETAIL_TEMPLATE;
	}
}

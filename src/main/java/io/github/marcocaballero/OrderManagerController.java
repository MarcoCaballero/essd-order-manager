package io.github.marcocaballero;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order-manager")
public class OrderManagerController {
	private static final String HOME_INDEX_TEMPLATE = "index.template";
	private static final String ORDER_DETAIL_TEMPLATE = "order-detail.template";
	private static final String ORDER_FORM_TEMPLATE = "form.template";
	private static final String REDIRECT_HOME = "redirect:/order-manager/orders";

	private OrderService orderService;
	private OrderRepository orderRepository;

	@Autowired
	public OrderManagerController(OrderService orderService, OrderRepository orderRepository) {
		this.orderService = orderService;
		this.orderRepository = orderRepository;
	}

	@PostConstruct
	public void init() {
		// OrderEntity order1 = new OrderEntity("Order 1");
		// OrderEntity order2 = new OrderEntity("Order 2");
		// order1.setItems(Arrays.asList(
		// new ItemEntity("Item_1__Order_1"),
		// new ItemEntity("Item_2__Order_1"),
		// new ItemEntity("Item_3__Order_1")));
		// orderRepository.save(order1);
		// orderRepository.save(order2);
	}

	@GetMapping({"/orders", "/", ""})
	public String getOrders(Model model) {

		List<OrderEntity> orders = orderRepository.findAll();

		model.addAttribute("orders", orders);
		model.addAttribute("hasOrders", !orders.isEmpty());

		return HOME_INDEX_TEMPLATE;
	}

	@GetMapping("/order/{orderId}")
	public String getOrder(Model model, @PathVariable long orderId) {

		model.addAttribute("order", orderService.findOrderById(orderId));

		return ORDER_DETAIL_TEMPLATE;
	}

	@GetMapping("/order/new")
	public String getFormNew() {

		return ORDER_FORM_TEMPLATE;
	}

	
	@GetMapping("/order/edit/{orderId}")
	public String getFormEdit(Model model, @PathVariable long orderId) {

		model.addAttribute("order", orderService.findOrderById(orderId));

		return ORDER_FORM_TEMPLATE;
	}

	@PostMapping("/order")
	public String UpdateOrder(Model model, @RequestParam String orderTitle,
			@RequestParam("element") String... elements) {
		
		orderService.addOrder(orderTitle, elements);

		return REDIRECT_HOME;
	}
}

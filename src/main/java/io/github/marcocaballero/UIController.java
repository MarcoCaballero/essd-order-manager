package io.github.marcocaballero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-manager")
public class UIController {
	private static final String HOME_INDEX_TEMPLATE = "index.template";
	private static final String ORDER_DETAIL_TEMPLATE = "order-detail.template";
	private static final String ORDER_FORM_TEMPLATE = "form.template";
	private static final String REDIRECT_HOME = "redirect:/order-manager/orders";

	private OrderService orderService;
	private OrderRepository orderRepository;

	@Autowired
	public UIController(OrderService orderService, OrderRepository orderRepository) {
		this.orderService = orderService;
		this.orderRepository = orderRepository;
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
		OrderEntity order = orderService.findOrderById(orderId);

		model.addAttribute("order", order);
		model.addAttribute("showDeletBtn", order.getItemsCount() > 1);

		return ORDER_DETAIL_TEMPLATE;
	}

	@GetMapping("/order/new")
	public String getFormEmpty() {

		return ORDER_FORM_TEMPLATE;
	}

	
	@GetMapping("/order/edit/{orderId}")
	public String getFormFilled(Model model, @PathVariable long orderId) {

		model.addAttribute("order", orderService.findOrderById(orderId));

		return ORDER_FORM_TEMPLATE;
	}
}

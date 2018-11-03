package io.github.marcocaballero;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order-manager")
public class OrderManagerController {

	private final Logger logger = LoggerFactory.getLogger(OrderManagerController.class);

	private static final String REDIRECT_RELATIVE = "redirect:/";
	private static final String REDIRECT_HOME = REDIRECT_RELATIVE + "order-manager/orders";
	private static final String REDIRECT_ORDER_ID = REDIRECT_RELATIVE + "order-manager/order/";

	private OrderService orderService;

	@Autowired
	public OrderManagerController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/order")
	public String createNewOrder(Model model, @RequestParam String orderTitle,
			                     @RequestParam("element") List<String> elements) {

		orderService.addOrder(orderTitle, elements);

		return REDIRECT_HOME;
	}

	@PostMapping("/order/{orderId}")
	public String updateOrder(@PathVariable long orderId,
							  @RequestParam String orderTitle,
							  @RequestParam("elementID") List<Integer> elementIds, 
							  @RequestParam("element") List<String> elements) {

		logger.info("Updating Order with ID: {}", orderId);

		Map<Long, List<String>> itemsMap = IntStream.range(0, elementIds.size())
				.boxed()
				.map(idx -> new Pair<Long, String>(Long.valueOf(elementIds.get(idx)), elements.get(idx)))
				.filter(pair -> pair instanceof Pair<?, ?>) // assertion
				.collect(Collectors.groupingBy(Pair::getValue0, Collectors.mapping(Pair::getValue1, Collectors.toList())));

		orderService.updateOrder(orderId, orderTitle, itemsMap);

		return REDIRECT_ORDER_ID + String.valueOf(orderId);
	}

	@DeleteMapping("/order/{orderId}/item/{itemId}")
	public ResponseEntity deleteItem(@PathVariable long orderId, @PathVariable long itemId) {
		orderService.deleteItemById(orderId, itemId);

		return ResponseEntity.ok().build();
	}
}
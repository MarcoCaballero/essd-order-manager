package io.github.marcocaballero;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity findOrderById(long orderId) {
        return(orderRepository.findById(orderId)).orElse(new OrderEntity("Not Found 404"));
    }

    public OrderEntity addOrder(String title, String... itemsList) {
        OrderEntity order = new OrderEntity(title);

        order.setItems(Arrays.asList(itemsList)
                                    .stream()
                                    .map(ItemEntity::new)
                                    .collect(Collectors.toList()));

        return orderRepository.save(order);
    }
}

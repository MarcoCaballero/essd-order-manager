package io.github.marcocaballero;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final int NEW_ITEMS_KEY = 0;
    
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity findOrderById(long orderId) {
        return(orderRepository.findById(orderId))
                              .orElse(new OrderEntity(" 404 - Not Found"));
    }

    public OrderEntity addOrder(String title, List<String> items) {
        OrderEntity order = new OrderEntity(title);

        order.setItems(items.stream()
                            .map(ItemEntity::new)
                            .collect(Collectors.toList()));

        return orderRepository.save(order);
    }

    public OrderEntity updateOrder(long orderId, String orderTitle, Map<Long, List<String>> data) {
        OrderEntity order = orderRepository.getOne(orderId);
        
        updateExistingItems(order, data);
        
        insertNewItems(order, data);
        
        order.setTitle(orderTitle);

        return orderRepository.save(order);
    }

    public void deleteOrderById(long orderId) {
        orderRepository.findById(orderId)
            .ifPresent(order -> orderRepository.delete(order));
    }

    public OrderEntity deleteItemById(long orderId, long itemId) {
        OrderEntity order = orderRepository.getOne(orderId);
        
        order.deleteItemById(itemId);

        return orderRepository.save(order);
    }

    private void updateExistingItems(OrderEntity order, Map<Long, List<String>> data) {
        order.getItems()
                .parallelStream()
                .filter(item -> data.containsKey(item.getId()))
                .forEach(item -> {
                    String value = data.get(item.getId()).get(NEW_ITEMS_KEY);
                    item.setName(value);
                });
    }

    private void insertNewItems(OrderEntity order, Map<Long, List<String>> data) {
        final List<String> newItems = data.get(Long.valueOf(NEW_ITEMS_KEY));
        if (newItems != null)
            order.addItems(newItems);
    }
}

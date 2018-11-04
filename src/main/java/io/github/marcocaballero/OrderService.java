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

    public void addOrder(String title, List<String> items) {
        OrderEntity order = new OrderEntity(title);

        order.setItems(items.stream()
                            .map(ItemEntity::new)
                            .collect(Collectors.toList()));

        orderRepository.save(order);
    }

    public void updateOrder(long orderId, String orderTitle, Map<Long, List<String>> data) {
        orderRepository.findById(orderId).ifPresent(order -> {

            updateExistingItems(order, data);
        
            insertNewItems(order, data);
            
            order.setTitle(orderTitle);

            orderRepository.save(order);
        });
    }

    public void updateOrder(long orderId, long itemId, boolean status) {
        orderRepository.findById(orderId).ifPresent(order -> {

            order.setItemChecked(itemId, status);

            orderRepository.save(order);
        });
    }

    public void deleteOrderById(long orderId) {
        orderRepository.findById(orderId)
            .ifPresent(order -> orderRepository.delete(order));
    }

    public void deleteItemById(long orderId, long itemId) {
        orderRepository.findById(orderId)
            .ifPresent(order -> {
                order.deleteItemById(itemId);
                orderRepository.save(order);
            });
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

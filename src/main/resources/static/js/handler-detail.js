deleteItemById = (orderId, itemId) => {
    fetch(`/order-manager/order/${orderId}/item/${itemId}`, {
        method: 'DELETE'
    }).then(location.reload());
}
const URL_BASE = '/order-manager/order/';

deleteItemById = (orderId, itemId) => {
    fetch(`${URL_BASE}${orderId}/item/${itemId}`, {
        method: 'DELETE'
    }).then(location.reload());
}

deleteOrderById = (orderId) => {
    fetch(`${URL_BASE}${orderId}`, {
        method: 'DELETE'
    }).then(window.location.replace(`${URL_BASE}`));
}

 checkDeletion = () => {
    $('#orderDeletionModal').modal('show');
}

closeModal = () => {
    $('#orderDeletionModal').modal('hide');
}
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
    COMMON_FUNCS.showModal('orderDeletionModal');
}

closeModal = () => {
    COMMON_FUNCS.hideModal('orderDeletionModal');
}

toggleCheck = (DOMelem, orderId, itemId) => {
    let label = `#label_check_${itemId}`;
    let status = DOMelem.checked;
    
    COMMON_FUNCS.toggleCrossText(label, status);

    fetch(`${URL_BASE}${orderId}/check/${itemId}`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({checked: status})
    });

    COMMON_FUNCS.triggerAlert(itemId, status);
}

init = () => {
   var elems =  COMMON_FUNCS.getAllElementsWithSelector('.custom-control-input');
   elems.forEach(elem => {
       if(elem.value == "true") {
           elem.checked = true;
           document.getElementById(`label_${elem.id}`).setAttribute('crossed', true);
       }
    });
}
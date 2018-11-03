const newElementHTML = '<div class="md-form"><input type="text" id="order_name_1" name="element" class="form-control" onchange="checkFormValidty(this)" onkeyup="checkFormValidty(this)" onpaste="checkFormValidty(this)" oncut="checkFormValidty(this)" required><input type="number" name="elementID" value="0" hidden><label for="order_name_1">Item</label></div>';

checkFormValidty = (elem) => {
    const shouldDisableButton = (!new_order_form.checkValidity()) || elem.value == '';
    send_new_order_btn.disabled = shouldDisableButton;
}

send_new_order_btn.onclick = () => {
    new_order_form.submit();
}

add_item_btn.onclick = () => {
    $(newElementHTML).insertBefore($('#add_item_btn'));
}

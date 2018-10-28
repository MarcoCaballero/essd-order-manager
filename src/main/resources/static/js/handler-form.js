const newElementHTML = '<div class="md-form"><input type="text" id="order_name_1" name="element" class="form-control"><label for="order_name_1">Item</label></div>';

send_new_order_btn.onclick = () => {
    new_order_form.submit();
}

add_item_btn.onclick = () => {
    $(newElementHTML).insertBefore($('#add_item_btn'));
}
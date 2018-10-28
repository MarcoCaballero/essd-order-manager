if (go_to_orders_list_btn) {
    go_to_orders_list_btn.onclick = () => {
        COMMON_FUNCS.scrollTo("#listSection");
        COMMON_FUNCS.animateObject('orders_title_div', 'wow fadeInLeft');
        COMMON_FUNCS.animateObject('orders_list_div', 'wow fadeInRight');
    }
}
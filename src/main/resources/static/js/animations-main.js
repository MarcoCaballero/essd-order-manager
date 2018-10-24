/* index.html fades */

const onAnimationEndDetected = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
const animationPattern = 'animated wow fadeInLeft fadeInRight';

elements = [
    'orders_list_div',
    'orders_title_div'
]

elements.forEach(elementID => {
    $(`#${elementID}`).one(onAnimationEndDetected, () => {
        COMMON_FUNCS.removeCssClass(elementID, `${animationPattern}`);
    });
});


/* fab-button scrolls with mdboostrap navbars */

'use strict';

(function($) {
    var SCROLLING_NAVBAR_OFFSET_TOP = 50;
    $(window).on('scroll', function() {
        var $navbar = $('.navbar');
        var $fab_btn = $('.scroll-with-nav');
        if ($navbar.length) {
            if ($navbar.offset().top > SCROLLING_NAVBAR_OFFSET_TOP) {
                $fab_btn.addClass('fab-collapse');
            } else {
                $fab_btn.removeClass('fab-collapse');
            }
        }
    });
})(jQuery);



const buildTemplate = (cssClass) => { return `<div class="tooltip ${cssClass}" role="tooltip"><div class="arrow"></div><div class="tooltip-inner"></div></div>` }
const optionsPrimary = {
    cssClass: 'tooltip-primary',
    options: {
        template: buildTemplate('tooltip-primary')
    }
}
const optionsSecondary = {
    cssClass: 'tooltip-secondary',
    options: {
        template: buildTemplate('tooltip-secondary')
    }
}
const optionsSecondaryWithSpy = {
    cssClass: 'tooltip-secondary-scroll-inside',
    options: {
        template: buildTemplate('tooltip-secondary-scroll-inside', )
    }
}

tooltipOptions = [
    optionsPrimary,
    optionsSecondary,
    optionsSecondaryWithSpy
]

tooltipOptions.forEach(tooltip => {
    $(function() {
        $(`[data-toggle="${tooltip.cssClass}"]`).tooltip(tooltip.options);
    });
});
var COMMON_FUNCS = COMMON_FUNCS || (() => {

    let alertCounter = 0;

    alert = (itemId, status) => {
        return ` <div id="alert_${alertCounter}" class="alert alert-success" role="alert">
                        You have ${status ? 'checked': 'unchecked'} the item with ID: ${itemId}
                        <button type="button" class="close ml-2" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>`
    }

    getAllElementsWithSelector = (cssClass) => {
        return document.querySelectorAll(`${cssClass}`);
    }

    scrollTo = (path, options = { speed: 1000, easing: 'easeOutCubic' }) => {
        document.querySelector(path).scrollIntoView({
            behavior: 'smooth'
        });
    }

    removeCssClass = (elementID, cssClass) => {
        $(`#${elementID}`).removeClass(`${cssClass}`);
    }

    animateObject = (elementID, animationTarget) => {
        $(`#${elementID}`).addClass(`animated ${animationTarget}`);
    }

    showModal = (elementID) => {
        $(`#${elementID}`).modal('show');
    }

    hideModal = (elementID) => {
        $(`#${elementID}`).modal('hide');
    }

    toggleCrossText = (selector, status) => {
        $(selector).attr('crossed', status);
    }

    triggerAlert = (itemId, status) => {
        alertCounter++;

        $(alert(itemId,status)).insertAfter($('#navDetail'));
    
        $(`#alert_${alertCounter}`).css('top', `${(window.innerHeight - (70*alertCounter))}px`);
        $(`#alert_${alertCounter}`).css('left', `${(window.innerWidth/3)}px`);
    
        setTimeout(() => {
            $(`#alert_${alertCounter}`).remove();
            alertCounter--;
        }, 3000);
        
    }
    
    return {
        getAllElementsWithSelector,
        toggleCrossText,
        removeCssClass,
        animateObject,
        triggerAlert,
        showModal,
        hideModal,
        scrollTo
    };
})();

// UMD (Universal Module Definition)
(function(root) {
    if (typeof define === 'function' && define.amd) {
        // AMD
        define([], function() {
            return COMMON_FUNCS;
        });
    } else if (typeof module !== 'undefined' && typeof exports === 'object') {
        // Node.js
        module.exports = COMMON_FUNCS;
    } else if (root !== undefined) {
        // Global variable
        root.COMMON_FUNCS = COMMON_FUNCS;
    }
})(this);
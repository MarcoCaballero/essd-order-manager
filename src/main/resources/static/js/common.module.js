var COMMON_FUNCS = COMMON_FUNCS || (function() {
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

    return {
        scrollTo,
        removeCssClass,
        animateObject
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
if(!(/Android|iPhone|iPad|iPod|BlackBerry|Windows Phone/i).test(navigator.userAgent || navigator.vendor || window.opera)){
    skrollr.init({
        forceHeight: false,
		smoothScrolling: true,
		mobileDeceleration: 0.004
    });
}
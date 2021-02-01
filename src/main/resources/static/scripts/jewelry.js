// Burger
const hamburger = document.querySelector(".nav-burger");
const navLinks = document.querySelector(".nav-links");
const links = document.querySelectorAll(".nav-links li");

hamburger.addEventListener("click", () => {
    navLinks.classList.toggle("open");
    optionsContainer1.classList.remove("active");
    optionsContainer2.classList.remove("active");
    optionsContainer3.classList.remove("active");
    optionsContainer4.classList.remove("active");
    links.forEach(link => {
        link.classList.toggle("fade");
    });
});

$(document).ready(function () {

    // Loader
    $(window).load(function () {
        setTimeout(function () {
            $('.loader').fadeIn();
        }, 1000);
        setTimeout(function () {
            $('.loader').fadeOut('slow');
        }, 3000);

    })

    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({
            scrollTop: 0
        }, 1500);
        return false;
    });

    // Header fixed on scroll
    $(document).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('#header').addClass('header-scrolled');
        } else {
            $('#header').removeClass('header-scrolled');
        }
    });

    // Color change on scroll
    $(document).scroll(function () {
        scroll_p = $(this).scrollTop();

        if (scroll_p > 100) {
            $(".nav-menu li a").css('color', "black");
            $(".nav-links li a").css('color', "black");
            $(".btn.wishlist").css('color', "black");
            $(".btn.cart").css('color', "black");
            $(".nav-logo a").css('color', "black");
            $(".line").css('stroke', "black");
            $(".option").css('color', "black");
        } else {
            $(".nav-menu li a").css('color', "white");
            $(".nav-links li a").css('color', "white");
            $(".btn.wishlist").css('color', "white");
            $(".btn.cart").css('color', "white");
            $(".nav-logo a").css('color', "white");
            $(".line").css('stroke', "white");
            $(".option").css('color', "white");
        }

    });

    // Smooth scroll
    $('.nav-menu li a[href^="#"]').on('click', function (e) {
        e.preventDefault();

        var target = $(this.hash);

        if (target.length) {
            $('html, body').stop().animate({
                scrollTop: target.offset().top - 70
            }, 1000);
        }
    });

    // Cart Total Price
    var totalPrice = 0;
    var cartSize = $('#cart-size').val();
    while (cartSize > 0) {
        var price = $('#pPr' + cartSize).val();
        var productId = $('#pId' + cartSize).val();
        var quantity = $('#qty' + productId).val();

        totalPrice += (price * quantity);
        cartSize--;
    }
    $('#price-count').text(totalPrice.toFixed(2));

});

// Left menu
var selected1 = document.querySelector("#selected1");
var optionsContainer1 = document.querySelector("#options-container1");

selected1.addEventListener("click", () => {
    optionsContainer4.classList.remove("active");
    optionsContainer2.classList.remove("active");
    optionsContainer3.classList.remove("active");
    optionsContainer1.classList.toggle("active");
});

var selected2 = document.querySelector("#selected2");
var optionsContainer2 = document.querySelector("#options-container2");

selected2.addEventListener("click", () => {
    optionsContainer4.classList.remove("active");
    optionsContainer1.classList.remove("active");
    optionsContainer3.classList.remove("active");
    optionsContainer2.classList.toggle("active");
});

var selected3 = document.querySelector("#selected3");
var optionsContainer3 = document.querySelector("#options-container3");

selected3.addEventListener("click", () => {
    optionsContainer4.classList.remove("active");
    optionsContainer1.classList.remove("active");
    optionsContainer2.classList.remove("active");
    optionsContainer3.classList.toggle("active");
});

var selected4 = document.querySelector("#selected4");
var optionsContainer4 = document.querySelector("#options-container4");

selected4.addEventListener("click", () => {
    optionsContainer3.classList.remove("active");
    optionsContainer1.classList.remove("active");
    optionsContainer2.classList.remove("active");
    optionsContainer4.classList.toggle("active");
});

// Slick Slider
$('.slider').slick({
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: false,
    dots: false,
    centerMode: true,
    slidesToShow: 5,

    responsive: [{

        breakpoint: 1300,
        settings: {
            slidesToShow: 3
        }
    },
        {
            breakpoint: 990,
            settings: {
                slidesToShow: 2
            }
        },
        {
            breakpoint: 768,
            settings: {
                slidesToShow: 1
            }
        }
    ]
});

// Top Section Slider
(function () {

    var $$ = function (selector, context) {
        var context = context || document;
        var elements = context.querySelectorAll(selector);
        return [].slice.call(elements);
    };

    function _fncSliderInit($slider, options) {
        var prefix = ".fnc-";

        var $slider = $slider;
        var $slidesCont = $slider.querySelector(prefix + "slider__slides");
        var $slides = $$(prefix + "slide", $slider);
        var $controls = $$(prefix + "nav__control", $slider);
        var $controlsBgs = $$(prefix + "nav__bg", $slider);
        var $progressAS = $$(prefix + "nav__control-progress", $slider);

        var numOfSlides = $slides.length;
        var curSlide = 1;
        var sliding = false;
        var slidingAT = +parseFloat(getComputedStyle($slidesCont)["transition-duration"]) * 1000;
        var slidingDelay = +parseFloat(getComputedStyle($slidesCont)["transition-delay"]) * 1000;

        var autoSlidingActive = false;
        var autoSlidingTO;
        var autoSlidingDelay = 5000;
        var autoSlidingBlocked = false;

        var $activeSlide;
        var $activeControlsBg;
        var $prevControl;

        function setIDs() {
            $slides.forEach(function ($slide, index) {
                $slide.classList.add("fnc-slide-" + (index + 1));
            });

            $controls.forEach(function ($control, index) {
                $control.setAttribute("data-slide", index + 1);
                $control.classList.add("fnc-nav__control-" + (index + 1));
            });

            $controlsBgs.forEach(function ($bg, index) {
                $bg.classList.add("fnc-nav__bg-" + (index + 1));
            });
        }

        setIDs();

        function afterSlidingHandler() {
            $slider.querySelector(".m--previous-slide").classList.remove("m--active-slide", "m--previous-slide");
            $slider.querySelector(".m--previous-nav-bg").classList.remove("m--active-nav-bg", "m--previous-nav-bg");

            $activeSlide.classList.remove("m--before-sliding");
            $activeControlsBg.classList.remove("m--nav-bg-before");
            $prevControl.classList.remove("m--prev-control");
            $prevControl.classList.add("m--reset-progress");
            var triggerLayout = $prevControl.offsetTop;
            $prevControl.classList.remove("m--reset-progress");

            sliding = false;
            var layoutTrigger = $slider.offsetTop;

            if (autoSlidingActive && !autoSlidingBlocked) {
                setAutoslidingTO();
            }
        }

        function performSliding(slideID) {
            if (sliding) return;
            sliding = true;
            window.clearTimeout(autoSlidingTO);
            curSlide = slideID;

            $prevControl = $slider.querySelector(".m--active-control");
            $prevControl.classList.remove("m--active-control");
            $prevControl.classList.add("m--prev-control");
            $slider.querySelector(prefix + "nav__control-" + slideID).classList.add("m--active-control");

            $activeSlide = $slider.querySelector(prefix + "slide-" + slideID);
            $activeControlsBg = $slider.querySelector(prefix + "nav__bg-" + slideID);

            $slider.querySelector(".m--active-slide").classList.add("m--previous-slide");
            $slider.querySelector(".m--active-nav-bg").classList.add("m--previous-nav-bg");

            $activeSlide.classList.add("m--before-sliding");
            $activeControlsBg.classList.add("m--nav-bg-before");

            var layoutTrigger = $activeSlide.offsetTop;

            $activeSlide.classList.add("m--active-slide");
            $activeControlsBg.classList.add("m--active-nav-bg");

            setTimeout(afterSlidingHandler, slidingAT + slidingDelay);
        }

        function controlClickHandler() {
            if (sliding) return;
            if (this.classList.contains("m--active-control")) return;
            if (options.blockASafterClick) {
                autoSlidingBlocked = true;
                $slider.classList.add("m--autosliding-blocked");
            }

            var slideID = +this.getAttribute("data-slide");

            performSliding(slideID);
        }

        $controls.forEach(function ($control) {
            $control.addEventListener("click", controlClickHandler);
        });

        function setAutoslidingTO() {
            window.clearTimeout(autoSlidingTO);
            var delay = +options.autoSlidingDelay || autoSlidingDelay;
            curSlide++;
            if (curSlide > numOfSlides) curSlide = 1;

            autoSlidingTO = setTimeout(function () {
                performSliding(curSlide);
            }, delay);
        }

        if (options.autoSliding || +options.autoSlidingDelay > 0) {
            if (options.autoSliding === false) return;

            autoSlidingActive = true;
            setAutoslidingTO();

            $slider.classList.add("m--with-autosliding");
            var triggerLayout = $slider.offsetTop;

            var delay = +options.autoSlidingDelay || autoSlidingDelay;
            delay += slidingDelay + slidingAT;

            $progressAS.forEach(function ($progress) {
                $progress.style.transition = "transform " + (delay / 1000) + "s";
            });
        }

        $slider.querySelector(".fnc-nav__control:first-child").classList.add("m--active-control");

    }

    window.fncSlider = function (sliderSelector, options) {
        var $sliders = $$(sliderSelector);

        $sliders.forEach(function ($slider) {
            _fncSliderInit($slider, options);
        });
    };
}());

fncSlider(".example-slider", {
    autoSlidingDelay: 4000
});

var $demoCont = document.querySelector(".demo-cont");

[].slice.call(document.querySelectorAll(".fnc-slide__action-btn")).forEach(function ($btn) {
    $btn.addEventListener("click", function () {
    });
});

// Login Page
document.getElementById("open-popup-btn").addEventListener("click", function () {
    document.getElementsByClassName("popup")[0].classList.add("active");
    const styleElem = document.head.appendChild(document.createElement("style"));
    styleElem.innerHTML = "#popup-ad::after {z-index: 0;}";
  });
  
  document.getElementById("dismiss-popup-btn").addEventListener("click", function () {
    document.getElementsByClassName("popup")[0].classList.remove("active");
      const styleElem = document.head.appendChild(document.createElement("style"));
      styleElem.innerHTML = "#popup-ad::after {z-index: -1;}";
  });
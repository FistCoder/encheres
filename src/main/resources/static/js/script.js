let toggle = document.getElementById("toggle");

let checksVente = Array.from(document.querySelectorAll(".ventes .ctn_radio_all"));
let checksAchat = Array.from(document.querySelectorAll(".achats .ctn_radio_all"));

checksVente.forEach(function (check) {
    check.classList.add("inactive");
})
toggle.addEventListener("click", function () {
    checksAchat.forEach(function (check) {
        check.classList.toggle("inactive");
    })
    checksVente.forEach(function (check) {
        check.classList.toggle("inactive");
    })
});

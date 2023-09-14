

document.getElementById("form").addEventListener("submit", function(e) {
    var files = document.getElementById("images").files;
    if (files.length === 0) {
        this.removeAttribute("enctype");
    }
});

window.onload = function() {
    var productIdValue = document.querySelector("input[name='id_product']").value;

    if (productIdValue) {
        document.getElementById("productName").readOnly = true;
        document.getElementById("description").readOnly = true;
        document.getElementById("rate").disabled = true;
        document.getElementById("price").readOnly = true;
        document.getElementById("images").style.display = "none";
        document.querySelector("label[for='images']").style.display = "none";
    }
}



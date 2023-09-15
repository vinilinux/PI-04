

document.getElementById("form").addEventListener("submit", function(e) {
    var files = document.getElementById("images").files;
    if (files.length === 0) {
        this.removeAttribute("enctype");
    }
});

window.onload = function() {
    var productIdValue = document.querySelector("input[name='id_product']").value;

    if (productIdValue) {
        document.getElementById("productName").readOnly = false;
        document.getElementById("description").readOnly = false;
        document.getElementById("rate").disabled = false;
        document.getElementById("price").readOnly = false;
        document.getElementById("images").style.display = true;
        document.querySelector("label[for='images']").style.display = true;
    }
}



document.addEventListener("DOMContentLoaded", function() {
    const statusNativo = document.getElementById("status-ativo");
    const statusInativo = document.getElementById("status-inativo");

    statusNativo.checked = true;
    statusInativo.disabled = true;
});


document.addEventListener("DOMContentLoaded", function() {
    const passwordInput = document.getElementById("password");
    const passwordConfirmationInput = document.getElementById("password-confirmation");
    const errorMessage = document.getElementById("error-message");

    const form = document.getElementById("form");
    form.addEventListener("submit", function(event) {
        if (passwordInput.value !== passwordConfirmationInput.value) {
            event.preventDefault();
            errorMessage.textContent = "As senhas não coincidem. Por favor, tente novamente.";
        }
    });
});



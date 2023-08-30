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
            errorMessage.classList.add("erro-msg");
        } else {
            errorMessage.textContent = "";
            errorMessage.classList.remove("erro-msg");
        }
    });
});


document.addEventListener("DOMContentLoaded", function() {
    var cpfInput = document.getElementById("cpf");
    var form = document.getElementById("form");

    form.addEventListener("submit", function(event) {
        var cpfValue = cpfInput.value.replace(/\D/g, "");

        if (!validarCPF(cpfValue)) {
            event.preventDefault();
            cpfInput.classList.add("erro");
            cpfInput.nextElementSibling.innerText = "CPF inválido";
        } else {
            cpfInput.classList.remove("erro");
            cpfInput.nextElementSibling.innerText = "";

            cpfInput.value = cpfValue;
        }
    });

    cpfInput.addEventListener("input", function() {
        var value = this.value.replace(/\D/g, "");

        if (value.length > 11) {
            value = value.slice(0, 11);
        }

        if (value.length > 9) {
            value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
        } else if (value.length > 6) {
            value = value.replace(/(\d{3})(\d{3})(\d{3})/, "$1.$2.$3");
        } else if (value.length > 3) {
            value = value.replace(/(\d{3})(\d{3})/, "$1.$2");
        }

        this.value = value;
    });

    function validarCPF(cpf) {
        cpf = cpf.replace(/\D/g, '');
        if (cpf.length !== 11 || /^(.)\1+$/.test(cpf)) {
            return false;
        }

        var sum = 0;
        var remainder;

        for (var i = 1; i <= 9; i++) {
            sum += parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }

        remainder = (sum * 10) % 11;

        if ((remainder === 10) || (remainder === 11)) {
            remainder = 0;
        }

        if (remainder !== parseInt(cpf.substring(9, 10))) {
            return false;
        }

        sum = 0;

        for (var i = 1; i <= 10; i++) {
            sum += parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }

        remainder = (sum * 10) % 11;

        if ((remainder === 10) || (remainder === 11)) {
            remainder = 0;
        }

        if (remainder !== parseInt(cpf.substring(10, 11))) {
            return false;
        }

        return true;
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('search');
    const tableRows = document.querySelectorAll('tbody tr');

    // Função para filtrar a tabela com base no valor de pesquisa
    function filterTable(searchTerm) {
        tableRows.forEach(function(row) {
            const username = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
            const shouldDisplay = username.includes(searchTerm);
            row.style.display = shouldDisplay ? 'table-row' : 'none';
        });
    }

    // Adicione um evento de entrada para atualizar o filtro conforme o usuário digita
    searchInput.addEventListener('input', function() {
        const searchTerm = searchInput.value.trim().toLowerCase();
        filterTable(searchTerm);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('search');
    const tableRows = document.querySelectorAll('tbody tr');

    function filterTable(searchTerm) {
        tableRows.forEach(function(row) {
            const nameCell = row.querySelector('td:nth-child(2)');
            const name = nameCell.textContent.toLowerCase();
            const shouldDisplay = name.includes(searchTerm);
            row.style.display = shouldDisplay ? 'table-row' : 'none';
        });
    }

    searchInput.addEventListener('input', function() {
        const searchTerm = searchInput.value.trim().toLowerCase();
        filterTable(searchTerm);
    });
});
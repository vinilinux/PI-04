<!DOCTYPE html>
<html lang="pt-br">

<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Sora:wght@300;400;500;600&display=swap"
            rel="stylesheet">
    <title>Cadastro Usuario</title>

    <link rel="stylesheet" href="/Style/cadastroUsuario.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/inputmask/5.0.6/jquery.inputmask.min.js"></script>
    <script src="/JavaScript/script.js"></script>


</head>

<body>

<div class="container">
    <section class="header">
        <h2>Cadastro</h2>
    </section>

    <form id="form" class="form" action="createProduct" method="post" enctype="multipart/form-data">

        <div class="form-content">
            <label for="productName">Nome do Produto</label>
            <input type="text" id="productName" name="productName" placeholder="Digite o nome do Produto" required />
        </div>

        <div class="form-content">
            <label for="description">Descrição</label>
            <input type="text" id="description" name="description" placeholder="Digite a descrição" required/>
            <span class="erro-msg"></span>
        </div>

        <div class="form-content">
            <label for="rate">Avaliação</label>
            <input type="number" id="rate" name="rate" placeholder="Avaliação de 1 à 5" required />
        </div>


        <div class="form-content">
            <label for="price">Preço</label>
            <input type="number" id="price" name="price" placeholder="Informe o preço" required />
        </div>

        <div class="form-content">
            <label for="amount">Quantidade</label>
            <input type="number" id="amount" name="amount" placeholder="Informe a quantidade"
                   required />
        </div>

        <div class="error-message" id="error-message"></div>

        <p>Status:</p><br>
        <div class="box-select">
            <div>

                <input type="radio" id="status-ativo" value="ativo" name="status"
                ${user.status == 'ativo' ? 'checked' : ''}>
                <label for="status-ativo">Ativo</label>

                <input type="radio" id="status-inativo" value="inativo" name="status"
                ${user.status == 'inativo' ? 'checked' : ''}>
                <label for="status-inativo">Inativo</label>

            </div>
        </div><br>

        <div class="form-content">
            <label for="images">Imagens</label>
            <input type="file" id="images" name="images[]" multiple accept="image/*" />
        </div>

        <div class="form-content">
            <button type="submit">Cadastrar</button>
        </div>

        <script>
            window.onload = function() {
                var mode = document.querySelector("input[name='mode']").value;
                if (mode === 'create') {
                    document.getElementById("form").reset();
                }
            }
        </script>

    </form>
</body>



</html>
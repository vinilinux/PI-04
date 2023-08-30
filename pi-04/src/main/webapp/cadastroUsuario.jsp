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

<form id="form" class="form" action="${empty user.id_user ? '/CreateUserServlet' : '/EditUserServlet'}" method="post">
    <input type="hidden" name="mode" value="${empty param.id_user ? 'create' : 'update'}"/>

        <c:if test="${not empty user.id_user}">
       <input type="hidden" name="id_user" value="${user.id_user}"/>
        </c:if>


        <div class="form-content">
            <label for="username">Nome do usuário</label>
            <input type="text" id="name" name="name" placeholder="Digite seu nome" value="${user.name}" required />
        </div>

        <div class="form-content">
            <label for="cpf">CPF</label>
            <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" value="${user.cpf}" required/>
            <span class="erro-msg"></span>
        </div>

        <div class="form-content">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Digite seu email" value="${user.email}" required/>
        </div>

        <div class="form-content">
            <label for="password">Senha</label>
            <input type="password" id="password" name="password" placeholder="Digite sua senha" value="${user.password}" required />
        </div>

        <div class="form-content">
            <label for="password-confirmation">Confirmação de senha</label>
            <input type="password" id="password-confirmation" name="password-confirmation" placeholder="Digite sua senha novamente" value="${user.password}" required />
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

        <p>Grupo:</p><br>
        <input type="radio" id="grupo-admin" value="administrador" name="grupo"
               ${user.group_user == 'administrador' ? 'checked' : ''}>
        <label for="grupo-admin">Administrador</label>

        <input type="radio" id="grupo-estoq" value="estoquista" name="grupo"
               ${user.group_user == 'estoquista' ? 'checked' : ''}>
        <label for="grupo-estoq">Estoquista</label>



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
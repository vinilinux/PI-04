<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
    <%@ page contentType="text/html; charset=UTF-8" %>

    <title>Login</title>
    <link rel="shortcut icon" href="/Images/LogoTripTaker.ico" type="image/x-icon">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;300;500&display=swap" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/Style/loginUsuario.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</head>

<body>

      <div class="container loginBox">
        <form action="/login" method="post">
            <span>${requestScope.message}</span>
            <span class="error-message">${requestScope.errorMessage}</span>


          <div class="mb-3">
            <label for="email" class="form-label">E-mail</label>
            <input type="text" class="form-control" id="email" name="email" required>
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Senha</label>
            <input type="password" class="form-control" id="password" name="password" required>
          </div>
          <button type="submit" class="btn btn-primary btnEntrar">Entrar</button>
        </form>
      </div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
      document.addEventListener('DOMContentLoaded', function() {
        document.querySelector('button[type="submit"]').addEventListener('click', function() {
          var inputs = document.querySelectorAll('form input[required], form textarea[required]');

          for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].value.trim() === '') {
              inputs[i].classList.add('is-invalid');
            } else {
              inputs[i].classList.remove('is-invalid');
            }
          }
        });
      });
    document.getElementById('arrow-up').addEventListener('click', function() {
      window.scrollTo({ top: 0, behavior: 'smooth' });
    });
    </script>
</body>
</html>

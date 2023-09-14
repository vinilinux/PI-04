<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Detalhes do Produto</title>

  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <script src="JavaScript/visualizarProduto.js"></script>


</head>

<body>

  <div class="container mt-5">
    <div class="row">
      <div class="col-md-6">
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-intervale="false">
          <div class="carousel-inner">

            <c:forEach var="img" items="${product_img}" varStatus="status">
              <c:set var="activeClass" value="${status.index == 0 ? 'active' : ''}" />
              <div class="carousel-item ${activeClass}">
                <img class="d-block w-100" src="${img.image_path}" alt="${img.image_defaulth}">
              </div>
            </c:forEach>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Anterior</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Próximo</span>
          </a>
        </div>
      </div>

      <div class="col-md-6">
        <h3>${product.name}</h3><br>

        <p class="mb-3"><strong>Avaliação</strong></p>

        <div id="estrelas-container"></div>

          <script>
            var rateDouble = ${product.rate};
            var rateInt = Math.floor(rateDouble);
            var decimalPart = rateDouble - rateInt;
            var estrelasContainer = document.getElementById('estrelas-container');
            for (var i = 0; i < rateInt; i++) {
              var img = document.createElement('img');
              img.src = 'Images/icons8-estrela-50.png';
              estrelasContainer.appendChild(img);
            }

            if (decimalPart > 0) {
              var img = document.createElement('img');
              img.src = 'Images/icons8-estrela-meio-vazia-50.png';
              estrelasContainer.appendChild(img);
            }
          </script>
          <br/>
        <p><strong>Descrição do Produto:</strong></p>

        <p>${product.description}</p>

          <h1 class="text-secondary">${product.price}</h1>

          <p><strong>Disponibilidade: Em estoque</p>
          <div class="form-group">
            <label for="quantidade">Quantidade:</label><br>
            <input type="number" class="form-control-light" id="quantidade" value="1">
          </div>
          <button class="btn btn-danger">Comprar</button>
      </div>
    </div>
  </div>
  </div>



</body>

</html>
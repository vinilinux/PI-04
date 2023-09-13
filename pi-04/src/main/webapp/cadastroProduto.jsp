<%--
  Created by IntelliJ IDEA.
  User: vinicius
  Date: 12/09/2023
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastrar Produto</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    .destaque {
      border: 2px solid red;
    }

    .imgthumbnail:hover {
      cursor: pointer;
      opacity: 0.7;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h1>Formulário de Produto</h1>
  <form action="createProduct" method="post" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="productName" class="form-label">Nome do Produto</label>
      <input type="text" class="form-control" id="productName" name="productName" required>
    </div>
    <div class="mb-3">
      <label for="description" class="form-label">Descrição</label>
      <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
    </div>
    <div class="mb-3">
      <label for="rate" class="form-label">Avaliação</label>
      <input type="number" class="form-control" id="rate" name="rate" min="1" max="5" required>
    </div>
    <div class="mb-3">
      <label for="price" class="form-label">Preço</label>
      <input type="number" class="form-control" id="price" name="price" step="0.01" required>
    </div>
    <div class="mb-3">
      <label for="amount" class="form-label">Quantidade</label>
      <input type="number" class="form-control" id="amount" name="amount" required>
    </div>
    <div class="mb-3">
    <label for="images" class="form-label">Selecione imagens</label>
    <input class="form-control" type="file" id="images" name="images[]" accept="image/*" multiple
           onchange="handleFiles(this.files)"/>
  </div>

    <div class="row mt-3" id="thumbnails"></div>
    <input type="hidden" id="selectedImage" name="selectedImage"/>
    <button type="submit" class="btn btn-primary">Enviar</button>
  </form>
</div>

<script>
  function handleFiles(files) {
    const thumbnailsDiv = document.getElementById("thumbnails");

    // Limpa a área de miniaturas
    thumbnailsDiv.innerHTML = "";

    // Loop através das imagens carregadas
    for (let i = 0; i < files.length; i++) {
      const file = files[i];

      const thumbnail = document.createElement("div");
      thumbnail.className = "col-3";

      // Cria um elemento de imagem
      const image = document.createElement("img");
      image.className = "img-thumbnail imgthumbnail";
      image.src = URL.createObjectURL(file);
      console.log(image.className + file.name)
      let acaoAconteceu = false;
      image.onclick = function () {
        image.classList.add("destaque");
        document.getElementById("selectedImage").value = file.name;
      }

      thumbnail.appendChild(image);

      // Adiciona a miniatura à área de miniaturas
      thumbnailsDiv.appendChild(thumbnail);
    }
  }
</script>
</body>
</html>


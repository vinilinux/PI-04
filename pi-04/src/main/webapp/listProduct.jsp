<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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

    <title>Lista de Produtos</title>

    <link rel="stylesheet" href="/Style/listaUsuario.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/inputmask/5.0.6/jquery.inputmask.min.js"></script>
    <script src="/JavaScript/script.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
<input type="text" name="search" id="search" placeholder="Pesquise aqui" required />
<button type="submit">
    <svg xmlns="http://www.w3.org/2000/svg" height="1em"
         viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
        <path
                d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
    </svg>
</button>
<a href="createProduct">
    <button type="button">
        <svg xmlns="http://www.w3.org/2000/svg" height="1em"
             viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
            <path
                    d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z" />
        </svg>
    </button>
</a>
<c:set var="produtosPorPagina" value="10" />
<c:set var="paginaAtual" value="${param.pagina != null ? param.pagina : 1}" />

<%-- Calcula o índice de início e fim da lista de produtos a serem exibidos --%>
<c:set var="startIndex" value="${(paginaAtual - 1) * produtosPorPagina}" />
<c:set var="endIndex" value="${startIndex + produtosPorPagina - 1}" />

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Quantidade</th>
        <th>Valor</th>
        <th>Status</th>
        <th>Alterar</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productsList}" begin="${startIndex}" end="${endIndex}">
        <tr>
            <td>${product.id_product}</td>
            <td>${product.name}</td>
            <td>${product.amount}</td>
            <td>${product.price}</td>
            <td>
                <form id="updateForm${product.id_product}" action="EditProductStockServlet"
                      method="post">
                    <input type="hidden" name="productId" value="${product.id_product}">
                    <select name="status" onchange="confirmAndUpdate(${product.id_product})">
                        <option value="ativo" ${user.status=='ativo' ? 'selected' : '' }>Ativo
                        </option>
                        <option value="inativo" ${user.status=='inativo' ? 'selected' : '' }>Inativo
                        </option>
                    </select>
                    <input type="hidden" name="action" value="updateStatus">
                </form>
            </td>
            <td><a href="EditProductStockServlet?id=${product.id_product}&mode=update">Alterar
                Produto</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%-- Adicione links para navegar entre as páginas --%>
<c:set var="totalProdutos" value="${productsList.size()}" />
<c:set var="totalPaginas" value="${(totalProdutos + produtosPorPagina - 1) / produtosPorPagina}" />

<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${totalPaginas}">
            <li class="page-item ${i == paginaAtual ? 'active' : ''}">
                <a class="page-link" href="?pagina=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
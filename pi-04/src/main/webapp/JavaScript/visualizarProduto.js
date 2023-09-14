function Avaliar(estrela) {
    var url = window.location.href.split("./visualizarProduto.jsp")[0];
    var avaliacao = 0;
  
    for (var i = 1; i <= 5; i++) {
      var starElement = document.getElementById("star" + i);
      var newImageSrc = (i <= estrela) ? "./Images/star1.png" : "./Images/star0.png";
      starElement.src = newImageSrc;
  
      if (newImageSrc === "./Images/star1.png") {
        avaliacao = i;
      }
    }
  
    document.getElementById('rating').innerHTML = avaliacao;
  
  }

var botao = document.getElementById("botao");

botao.disabled = true;
  
  
  
  
  
  
  
  
  
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Motherboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Placas-Mãe</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="app/vendor/scripts/jquery.cookie-1.4.1.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/product.css">
</head>
<body>
  <header class="container-fluid">
    <h1 class="logo">COMPUS</h1>
  </header>
  <nav>
    <ul class="nav-list">
      <li class="nav-item"><a href="processadores">Processador</a></li>
      <li class="nav-item active"><a href="#">Placa-Mãe</a></li>
      <li class="nav-item"><a href="memorias">Memória</a></li>
      <li class="nav-item"><a href="hds">HD</a></li>
      <li class="nav-item"><a href="computadores">Computador</a></li>
      <li class="nav-end-request">
        <a href="carrinho">
          <span class="fa-lg">
            <i class="fa fa-shopping-cart"></i>
          </span>
          <span>Carrinho</span>
        </a>
      </li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Placas-Mãe</span>
    <hr class="content-line" />
    <div class="products">
      <div class="product-row">
        <% List<Motherboard> motherboards = (ArrayList<Motherboard>) request.getAttribute("motherboards"); %>
        <% if(motherboards.isEmpty()) { %>
          <div class="col-sm-12 text-center">
            <span>Não existem placas-mãe cadastradas!</span>
          </div>
        <% } %>
        <% for(Motherboard motherboard: motherboards) { %>
          <div class="product">
            <img src="<%= motherboard.getImageUrl() %>">
            <span class="product-name"><%= motherboard.getName() %></span>
            <span class="product-price">R$ <%= motherboard.getFormattedPrice() %></span>
            <a class="btn btn-primary js-select-button" data-id="<%= motherboard.getId() %>">
              Selecionar
            </a>
          </div>
        <% } %>
      </div>
    </div>
  </div>
  <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
   <div class="modal-dialog" role="document">
     <div class="modal-content">
       <div class="modal-header">
         <h5 class="modal-title" id="messageModalLabel">Mensagem</h5>
         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
           <span aria-hidden="true">&times;</span>
         </button>
       </div>
       <div class="modal-body">
         Placa-mãe adicionada ao carrinho com sucesso!
       </div>
       <div class="modal-footer">
         <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
       </div>
     </div>
   </div>
  </div>
  <script type="text/javascript">
  $(".js-select-button").click(function() {
    var id = $(this).data("id");
    var motherboard_raw_cookie = $.cookie("motherboard_ids");
    if(motherboard_raw_cookie) {
      var motherboard_cookies = motherboard_raw_cookie.split(';');
      if($.inArray(id.toString(), motherboard_cookies) == -1) {
        $.cookie("motherboard_ids", $.cookie("motherboard_ids") + ";" + id);
      }
    }
    else {
      $.cookie("motherboard_ids", id);
    }
    $('#messageModal').modal({show: true});
  });
  </script>
</body>
</html>
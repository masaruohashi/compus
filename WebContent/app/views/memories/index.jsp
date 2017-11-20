<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Memory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Memórias</title>
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
      <li class="nav-item"><a href="placas-mae">Placa-Mãe</a></li>
      <li class="nav-item active"><a href="#">Memória</a></li>
      <li class="nav-item"><a href="hds">HD</a></li>
      <li class="nav-item"><a href="#">Computador</a></li>
      <li class="nav-end-request">
        <a href="cart.html">
          <span class="fa-lg">
            <i class="fa fa-shopping-cart"></i>
          </span>
          <span>Carrinho</span>
        </a>
      </li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Memórias</span>
    <hr class="content-line" />
    <div class="products">
      <div class="product-row">
        <% List<Memory> memories = (ArrayList<Memory>) request.getAttribute("memories"); %>
        <% if(memories.isEmpty()) { %>
          <div class="col-sm-12 text-center">
            <span>Não existem memórias cadastradas!</span>
          </div>
        <% } %>
        <% for(Memory memory: memories) { %>
          <div class="product">
            <img src="app/assets/images/memory.jpg">
            <span class="product-name"><%= memory.getName() %></span>
            <span class="product-price">R$ <%= memory.getFormatedPrice() %></span>
            <a class="btn btn-primary js-select-button" data-id="<%= memory.getId() %>">
              Selecionar
            </a>
          </div>
        <% } %>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  $(".js-select-button").click(function() {
    var id = $(this).data("id");
    var memory_raw_cookie = $.cookie("memory_ids");
    if(memory_raw_cookie) {
      var memory_cookies = memory_raw_cookie.split(';');
      if($.inArray(id.toString(), memory_cookies) == -1) {
        $.cookie("memory_ids", $.cookie("memory_ids") + ";" + id);
      }
    }
    else {
      $.cookie("memory_ids", id);
    }
  });
  </script>
</body>
</html>
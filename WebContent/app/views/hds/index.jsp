<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Hd"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - HDs</title>
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
      <li class="nav-item"><a href="memorias">Memória</a></li>
      <li class="nav-item active"><a href="#">HD</a></li>
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
    <span class="content-header">HDs</span>
    <hr class="content-line" />
    <div class="products">
      <div class="product-row">
        <% List<Hd> hds = (ArrayList<Hd>) request.getAttribute("hds"); %>
        <% if(hds.isEmpty()) { %>
          <div class="col-sm-12 text-center">
            <span>Não existem hds cadastrados!</span>
          </div>
        <% } %>
        <% for(Hd hd: hds) { %>
          <div class="product">
            <img src="app/assets/images/hd.jpg">
            <span class="product-name"><%= hd.getName() %></span>
            <span class="product-price">R$ <%= hd.getFormatedPrice() %></span>
            <a class="btn btn-primary js-select-button" data-id="<%= hd.getId() %>">
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
    var hd_raw_cookie = $.cookie("hd_ids");
    if(hd_raw_cookie) {
      var hd_cookies = hd_raw_cookie.split(';');
      if($.inArray(id.toString(), hd_cookies) == -1) {
        $.cookie("hd_ids", $.cookie("hd_ids") + ";" + id);
      }
    }
    else {
      $.cookie("hd_ids", id);
    }
  });
  </script>
</body>
</html>
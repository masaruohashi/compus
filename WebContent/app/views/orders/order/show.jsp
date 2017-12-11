<%@page import="br.com.compus.models.OrderItem"%>
<%@page import="br.com.compus.models.Product"%>
<%@page import="br.com.compus.decorators.OrderDecorator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Pedidos</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/admin_panel.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/user_list.css">
</head>
<body>
  <header class="container-fluid">
    <div class="header-row row">
      <div class="col-sm-7 pull-left">
        <h1 class="logo">COMPUS</h1>
      </div>
      <div class="col-sm-5">
        <span class="current-username pull-right">username</h3>
        <a href="index.html">
          <span class="fa-stack fa-lg">
            <i class="fa fa-circle-thin fa-stack-2x"></i>
            <i class="fa fa-sign-out fa-stack-1x"></i>
          </span>
        </a>
      </div>
    </div>
  </header>
  <div class="container-fluid page-body">
    <div class="row">
      <nav class="col-sm-2">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="funcionario"><span>Funcionários</span></a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="cliente"><span>Clientes</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><span>Relatório Individual</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="pedidos"><span>Pedidos</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <div class="row">
          <div class="col-sm-10">
            <span class="h2">Detalhes do Pedido</span>
          </div>
        </div>
        <hr>
        <% OrderDecorator order = (OrderDecorator) request.getAttribute("order"); %>
        <% if(order.items() == null) { %>
          <div class="content-table text-center">
            <span class="h4">Não existem produtos no pedido!</span>
          </div>
        <% } else { %>
          <div class="content-table">
            <table class="table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Cliente</th>
                    <th>Vendedor</th>
                    <th>Data</th>
                    <th>Preço</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td><%= order.id() %></td>
                    <td><%= order.client() %></td>
                    <td><%= order.seller() %></td>
                    <td><%= order.date() %></td>
                    <td>R$ <%= order.finalPrice() %></td>
                  </tr>
                </tbody>
            </table>
          </div>
          <div class="content-table">
            <table class="table">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>Preço</th>
                  <th>Quantidade</th>
                </tr>
              </thead>
              <tbody>
                <% for(OrderItem item: order.items()) { %>
                  <tr>
                    <td><%= item.getProduct().getName() %></td>
                    <td>R$ <%= item.getProduct().getFormattedPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                  </tr>
                <% } %>
              </tbody>
            </table>
          </div>
        <% } %>
      </div>
    </div>
  </div>
</body>
</html>
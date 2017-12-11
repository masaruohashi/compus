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
            <a class="nav-link active" href="#"><span>Pedidos</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <div class="row">
          <div class="col-sm-10">
            <span class="h2">Listagem de Pedidos</span>
          </div>
        </div>
        <hr>
        <div class="content-table">
          <% List<OrderDecorator> orders = (ArrayList<OrderDecorator>) request.getAttribute("orders"); %>
          <% if(orders.isEmpty()) { %>
            <div class="col-sm-12 text-center">
              <span class="h4">Não existem pedidos cadastrados!</span>
            </div>
          <% } else { %>
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
                <% for(OrderDecorator order: orders) { %>
                  <tr>
                    <td><a href="pedidos?id=<%= order.id() %>"><%= order.id() %></a></td>
                    <td><%= order.client() %></td>
                    <td><%= order.seller() %></td>
                    <td><%= order.date() %></td>
                    <td>R$ <%= order.finalPrice() %></td>
                  </tr>
                <% } %>
              </tbody>
            </table>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
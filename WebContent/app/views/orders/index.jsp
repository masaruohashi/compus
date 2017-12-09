<%@page import="br.com.compus.models.Computer"%>
<%@page import="br.com.compus.models.Memory"%>
<%@page import="br.com.compus.models.Hd"%>
<%@page import="br.com.compus.models.Cpu"%>
<%@page import="br.com.compus.models.Motherboard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Finalizar Pedido</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
</head>
<body>
  <header>
    <h1 class="logo">COMPUS</h1>
  </header>
  <nav>
    <ul class="nav-list">
      <li class="nav-item"><a href="processadores">Processador</a></li>
      <li class="nav-item"><a href="placas-mae">Placa-Mãe</a></li>
      <li class="nav-item"><a href="memorias">Memória</a></li>
      <li class="nav-item"><a href="hds">HD</a></li>
      <li class="nav-item"><a href="computadores">Computador</a></li>
      <li class="nav-end-request">
        <a href="#">
          <span class="fa-lg">
            <i class="fa fa-shopping-cart"></i>
          </span>
          <span>Carrinho</span>
        </a>
      </li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Resumo do Pedido</span>
    <hr class="content-line" />
    <% List<Motherboard> motherboards = (ArrayList<Motherboard>) request.getAttribute("motherboards"); %>
    <% List<Cpu> cpus = (ArrayList<Cpu>) request.getAttribute("cpus"); %>
    <% List<Memory> memories = (ArrayList<Memory>) request.getAttribute("memories"); %>
    <% List<Hd> hds = (ArrayList<Hd>) request.getAttribute("hds"); %>
    <% List<Computer> computers = (ArrayList<Computer>) request.getAttribute("computers"); %>
    <% if(motherboards.isEmpty() && cpus.isEmpty() && memories.isEmpty() && hds.isEmpty() && computers.isEmpty()) { %>
      <div class="col-sm-12 text-center">
        <span>Nenhum produto foi adicionado.</span>
      </div>
    <% } else { %>
      <form action="carrinho" method="post" >
        <table class="table">
          <thead>
            <tr>
              <th>Nome do Produto</th>
              <th class="col-sm-2">Quantidade</th>
              <th>Preço</th>
            </tr>
          </thead>
          <tbody>
            <% for(Motherboard motherboard : motherboards) { %>
              <tr>
                <td><%= motherboard.getName() %></td>
                <td><input class="form-control" type="number" value="1" min="1" name="motherboard_<%= motherboard.getId() %>" /></td>
                <td>R$ <%= motherboard.getFormattedPrice() %></td>
              </tr>
            <% } %>
            <% for(Cpu cpu : cpus) { %>
              <tr>
                <td><%= cpu.getName() %></td>
                <td><input class="form-control" type="number" value="1" min="1" name="cpu_<%= cpu.getId() %>" /></td>
                <td>R$ <%= cpu.getFormattedPrice() %></td>
              </tr>
            <% } %>
            <% for(Hd hd : hds) { %>
              <tr>
                <td><%= hd.getName() %></td>
                <td><input class="form-control" type="number" value="1" min="1" name="hd_<%= hd.getId() %>" /></td>
                <td>R$ <%= hd.getFormattedPrice() %></td>
              </tr>
            <% } %>
            <% for(Memory memory : memories) { %>
              <tr>
                <td><%= memory.getName() %></td>
                <td><input class="form-control" type="number" value="1" min="1" name="memory_<%= memory.getId() %>" /></td>
                <td>R$ <%= memory.getFormattedPrice() %></td>
              </tr>
            <% } %>
            <% for(int i = 0; i < computers.size(); i++) { %>
              <% Computer computer = computers.get(i); %>
              <tr>
                <td><%= computer.getName() %></td>
                <td><input class="form-control" type="number" value="1" min="1" name="computer_<%= i %>" /></td>
                <td>R$ <%= computer.getFormattedPrice() %></td>
              </tr>
            <% } %>
          </tbody>
        </table>
        <div class="row">
          <div class="col-sm-12">
            <div class="pull-right">
              <a href="#" class="btn btn-warning">Cancelar</a>
              <input type="submit" class="btn btn-primary" value="Finalizar" />
            </div>
          </div>
        </div>
      </form>
    <% } %>
  </div>
</body>
</html>

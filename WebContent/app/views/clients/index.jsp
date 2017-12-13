<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="br.com.compus.models.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Usuários</title>
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
      <span class="current-username pull-right"><%=session.getAttribute("admin_name") %></h3>
        <a href="logout">
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
            <a class="nav-link active" href="cliente"><span>Clientes</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="relatorio/geral"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="relatorio/individual"><span>Relatório Individual</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="pedidos"><span>Pedidos</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <div class="row">
          <div class="col-sm-10">
          <span class="h2">Listagem de Clientes</span>
          </div>
          <div clas="col-sm-2">
          <a class="btn btn-primary pull-right add-button" href="cliente/novo">
            <i class="fa fa-user-plus" aria-hidden="true"></i>
            <span>Adicionar</span>
          </a>
          </div>
        </div>
        <hr>
        <div class="content-table">
          <table class="table">
            <thead>
            <tr>
              <th>Nome</th>
              <th>CPF</th>
              <th>E-mail</th>
              <th>Endereço</th>
              <th>Telefone</th>
              <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <% List<Client> clients = (ArrayList<Client>) request.getAttribute("clients"); %>
            <% for(Client client: clients) { %>
            <tr>
              <td><%= client.getName() %></td>
              <td><%= client.getCpf() %></td>
              <td><%= client.getEmail() %></td>
              <td><%= client.getAddress() %></td>
              <td><%= client.getPhone() %></td>
              <td>
                <a href="cliente/editar?id=<%=client.getId() %>" class="btn btn-primary">Editar</a>
                <a href="cliente/deletar?id=<%=client.getId() %>" class="btn btn-danger">Excluir</a>
              </td>
            </tr>
            <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <% if(request.getParameter("msg") != null) { %>
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
          <%= request.getParameter("msg") %>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
        </div>
      </div>
    </div>
  </div>
  <script>$('#messageModal').modal({show: true})</script>
  <% } %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="br.com.compus.models.Employee"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Editar</title>
  <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../bower_components/jquery/dist/jquery.mask.min.js"></script>
  <script src="../bower_components/jquery/dist/masks.js"></script>
  <script src="../bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="../bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/admin_panel.css">
  <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/registration.css">
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
          <a class="nav-link active" href="../funcionario"><span>Funcionários</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="../cliente"><span>Clientes</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="../relatorio/geral"><span>Relatório Geral</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="../relatorio/individual"><span>Relatório Individual</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="../pedidos"><span>Pedidos</span></a>
        </li>
      </ul>
    </nav>
    <div class="content col-sm-10">
      <span class="h2">Edição de Funcionário</span>
      <%Employee employee = (Employee) request.getAttribute("employee"); %>
      <hr>
      <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
          <form class="user-form" method="post">
            <div class="row">
              <div class="col-sm-3">
                <label>Nome: </label>
              </div>
              <div class="col-sm-9">
                <input class="user-input pull-right" type="text" name="name" value="<%=employee.getName() %>" />
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <label>CPF: </label>
              </div>
              <div class="col-sm-9">
                <input class="cpf user-input pull-right" type="text" name="cpf" value="<%=employee.getCpf() %>" />
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <label>Email: </label>
              </div>
              <div class="col-sm-9">
                <input class="user-input pull-right" type="text" name="email" value="<%=employee.getEmail() %>" />
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <label>Endereço: </label>
              </div>
              <div class="col-sm-9">
                <input class="user-input pull-right" type="text" name="address" value="<%=employee.getAddress() %>" />
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <label>Telefone: </label>
              </div>
              <div class="col-sm-9">
                <input class="phone user-input pull-right" type="text" name="phone" value="<%=employee.getPhone() %>" />
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <label>Função: </label>
              </div>
              <div class="col-sm-9">
                <select class="user-input pull-right" id="role" name="role">
                  <%for (String role: Employee.ACCEPTED_ROLES) {%>
                  <option value="<%=role %>" <%if (employee.getRole().matches(role)) {%> selected <%} %>><%=role %></option>
                  <%} %>
                </select>
              </div>
            </div>
            <div class="row .hidden" id="password">
              <div class="col-sm-3">
                <label>Senha: </label>
              </div>
              <div class="col-sm-9">
                <input class="user-input pull-right" type="password" name="password" />
              </div>
            </div>
            <div class="row form-buttons">
              <div class="col-sm-5 col-sm-offset-7">
                <div class="row">
                  <div class="col-sm-6">
                    <a href="${pageContext.request.contextPath }/funcionario" class="form-button btn btn-warning pull-right">Cancelar</a>
                  </div>
                  <div class="col-sm-6">
                    <button type="submit" class="form-button btn btn-primary pull-right">Enviar</button>
                  </div>
                </div>
              </div>
            </div>
          </form>
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
<script>
$(document).ready(function() {
  if ($('#role').val()==="administrador"){
    $("#password").show()
  }
  else{
    $("#password").hide()
  }

  $('#role').on('change',function(){
    if( $(this).val()==="administrador"){
      $("#password").show()
    }
    else{
      $("#password").hide()
    }
  });
});
</script>
</body>
</html>

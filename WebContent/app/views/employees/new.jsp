<%@ page import="br.com.compus.models.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Compus - Cadastro</title>
  <script type="text/javascript" src="../../../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../../../bower_components/jquery/dist/jquery.mask.min.js"></script>
  <script src="../../../bower_components/jquery/dist/masks.js"></script>
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
            <a class="nav-link" href="../funcionario"><span>Listagem de Funcionários</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="../funcionario/novo"><span>Cadastro de Funcionário</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="../cliente"><span>Listagem de Clientes</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="../cliente/novo"><span>Cadastro de Cliente</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="report.html"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><span>Relatório Individual</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <span class="h2">Cadastro de Funcionário</span>
        <hr>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3">
            <form class="user-form" action="novo" method="post">
              <div class="row">
                <div class="col-sm-3">
                  <label>Nome: </label>
                </div>
                <div class="col-sm-9">
                  <% if(request.getParameter("name") != null) { %>
                    <input class="user-input pull-right" type="text" name="name" value="<%=request.getParameter("name")%>"/>
                  <% }
                  else { %>
                    <input class="user-input pull-right" type="text" name="name" />
                  <% } %>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>CPF: </label>
                </div>
                <div class="col-sm-9">
                  <% if(request.getParameter("cpf") != null) { %>
                    <input class="cpf user-input pull-right" type="text" name="cpf" value="<%=request.getParameter("cpf")%>"/>
                  <% }
                  else { %>
                    <input class="cpf user-input pull-right" type="text" name="cpf"/>
                  <% } %>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Email: </label>
                </div>
                <div class="col-sm-9">
                  <% if(request.getParameter("email") != null) { %>
                    <input class="user-input pull-right" type="text" name="email" value="<%=request.getParameter("email")%>"/>
                  <% }
                  else { %>
                    <input class="user-input pull-right" type="text" name="email" />
                  <% } %>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Função: </label>
                </div>
                <div class="col-sm-9">
                  <select class="user-input pull-right" name="role">
                    <%for (String role: Employee.ACCEPTED_ROLES) { %>
                      <% if (request.getParameter("role") != null) { %>
                        <option value=<%=role%> <%if (request.getParameter("role").matches(role)) { %> selected <% } %> > <%=role %> </option>
                      <% }
                         else { %>
                        <option value=<%=role%> > <%=role %> </option>
                      <% }
                    }   %>
                  </select>
                </div>
              </div>
              <div class="row form-buttons">
                <div class="col-sm-5 col-sm-offset-7">
                  <div class="row">
                    <div class="col-sm-6">
                      <a href="${pageContext.request.contextPath }/funcionario" class="form-button btn btn-warning pull-right">Cancelar</a>
                    </div>
                    <div class="col-sm-6">
                      <input type="submit" value="Enviar" class="form-button btn btn-primary pull-right" />
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
</body>
</html>
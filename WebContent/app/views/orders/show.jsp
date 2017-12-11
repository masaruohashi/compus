<%@page import="br.com.compus.models.OrderItem"%>
<%@page import="br.com.compus.models.Order"%>
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
    <% Order order = (Order) request.getAttribute("order"); %>
    <% if(order.isEmpty()) { %>
      <div class="col-sm-12 text-center">
        <span>Nenhum produto foi adicionado.</span>
      </div>
    <% } else { %>
      <form action="pedido/novo" method="POST">
        <table class="table">
          <thead>
            <tr>
              <th>Nome do Produto</th>
              <th class="col-sm-2">Quantidade</th>
              <th class="col-sm-2">Preço</th>
            </tr>
          </thead>
          <tbody>
            <% for(OrderItem item : order.getItems()) { %>
              <tr>
                <td><%= item.getProduct().getName() %></td>
                <td class="text-center"><%= item.getQuantity() %></td>
                <td>R$ <%= item.getProduct().getFormattedPrice() %></td>
              </tr>
            <% } %>
          </tbody>
        </table>
        <div class="col-sm-offset-3 col-sm-6">
          <table class="table">
            <tr>
              <td><span class="h5">Preço Total:</span></td>
              <td><span>R$ <%= request.getAttribute("total_price") %></span></td>
            </tr>
            <tr>
              <td><span class="h5">Desconto:</span></td>
              <td><span>R$ <%= request.getAttribute("discount") %></span></td>
            </tr>
            <tr>
              <td><span class="h4"><b>Preço Final:</b></span></td>
              <td><span><b>R$ <%= request.getAttribute("final_price") %></b></span></td>
            </tr>
          </table>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="pull-right">
              <a href="sair" class="btn btn-warning">Cancelar</a>
              <input type="submit" class="btn btn-primary js-submit-button" value="Finalizar" />
            </div>
          </div>
        </div>
      </form>
    <% } %>
  </div>
  <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="messageModalLabel">Status</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="text-center" id="modal-text">
            <i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
            <span class="sr-only">Enviando...</span>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
        </div>
      </div>
    </div>
  </div>
  <script>
  $(document).ready(function() {
    $(".js-submit-button").click(function(event){
      $('#messageModal').modal({show: true})
      event.preventDefault();
      $.ajax({
        url: "pedido/novo",
        method: "POST",
        data: {
          <% for(OrderItem item : order.getItems()) { %>
            <%= item.getProduct().productType() %>_<%= item.getProduct().getId() %>: <%= item.getQuantity() %>,
          <% } %>
        },
        timeout: 3000,
        success: function(responseData) {
          $("#modal-text").text(responseData.message);
          $("#messageModal").on('hide.bs.modal', function () {
            window.location="identificacao";
          });
        },
        error: function(jqXHR) {
          $("#modal-text").text("Falha ao enviar o computador.");
        },
        complete: function() {
          $('#messageModal').modal({show: true});
        }
      });
    });
  });
  </script>
</body>
</html>

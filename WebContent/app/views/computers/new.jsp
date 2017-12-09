<%@page import="br.com.compus.models.Hd"%>
<%@page import="br.com.compus.models.Memory"%>
<%@page import="br.com.compus.models.Cpu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Motherboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Computadores</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
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
      <li class="nav-item"><a href="hds">HD</a></li>
      <li class="nav-item active"><a href="#">Computador</a></li>
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
    <span class="content-header">Montagem de Computador</span>
    <hr class="content-line" />
    <div class="products">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <% List<Motherboard> motherboards = (ArrayList<Motherboard>) request.getAttribute("motherboards"); %>
          <% List<Cpu> cpus = (ArrayList<Cpu>) request.getAttribute("cpus"); %>
          <% List<Memory> memories = (ArrayList<Memory>) request.getAttribute("memories"); %>
          <% List<Hd> hds = (ArrayList<Hd>) request.getAttribute("hds"); %>
          <% if(motherboards.isEmpty() || cpus.isEmpty() || memories.isEmpty() || hds.isEmpty()) { %>
            <div class="col-sm-12 text-center">
              <span>Não existem peças suficientes para a montagem!</span>
            </div>
          <% } else { %>
            <form>
              <div class="form-group">
                <label for="motherboard">Placa-Mãe:</label>
                <select class="form-control" id="motherboard" name="motherboard">
                  <% for(Motherboard motherboard : motherboards) { %>
                    <option value="<%= motherboard.getId() %>">
                      <%= motherboard.getName() %> - R$<%=motherboard.getFormattedPrice()%>
                    </option>
                  <% } %>
                </select>
                <br />
                <label for="cpu">Processador:</label>
                <select class="form-control" id="cpu" name="cpu">
                  <% for(Cpu cpu : cpus) { %>
                    <option value="<%= cpu.getId() %>">
                      <%= cpu.getName() %> - R$<%= cpu.getFormattedPrice() %>
                    </option>
                  <% } %>
                </select>
                <br />
                <div class="row">
                  <div class="col-sm-9">
                    <label for="memory">Memória:</label>
                    <select class="form-control col-sm-6" id="memory" name="memory">
                      <% for(Memory memory : memories) { %>
                        <option value="<%= memory.getId() %>">
                          <%= memory.getName() %> - R$<%= memory.getFormattedPrice() %>
                        </option>
                      <% } %>
                    </select>
                  </div>
                  <div class="col-sm-3">
                    <label for="memory_quantity">Quantidade:</label>
                    <input type="number" class="form-control" id="memory_quantity" min="1" value="1" name="memory_quantity" />
                  </div>
                </div>
                <br />
                <div class="row">
                  <div class="col-sm-9">
                    <label for="hd">HD:</label>
                    <select class="form-control" id="hd">
                      <% for(Hd hd : hds) { %>
                        <option value="<%= hd.getId() %>">
                          <%= hd.getName() %> - R$<%= hd.getFormattedPrice() %>
                        </option>
                      <% } %>
                    </select>
                  </div>
                  <div class="col-sm-3">
                    <label for="hd_quantity">Quantidade:</label>
                    <input type="number" class="form-control" id="hd_quantity" min="1" value="1" name="hd_quantity" />
                  </div>
                </div>
              </div>
              <div class="row">
                <span class="col-sm-12 text-muted">OBS: Serão adicionados R$300,00 referentes à montagem.</span>
              </div>
              <input type="submit" id="submit" class="btn btn-primary pull-right" value="Enviar" />
            </form>
          <% } %>
        </div>
      </div>
    </div>
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
    $("#submit").click(function(event){
      $('#messageModal').modal({show: true})
      event.preventDefault();
      $.ajax({
        url: "computadores",
        method: "POST",
        data: {
          motherboard: $("#motherboard").val(),
          cpu: $("#cpu").val(),
          hd: $("#hd").val(),
          memory: $("#memory").val(),
          hd_quantity: $("#hd_quantity").val(),
          memory_quantity: $("#memory_quantity").val()
        },
        timeout: 3000,
        success: function(responseData) {
          $("#modal-text").text(responseData.message);
        },
        error: function(jqXHR) {
          $("#modal-text").text("Falha ao enviar o computador.");
        },
        complete: function() {
          $('#messageModal').modal({show: true});
        }
      });
    });
  </script>
</body>
</html>
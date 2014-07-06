<%@ page language="java" contentType="text/html" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html"  import="com.encuesta.model.Acceso"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${session_ruta}images/favicon.png">

    <title>${pageContext.request.contextPath}</title>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,400italic,700,800' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:100' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700' rel='stylesheet' type='text/css'>
  

    <!-- Bootstrap core CSS -->
    <link href="${session_ruta}js/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" href="${session_ruta}fonts/font-awesome-4/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->
	    <link rel="stylesheet" type="text/css" href="${session_ruta}js/jquery.gritter/css/jquery.gritter.css" />

  <link rel="stylesheet" type="text/css" href="${session_ruta}js/jquery.nanoscroller/nanoscroller.css" />
  <link rel="stylesheet" type="text/css" href="${session_ruta}js/jquery.easypiechart/jquery.easy-pie-chart.css" />
	<link rel="stylesheet" type="text/css" href="${session_ruta}js/bootstrap.switch/bootstrap-switch.css" />
	<link rel="stylesheet" type="text/css" href="${session_ruta}js/bootstrap.datetimepicker/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" type="text/css" href="${session_ruta}js/jquery.select2/select2.css" />
	<link rel="stylesheet" type="text/css" href="${session_ruta}js/bootstrap.slider/css/slider.css" />
	<link rel="stylesheet" type="text/css" href="${session_ruta}js/intro.js/introjs.css" />
  <!-- Custom styles for this template -->
  <link href="${session_ruta}css/style.css" rel="stylesheet" />

</head>
<body>

  <!-- Fixed navbar -->
  <div id="head-nav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="fa fa-gear"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio.html"><span>${pageContext.request.contextPath}</span></a>
      </div>
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li class="active"><a href="${pageContext.request.contextPath}/inicio.html">Inicio</a></li>
        </ul>
    <ul class="nav navbar-nav navbar-right user-nav">
      <li class="dropdown profile_menu">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img alt="Avatar" src="${session_ruta}images/avatar2.jpg" /><span>${session_usuario.userName}</span> <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="${pageContext.request.contextPath}/des_logueo.html">Sign Out</a></li>
        </ul>
      </li>
    </ul>			
    <ul class="nav navbar-nav navbar-right not-nav" >
      <li class="button dropdown">
        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class=" fa fa-comments"></i></a>
      </li>
      <li class="button dropdown">
      <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-globe"></i></a>
      </li>
      <li class="button"><a href="javascript:;" class="speech-button"><i class="fa fa-microphone"></i></a></li>				
    </ul>

      </div><!--/.nav-collapse animate-collapse -->
    </div>
  </div>

	<div id="cl-wrapper" class="fixed-menu">
		<div class="cl-sidebar" data-position="right" data-step="1" data-intro="<strong>Fixed Sidebar</strong> <br/> It adjust to your needs." >
			<div class="cl-toggle"><i class="fa fa-bars"></i></div>
			<div class="cl-navblock">
        <div class="menu-space">
          <div class="content">
            <div class="side-user">
              <div class="avatar"><img src="${session_ruta}images/avatar1_50.jpg" alt="Avatar" /></div>
              <div class="info">
                <a href="#">${session_usuario.userName}</a>
                <img src="${session_ruta}images/state_online.png" alt="Status" /> <span>Online</span>
              </div>
            </div>
            <ul class="cl-vnavigation">
            <%! List<Acceso>  lista=null; %>
            		<%
            		lista=(List<Acceso>)session.getAttribute("usuario_accesos");
            		if(lista!=null){ 
    			   		for(int i=0,t=lista.size();i<t;i++){ 
    			   			if(lista.get(i).getMenu().getTipoMenu()==1 && lista.get(i).getMenu().getMasterMenu()==0){ %>
    			   			<li><a href="${pageContext.request.contextPath}/<%=lista.get(i).getMenu().getUrlMenu()%>"><i class="<%=lista.get(i).getMenu().getIconoMenu()%>"></i><span><%=lista.get(i).getMenu().getNomMenu()%></span></a></li>
    			   			<% }else if(lista.get(i).getMenu().getMasterMenu()==1){ 
					   				if( lista.get(i).getMenu().getTipoMenu()==1 ){%> 
					   					<li><a href="#"><i class="<%=lista.get(i).getMenu().getIconoMenu()%>"></i><span><%=lista.get(i).getMenu().getNomMenu()%></span></a>
	               						 <ul class="sub-menu">
					   					
					   				<%}else{ %> 
					   					<li class="active"><a href="${pageContext.request.contextPath}/<%=lista.get(i).getMenu().getUrlMenu()%>"><%=lista.get(i).getMenu().getNomMenu()%></a></li> 
					   			   <% } 
					   				if(lista.size()==i+1){ %>
					   					</ul>
					   					</li>
					   				<%}else{
					   					if(lista.get(i+1).getMenu().getTipoMenu()==1){ %>
					   					</ul>
					   					</li>
					   					<%}
					   					
					   				}
    			   			}//if
    			   		}//for
            		}//if null
            		%>  
            </ul>
          </div>
        </div>
        <div class="text-right collapse-button" style="padding:7px 9px;">
          <input type="text" class="form-control search" placeholder="Search..." />
          <button id="sidebar-collapse" class="btn btn-default" style=""><i style="color:#fff;" class="fa fa-angle-left"></i></button>
        </div>
			</div>
		</div>

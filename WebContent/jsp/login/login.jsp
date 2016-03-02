<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
<title><fmt:message key="option.login" /></title>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body id="page1">
<div class="main">
<header>
<div class="wrapper">
<h1>
<a href="index.jsp" id="logo"></a>
</h1>
<span id="slogan"><fmt:message key="header.haliy" /></span>
</div>
</header>
			<div align="center">
				<article style="width: 80%; margin-top: 2px;">
					<div class="tabs">
						<div class="box1">
							<div class="notes">
								<fmt:message key="lable.reg" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" name="loginForm" method="POST"
									action="controller">
									<input type="hidden" name="command" value="login" />
									<div>
										<div style="margin-top: 20px;">
											<span class="left"><fmt:message key="option.nick" /></span>
											<input type="text" name="login" value="" class="input" />
										</div>
										<div style="margin-top: 2px; ">
											<span class="left"><fmt:message key="option.pass" /></span>
											<input type="password" name="password" value="" class="input" />
										</div>
										<div class="wrapper" align="center">
											<span> <input type="submit"
												value="<fmt:message key="option.login" />" class="button1" />
											</span>
										</div>
										<div class="wrapper"
											style="text-align: center; margin-top: 10px;">
											<c:if test="${errorLoginPassMessage != null }">
												<fmt:message key="${ errorLoginPassMessage }" />
											</c:if>
											<c:if test="${ wrongAction != null }">
												<fmt:message key="${ wrongAction }" />
											</c:if>											
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</article>
			</div>
		<footer>
			<div class="wrapper" align="center" style="margin-top: 2px;">
				<div class="links">
					<fmt:message key="contact.email" />
				</div>
			</div>
		</footer>
	</div>
</body>
</html>
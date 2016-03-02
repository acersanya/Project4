<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://air.com/mytags" prefix="mytags"%>
<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
<title><fmt:message key="label.dispatcher" /></title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body id="page1">
	<div class="main">
		<header>
			<div class="wrapper">
				<h1>
					<a href="index.jsp" id="logo"></a>
				</h1>
				<span id="slogan"><fmt:message key="header.haliy" /></span>
					<table style="margin-top: 10px; margin-right: 10px;" align="right">
					<tr>
					<td style="width: 90px;"><p
								style="line-height: 20px; padding: 0; margin-bottom: 20px;">
								${user}
								</p></td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="logout" />
								<input class="button1" type="submit" value=<fmt:message key="option.logout" /> />
							</form>
						</td>
					</tr>
				</table>
				<mytags:currentTime/>
			</div>
		</header>
					<div class="box1" style="margin-top: 0px;">
						<div class="notes">
							<fmt:message key="flight.flights" />
						</div>		
						<div>
							<table class="table" style="width: 100%;">
								<tr>
									<td><fmt:message key="flight.flight" /></td>
									<td><fmt:message key="flight.to" /></td>
									<td><fmt:message key="flight.from" /></td>
									<td><fmt:message key="flight.date" /></td>
									<td><fmt:message key="flight.action" /></td>			
								</tr>
								<c:forEach var="flight" items="${flights}">
									<c:set var="status" value="${ flight.status}" />
									<c:if test="${ status == 0}">
										<tr>
											<td><c:out value="${ flight.id }" /></td>
											<td><c:out value="${ flight.to }" /></td>
											<td><c:out value="${ flight.from }" /></td>
											<td><c:out value="${ flight.date }" /></td>
											<td><form style="margin-bottom: 0;" name="teamForm"
													method="POST" action="controller">
													<input type="hidden" name="command" value="team" /> <input
														type="hidden" name="flight" value="${ flight.id }" /> <input
														class="button1" type="submit"
														value=<fmt:message key="option.team" /> />
												</form></td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
							<span> <c:if test="${ teamFormed != null }">
									<fmt:message key="${ teamFormed }" />
								</c:if> <c:if test="${ teamNotFormed != null }">
									<fmt:message key="${ teamNotFormed }" />
								</c:if>
							</span>
						</div>	
					</div>
		<footer>
			<div class="wrapper" align="center" style="margin-top: 10px;"> 
				<div class="links">
					<fmt:message key="contact.email" />
				</div>
			</div>
		</footer>
	</div>
</body>
</html>
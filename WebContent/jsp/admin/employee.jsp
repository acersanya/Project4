<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
<title><fmt:message key="label.admin" /></title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body id="page1">
	<div class="main">
		<!--header -->
		<header>
			<div class="wrapper">
				<h1>
					<a href="index.jsp" id="logo"></a>
				</h1>
				<span id="slogan"><fmt:message key="header.haliy" /></span>
					<table style="margin-top: 10px; margin-right: 10px;" align="right">
					<tr>
					<td style="width: 90px;">
					<p style="line-height: 20px; padding: 0; margin-bottom: 20px;">
								${user}
						</p></td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="logout" /><input
									class="button1" type="submit"
									value=<fmt:message key="option.logout" /> />
							</form>
						</td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="backToAdmin" /><input
									class="button1" type="submit"
									value=<fmt:message key="back.flights" /> />
							</form>
						</td>
					</tr>
				</table>
			</div>
		</header>
			<div >
					<div class="tabs">
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<fmt:message key="employee.modify" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="changeEmployee" />	
									<div align="center" style="margin-top: 20px;">
									<span class="center"><fmt:message key="position.name" />
												: 
												</span> <select class="input" name="modifiedPosition">
												<c:forEach var="pos" items="${positions}">
													<option value="${ pos }">${ pos }</option>
												</c:forEach>
											</select>
											<span class="center"><fmt:message key="employee.name" />
												: 
												</span> 
											<input required="required" type="text" name="modifiedName" value="${ employeeToModify.name }" class="input" />
											<span class="center"><fmt:message key="employee.surname" />
												: 
												</span> 
												<input required="required" type="text" name="modifiedSurname" value="${ employeeToModify.surname }" class="input" />
										<div class="wrapper" style="margin-top: 10px;">
											<span class="center"> <input type="submit"
												value=<fmt:message key="employee.modify" /> class="button1" />
											</span>
										</div>
										<span class="center"> <c:if test="${ employeeWasntModified != null }">
												<fmt:message key="${ employeeWasntModified }" />
											</c:if>
										</span>
									</div>
								</form>
								<div class="wrapper" align="center">
									<form action="controller" method="post">
										<input type="hidden" name="command" value="backToStaff" /> 
										<span style="margin-bottom: 20px; margin-right: 30px;"> 
										<input type="submit" value=<fmt:message key="back.staff" />
											class="button1" />
										</span>
									</form>
								</div>
							</div>
						</div>
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
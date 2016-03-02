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
								</p>
								</td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="logout" />
								<input class="button1" type="submit" value=<fmt:message key="option.logout" /> />
							</form>
						</td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="manageStaff" />
								<input class="button1" type="submit" value=<fmt:message key="manage.staff" /> />
							</form> <c:if test="${ employeesNull != null }">
								<fmt:message key="${ employeesNull }" />
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</header>	
			<div >
					<div >
						<div  style="margin-top: 20px;">
							<div class="notes">
								<fmt:message key="flight.new" />
							</div>
							<div  align="center">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="addFlight" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="center"><fmt:message key="flight.flight" />
												: </span> <input type="number" name="addedflight" value=""
												class="input" />
										</div>
										<div class="row">
											<span class="center"><fmt:message key="flight.to" /> :
											</span> <input type="text" name="to" value="" class="input" />
										</div>
										<div class="row">
											<span class="center"><fmt:message key="flight.from" />
												: </span> <input type="text" name="from" value="" class="input" />
										</div>
										<div class="row">
											<span class="center"><fmt:message key="flight.date" />
												: </span> <input type="date" name="date" value="" class="input" />
										</div>
										<div class="row">
											<span class="center"><fmt:message key="flight.plane" />
												: </span> <select class="input" name="plane">
												<option disabled><fmt:message key="position.select" /></option>
												<c:forEach var="pl" items="${planes}">
													<option value="${ pl.id }">${ pl.id }</option>
												</c:forEach>
											</select>
										</div>
										<div class="wrapper" style="margin-top: 10px;">
											<span class="center"> <input type="submit"
												value=<fmt:message key="flight.add" /> class="button1" />
											</span>
										</div>

										<span class="right relative"> <c:if
												test="${ flightNotAdded != null }">
												<fmt:message key="${ flightNotAdded }" />
											</c:if> <c:if test="${ flightAdded != null }">
												<fmt:message key="${ flightAdded }" />
											</c:if>
										</span>
									</div>
								</form>
							</div>
							<div class="notes">
								<fmt:message key="flight.delete" />
							</div>
							<div align="center">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="deleteFlight" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="center">
											<fmt:message key="flight.flight" />  : 
											</span> <select required="required" class="input" name="delFlightId">
												<c:forEach var="flight" items="${flights}">
													<c:set var="status" value="${ flight.status}" />
													<c:if test="${ status == 2}">
														<option value="${ flight.id }">${ flight.id }${ flight.to }-${ flight.from }
															${ flight.date }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										<div class="wrapper"  style="margin-top: 10px;">
											<span class="center"> <input type="submit"
												value=<fmt:message key="flight.delete" /> class="button1" />
											</span>
										</div>

										<span class="right relative"> <c:if
												test="${ flightDeleted != null }">
												<fmt:message key="${ flightDeleted }" />
											</c:if> <c:if test="${ flightNotDeleted != null }">
												<fmt:message key="${ flightNotDeleted }" />
											</c:if>
										</span>
									</div>
								</form>
							</div>
						</div>
					</div>
				
					<div  style="margin-top: 10px;">
						<div class="notes">
							<!--заголовок  -->
							<fmt:message key="flight.flights" />
						</div>
						<div>
							<table style="width: 100%;">
								<tr class="name">
									<td><fmt:message key="flight.flight" /></td>
									<td><fmt:message key="flight.to" /></td>
									<td><fmt:message key="flight.from" /></td>
									<td><fmt:message key="flight.date" /></td>
									<td><fmt:message key="flight.action" /></td>
								</tr>
								<c:forEach var="flight" items="${flights}">
									<c:set var="status" value="${ flight.status}" />
									<c:if test="${ status == 1}">
										<tr>
											<td><c:out value="${ flight.id }" /></td>
											<td><c:out value="${ flight.to }" /></td>
											<td><c:out value="${ flight.from }" /></td>
											<td><c:out value="${ flight.date }" /></td>
											<td>
												<form style="margin-bottom: 0;" name="teamForm"
													method="POST" action="controller">
													<input type="hidden" name="command" value="completeFlight" />
													<input type="hidden" name="flightId" value="${ flight.id }" />
													<input class="button1" type="submit"
														value=<fmt:message key="flight.complete" /> />
												</form>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
							<span> <c:if test="${ flightCompleted != null }">
									<fmt:message key="${ flightCompleted }" />
								</c:if> <c:if test="${ flightNotCompleted != null }">
									<fmt:message key="${ flightNotCompleted }" />
								</c:if>
							</span>
						</div>
					</div>
			</div>
		<footer>
			<div align="center" style="margin-top: 10px;">
				<div class="links">
					<fmt:message key="contact.email" />
				</div>
			</div>
		</footer>
	</div>
</body>
</html>
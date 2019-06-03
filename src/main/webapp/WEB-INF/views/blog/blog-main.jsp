<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url='/WEB-INF/views/includes/blog-header.jsp' />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${empty postVo}">
							<h4>작성된 게시글이 없습니다.</h4>
							<p>게시글을 등록해주세용^^
							<p>
						</c:when>
						<c:otherwise>
							<h4>${postVo.title }</h4>
							<p>${postVo.content }</p>
						</c:otherwise>
					</c:choose>
				</div>
				
				<ul class="blog-list">
					<c:forEach items='${postTitleList}' var='vo' varStatus='status'>
						<li><a
							href="${pageContext.servletContext.contextPath}/${blogVo.id}/${vo.categoryNo}/${vo.no}">${vo.title}</a> <span>${ vo.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items='${categoryList}' var='vo' varStatus='status'>
					<li><a
						href="${pageContext.servletContext.contextPath}/${blogVo.id}/${vo.no}">${vo.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>
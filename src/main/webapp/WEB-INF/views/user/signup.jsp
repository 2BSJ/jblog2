<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script>
	// $(function(){
	// 	$('#email').change(function(){
	// 		$('#check-button').show();
	// 		$('#check-img').hide();		
	// 	})
	// 	$('#check-button').click(function(){
	// 		var email = $('#email').val();
	// 		if(email==''){
	// 			return ;
	// 		}

	// 		/*ajax 통신 */
	// 		$.ajax({
	// 			url:"${pageContext.servletContext.contextPath }/user/api/checkemail?email=" + email,
	// 			type:"get",
	// 			dataType:"json",
	// 			data:"",
	// 			success:function(response){
	// 				if(response.result != "success"){
	// 					console.log(response.message);
	// 					return ;
	// 				}

	// 				if(response.data == true){
	// 					alert('이미 존재하는 이메일입니다.\n다른 이메일 사용 ㄱ')
	// 					$('#email').focus();
	// 					$("#email").val("");
	// 					return;
	// 				}

	// 				$('#check-button').hide();
	// 				$('#check-img').show();

	// 			},
	// 			error:function(xhr, error){
	// 				console.error("error:" + error)
	// 			}
	// 		});
	// 		console.log(email);
	// 	})
	// });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<c:import url='/WEB-INF/views/includes/navigation.jsp' />
		<form class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath}/user/signup">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			
<!-- 			<input id="btn-checkemail" type="button" value="id 중복체크"> -->
<%-- 			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png"> --%>

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>

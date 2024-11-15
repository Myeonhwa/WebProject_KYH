<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary" >
	
	<span style="color: red; font-size: 1.2em;">
		<%= request.getAttribute("LoginErrMsg") == null ?
				"" : request.getAttribute("LoginErrMsg") %>
	</span>
	<%
		if (session.getAttribute("UserId") == null ) {
	%>
	<script>
		function validateForm(form) {
			if (!form.user_id.value) {
				alert("아이디를 입력하세요");
				form.user_id.focus();
				return false;
			}
			
			if (form.user_pw.value == "") {
				alert("비밀번호를 입력하세요");
				form.user_pw.focus();
				return false;
			}
		}
	</script>
		
    <div class="container" data-setbg="../img/hero/hero-1.jpg">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image">
                            	<img src="../img/login/bg.jpg" alt="" width="450" height="500"/>
                            </div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">환영합니다!</h1>
                                    </div>
                                    <form class="user" action="LoginProcess.jsp" method="post"
                                    	name="loginFrm" onsubmit="return validateForm(this);">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="user_id" aria-describedby="emailHelp"
                                                placeholder="아이디">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                id="user_pw" placeholder="비밀번호">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">자동로그인</label>
                                            </div>
                                        </div>
                                        <a href="../index.jsp" type="submit" class="btn btn-primary btn-user btn-block">
                                            로그인
                                        </a>                                                                                
                                    </form>
                                    <%
									} else {
                                    %>
                                    <%= session.getAttribute("UserName") %> 로그인 되었습니다. <br />
                                    <a href="Logout.jsp">[로그아웃]</a>
                                    <% 
                                    }
                                    %>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.jsp">비밀번호 찾기</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="register.jsp">회원가입 하기</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>
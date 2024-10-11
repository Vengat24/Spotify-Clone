<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		//To avoid back button cache.
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//Http 1.1
		response.setHeader("Pragma", "no-cache"); //Http 1.0
		response.setHeader("Expires","0");// Proxies Browser
	
%>
<!DOCTYPE html>
<html>
<head>
 <link rel="icon" href="./images/favicon.ico" type="image/x-icon">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 <link rel="stylesheet" href="./Css/signup.css">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Sign up - Spotify</title>
</head>
<body>
        <div class="signup-main">
            <div class="top-signup">
                <div class="logo">
                    <img src="./images/spotifylogo.png" alt="logo" width="50px">
                    <h3>Sign up to start listening</h3>
                </div>
                <form action="signup">
                	<div class="email">
	                    <label>Email address</label>
	                    <input type="email" name="email" placeholder="name@domain.com">
	                </div>
	                <div class="signupbtn">
	                    <input type="submit" value="Next">
	                </div>
                </form>
            </div>
            <div class="line">
                <hr id="half1">
                <p>or</p>
                <hr id="half2">
            </div>
            <div class="down-signup">
                <div class="other-signup">
                    <div>
                       <img src="./images/google.png" alt="google logo" width="25px">
                       <p>Sign up with Google</p>
                    </div>
                    <div>
                       <img src="./images/facebook.png" alt="facebook logo" width="25px">
                       <p>Sign up with Facebook</p>
                    </div>
                    <div>
                       <img src="./images/apple.png" alt="apple logo" width="40px">
                       <p>Sign up with Apple</p>
                    </div>
               </div>   
            </div>
            <hr id="full">
            <div class="end">
                <div class="signup">
                    <p>Already have an account? </p>
                    <a href="./login">Log in here.</a>
                </div>
                <div class="forget">
                    <p>This site is protected by reCAPTCHA and the Google Privacy Policy and Terms of Service apply.</p>
                </div>
            </div>

        </div>
</body>
</html>
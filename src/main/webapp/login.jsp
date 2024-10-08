<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
     <link rel="icon" href="./images/favicon.ico" type="image/x-icon">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
     <link rel="stylesheet" href="./Css/login.css">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Login - Spotify</title>
    </head>
    <body>
            <div class="login-main">
                <div class="top-login">
                    <div class="logo">
                        <img src="./images/spotifylogo.png" alt="logo" width="30px">
                        <h3>Log in to Spotify</h3>
                    </div>
                    <div class="other-login">
                         <div>
                            <img src="./images/google.png" alt="google logo" width="25px">
                            <p>Continue with Google</p>
                         </div>
                         <div>
                            <img src="./images/facebook.png" alt="facebook logo" width="25px">
                            <p>Continue with Facebook</p>
                         </div>
                         <div>
                            <img src="./images/apple.png" alt="apple logo" width="40px">
                            <p>Continue with Apple</p>
                         </div>
                         <div>
                            <p>Continue with phone number</p>
                         </div>
                    </div>
                </div>
                <hr>
                <form action="login" method="post">
                <div class="down-login">
                        <div class="username">
                            <label>Email or username</label>
                            <input type="email" name="email" placeholder="name@domain.com">
                        </div>
                        <div class="password">
                            <label>Password </label>
                            <input type="password" name="password" placeholder="Password">
                            <i class="fa-regular fa-eye-slash eye" onclick="viewPassword()" id="eye-close"></i>
                            <i class="fa-regular fa-eye eye" onclick="viewPassword()" id="eye-open" style="visibility: hidden;"></i>
                        </div>
                        <div class="loginbtn">
                            <input type="submit" value="Log in">
                        </div>
                        <div class="forget">
                            <a href="./signup.jsp">Forgot your password?</a>
                        </div>
                        <div class="signup">
                            <p>Don't have an account? </p>
                            <a href="./signup.jsp">Sign up for spotify</a>
                        </div>
                    </div>
                </form>
            </div>
            <script src="./JS/login.js"></script>
    </body>
    </html>
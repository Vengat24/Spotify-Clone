<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		//To avoid back button cache.
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//Http 1.1
		response.setHeader("Pragma", "no-cache"); //Http 1.0
		response.setHeader("Expires","0");// Proxies Browser
		
		if(session.getAttribute("email") == null){
			response.sendRedirect("signup.jsp");
		}
	
%>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="./images/favicon.ico" type="image/x-icon">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./Css/signup2.css">
        <title>Signup - Spotify</title>
    <style>
    	.verifybtn button{
		    width: 100%;
		    height: 100%;
		    border: none;
		    background-color: #1ed760;
		    border-radius: 50px;
		    font-weight: 600;
		}
    </style>
    </head>
    <body>
        <div class="signup-main">
            <form action="signup2" method="post">
                <div class="logo">
                    <img src="./images/spotifylogo.png" alt="logo" width="50px">
                </div>   
                <div class="password">
                    <label>Create a Password </label>
                    <input type="password" name="password" placeholder="Password" id="password" required>
                    <i class="fa-regular fa-eye-slash eye" onclick="viewPassword()" id="eye-close"></i>
                    <i class="fa-regular fa-eye eye" onclick="viewPassword()" id="eye-open" style="visibility: hidden;"></i>
                </div>
                <div class="verifybtn">
                    <button onclick="return verifyPass()">Verify</button>
                </div>
                <div id="message"></div> <!-- Element to display messages -->
                
                
                <div class="name">
                    <h4>Name</h4>
                    <p>This name will appear on your profile</p>
                    <input type="text" placeholder="Enter your name" required name="username">
                </div>
                <div class="dob">
                    <h4>Date of birth</h4>
                    <input type="number" placeholder="Day" min="1" max="31" required name="date">
                    <select required name="month">
                        <option value="" disabled selected >Month</option>
                        <option  value="January">January</option>
                        <option value="February">February</option>
                        <option value="March">March</option>
                        <option value="April">April</option>
                        <option value="May">May</option>
                        <option value="June">June</option>
                        <option value="July">July</option>
                        <option value="August">August</option>
                        <option value="September">September</option>
                        <option value="October">October</option>
                        <option value="November">November</option>
                        <option value="December">December</option>
                    </select>
                    <input type="number" placeholder="Year" min="1900" max="2024" required name="year">
                </div>
        
                <div class="gender">
                    <h4>Gender</h4>
                    <p>We use your gender to help personalize our content recommendations and ads for you.</p>
                    <div class="gender-option">
                        <input type="radio" name="gender" value="man" checked> Man
                        <input type="radio" name="gender" value="woman"> Woman
                        <input type="radio" name="gender" value="non-binary"> Non-binary
                        <input type="radio" name="gender" value="something-else"> Something else
                        <input type="radio" name="gender" value="prefer-not-to-say"> Prefer not to say
                    </div>
                    
                </div>
                <div class="signupbtn">
                    <input type="submit" value="Sign up">
                </div>
        
                    
            </form>    
            </div>
        <script src="./JS/signup.js"></script>   
    </body>
    </html>
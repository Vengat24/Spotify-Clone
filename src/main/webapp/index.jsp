<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spotify.dao.SignupDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spotify - Web Player: Music for everyone</title>
    <link rel="icon" href="./images/logo_green.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./Css/index.css">
<style>
body{
    margin: 0;
    /* flex-wrap: wrap; */
    display: flex;
    flex-direction : column;
    background-color: black;
}
.header{
    width: 98%;
    height: 50px;
    padding-top: 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    
}

.header .user{
    /* height: 80px; */
    width: 25%;
    display: flex;
    justify-content: flex-end;
    align-items: center;
}
.main-flow{
    display: flex;
}
.header .user > a {
    padding: 15px;
    width: 120px;
    font-size: 15px;
    font-weight: 600;
    border-radius: 25px;
    background-color: aliceblue;
    color: black;
    text-decoration: none;
    display: flex;
    justify-content: center;
    align-items: center;
}
.header .user>div{
    width: 50px;
    height: 50px;
    background-color: rgb(51, 52, 52);
    border-radius: 50%;
    margin-left: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.header .user p{
    width: 35px;
    height: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    font-weight: 600;
    font-family: sans-serif;
    background-color: rgb(135, 255, 54);
    border-radius: 50%  ;
}
.header .homepage{
    width: 75%;
    height: auto;
    display: flex;
    justify-content: space-between;
    margin-left: 20px;
    flex-direction: row;

}

.left .home{
    width: 330px;
    height: 26%;
    min-height: 180px;
    display: flex;
    justify-content: center;
    gap: 15px;
    padding-left: 20px;
    font-size: 25px;
    flex-direction: column;
    border-radius: 10px;
    background-color: var(--bgcolor);
}
.left .home a{
    color: aliceblue;
    text-decoration: none;
}
.left .home>div{
    color: aliceblue;

}
#home{
    cursor: pointer;
}
.header #homepage{
    cursor: pointer;
    width:60%;
    display: flex;
    gap: 10px;
}
.header #homepage .fa-house{
    background-color: var(--bgcolor);
    width: 50px;
    height: 50px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: aliceblue;
    font-size: 20px;
    

}

.library{
    width: 350px;
    height: auto;
    border-radius: 10px;
    background-color: var(--bgcolor);
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    align-items: center;
}

.search_bar{
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
    width: 90%;
    height: 50px;
    background-color: var(--bgcolor);
    border-radius: 50px;
    color: aliceblue;
    font-size: 20px;
}
.search_bar input{
    width: 80%;
    height: 65%;
    border: none;
    outline: none;
    background-color: var(--bgcolor);
    color: aliceblue;
    padding-left: 10px;
    font-size: 17px;
}
</style>
</head>
<body>
    <header class="header">
        <div class="homepage">
            <div>
                <img src="./images/spotifylogo.png" alt="logo" width="45px">
            </div>
            <div  id="homepage"> 
                <a href="index.jsp"><i class="fa-solid fa-house"></i></a>
                <div class="search_bar">
                    
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input id="search" type="text" placeholder="What do you want to play?">
                        
                    
                </div>
            </div>
            
        </div>

        <% if (session.getAttribute("email") == null) { %>
            <div class="user">
                <a href="./signup.jsp" style="background-color: transparent;color: aliceblue;">Sign Up</a>
                <a href="./login.jsp" id="login">Log In</a>
            </div>
        <% } else { %>
            <form action="Logout" method="post" id="logoutForm" class="user">
            
			    <a href="#" onclick="document.getElementById('logoutForm').submit(); return false;">Log Out</a>
                <div ><p><%= ((String) session.getAttribute("email")).charAt(0) %></p></div>            
            
			</form>
        <% } %>
    </header>
    <div class="main-flow">
        <div class="left">
            <div class="home">
                <div>
                    <i class="fa-regular fa-user"></i></i>
                    <% if (session.getAttribute("email") == null) { %>
                    	<a href="./login.jsp">Guest</a>
                    <% } else { %>
                    <a href="#"><%= SignupDao.getUserName((String) session.getAttribute("email")) %></a>
                    <%} %>						                    
                    
                </div>
                <div  id="home"> 
                    <i class="fa-solid fa-house"></i>
                    <a href="index.jsp">Home</a>
                </div>
                <div>
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <a href="#search">Search</a>
                </div>
            </div>
            <div class="library">
                <div class="head">
                    <div>
                        <i class="fa-regular fa-bookmark"></i>
                    <span>Your library</span>
                    </div>
                    <div>
                        <i class="fa-solid fa-plus" id="fa-plus" onclick="create()"></i>
                    </div>
                </div>
    
                <div class="createplay" id="createplay">
				    <p>Create Your Playlist</p>
				    <p>It's easy, we'll help you</p>
				    <% if(session.getAttribute("email") == null) { %>
				        <button id="createplaybutton" onclick="window.location.href='login.jsp'">Create Playlist</button>
				    <% } else { %>
				        <button id="createplaybutton" onclick="create()">Create Playlist</button>
				    <% } %>
				</div>

    
                <div class="favplay">
                    <p>Add Songs to Favorite Songs</p>
                    <p>Find it Easyly when needed </p>
                    <% if(session.getAttribute("email") == null) { %>
                        <button  onclick="window.location.href='login.jsp'">View Favorite Songs</button>
                    <% } else { %>
                        <button id="viewfav">View Favorite Songs</button>
                    <% } %>
                    
                    
                </div>
    
    
    
            </div>
        </div>
    
        <div class="mid" id="mid">
            <div class="close" id="close">
                <i class="fa-solid fa-xmark"></i>
            </div>
            <div class="photo" id="photo">
    
            </div>
            <div class="main">
                <div class="songname">
                    <p id="songname">Song Name</p>
                    <p id="artistname">Artist Name</p>
                </div>
                <div class="playline">
                    <div class="playing" id="playing">
                        <i class="fa-solid fa-circle"></i>
                    </div>
                </div>
                <div class="playsong">
                    <i class="fa-solid fa-backward-step"></i>
                    <div class="songcontrol">
                        <i class="fa-solid fa-circle-play" id="playbutton"></i>
                        <i class="fa-solid fa-circle-pause" id="stopbutton"></i>
                    </div>
                    <i class="fa-solid fa-forward-step"></i>
                    <!-- <i class="fa-regular fa-heart" id="like"></i> -->
    
                </div>
                <div>
                    <audio id="cursong" src="./songs/Verithanam-MassTamilan.io.mp3" controls></audio>
                </div>
            </div>
        </div>
        <div class="right">
            <div class="listname">
                <p id="album"></p>
            </div>
            <div class="songlist" id="songlist">
                
    
            </div>
        </div>
    
    </div>



    <script src="./JS/index.js"></script>
</body>
</html>
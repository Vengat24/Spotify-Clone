

var list = {
    "song1": {
        "name" : "Verithanam",
        "artist" :"A.R. Rahman, vijay",
        "imglink": "./images/bigil.jpeg",
        "audiolink" : "./songs/Verithanam-MassTamilan.io.mp3",
        "length" : "3000",
        "songno" : "1"
    },
    "song2": {
        "name" : "Naa Ready",
        "artist" :"Aniruth, vijay, asal kolaru",
        "imglink":"./images/leo.jpeg",
        "audiolink" : "./songs/Naa Ready.mp3",
        "length" : "3000",
        "songno" : "2"
    },
    "song3": {
        "name" : "Oh Raaya",
        "artist" :"Dhanush, A.R. Rahman, Ganavya",
        "imglink": "./images/raaya.jpeg",
        "audiolink" : "./songs/Oh Raaya.mp3",
        "length" : "3000",
        "songno" : "3"
    },
    "song4": {
        "name" : "Thatha Vararu",
        "artist" :"Aniruth",
        "imglink": "./images/indian.jpeg",
        "audiolink" : "./songs/Thatha Vararu.mp3",
        "length" : "3000",
        "songno" : "4"
    },
    "song5": {
        "name" : "Jumukku Jumukku",
        "artist" :"Anthony Daasan, Aavi",
        "imglink": "./images/vjsiddhu.jpeg",
        "audiolink" : "./songs/Verithanam-MassTamilan.io.mp3",
        "length" : "3000",
        "songno" : "5"
    },
    "song6": {
        "name" : "Kayilae Aagasam",
        "artist" :"Jos, Govind Vasantha",
        "imglink": "./images/soorarai.jpeg",
        "audiolink" : "./songs/Aagasam.mp3",
        "length" : "3000",
        "songno" : "6"
    },
    "song7": {
        "name" : "Kannamma",
        "artist" :"Aniruth, vijay, asal kolaru",
        "imglink": "./images/kala.jpeg",
        "audiolink" : "./songs/Verithanam-MassTamilan.io.mp3",
        "length" : "3000",
        "songno" : "7"
    },
    "song9": {
        "name" : "Agayam Theepiditha ",
        "artist" :"Pradeep, Santhosh",
        "imglink": "./images/madras.jpeg",
        "audiolink" : "./songs/Verithanam-MassTamilan.io.mp3",
        "length" : "3000",
        "songno" : "9"
    },
    "song10": {
        "name" : "Toofan",
        "artist" :" Sandesh, Sachin",
        "imglink": "./images/kgff.jpeg",
        "audiolink" : "./songs/Verithanam-MassTamilan.io.mp3",
        "length" : "3000",
        "songno" : "10"
    },
};
var fav = [];
var playlist = [];
createList(list);


function createList(list, album = "Popular albums") {
    document.getElementById("album").innerText = album;
    var songlist = document.getElementById("songlist");
    songlist.innerHTML = "";  // Clear the list before populating

    for (var key in list) {
        
        displaySong(list[key]);  // Displaying the song regardless
    }
}



function displaySong(element) {
    var songlist = document.getElementById("songlist");

    var song = document.createElement("div");
    song.classList.add("song");

    var songimg = document.createElement("div");
    songimg.classList.add("songimg");
    songimg.style.backgroundImage = `url('${element["imglink"]}')`;
    songimg.style.backgroundPosition = "center";
    songimg.style.backgroundSize = "cover";

    var songdetails = document.createElement("div");
    songdetails.classList.add("songdetails");

    var songnameline = document.createElement("div");
    songnameline.classList.add("songnameline");

    var songname = document.createElement("p");
    songname.innerText = element["name"];

    var addtoplay = document.createElement("i");  // Declaring addtoplay as a local variable
    if(!playlist.includes(element)){
        addtoplay.classList.add("fa-solid", "fa-plus");
    }else{
        addtoplay.classList.add("fa-solid", "fa-minus");
    }
    // addtoplay.style.visibility = "hidden";
	
	var like = document.createElement("i");  // Create a like button for each song
	like.classList.add("fa-solid", "fa-heart");  // Add Font Awesome classes to the <i> element
	like.style.color = "red";
	
    var icon = document.createElement("div");
    icon.classList.add("icon");

    var artistname = document.createElement("p");
    artistname.innerText = element["artist"];
    artistname.style.color = "rgb(138, 189, 173)";
	
	if (isLoggedIn) {
    	icon.append(addtoplay, like);
	    songnameline.append(songname, icon);
	}else{
		songnameline.append(songname);
	}
    songdetails.append(songnameline, artistname);
    song.append(songimg, songdetails);
    songlist.append(song);

    song.addEventListener("click", () => {
        var mid = document.getElementById("mid");
        mid.style.width = "60%";
        mid.style.visibility = "visible";
        mid.style.margin ="10px 0px 10px 10px";
        var photo = document.getElementById("photo");
        photo.style.backgroundImage = `url('${element["imglink"]}')`;
        photo.style.backgroundSize = "cover";

        var songname = document.getElementById("songname");
        songname.innerText = element["name"];

        var artistname = document.getElementById("artistname");
        artistname.innerText = element["artist"];

        var cursong = document.getElementById("cursong");
        cursong.src = element["audiolink"];

        var playbutton = document.getElementById("playbutton");
        var stopbutton = document.getElementById("stopbutton");
        playbutton.style.visibility = "visible";
        stopbutton.style.visibility = "hidden";
        playbutton.onclick = () => {
            cursong.play();
            playbutton.style.visibility = "hidden";
            playbutton.style.width = "0px";
            stopbutton.style.visibility = "visible";
            stopbutton.style.width = "40px";
        
            var time = 0;
            playsong(time);
        };
        
        function playsong(time) {
            var playing = document.getElementById("playing");
        
            // Update the width of the playing bar
            playing.style.width = `${time}%`;
        
            // Increment the time for the next update
            time += 1;
        
            // Stop if the song is paused
            if (cursong.paused) {
                return;
            }
        
            // Stop if the playing bar reaches 100%
            if (time > 100) {
                return;
            }
        
            // Call playsong again after a short delay
            setTimeout(() => playsong(time), element["length"]);  // Adjust the delay as needed
        }
        
        stopbutton.onclick = () => {
            cursong.pause();
            stopbutton.style.visibility = "hidden";
            stopbutton.style.width = "0px";
            playbutton.style.visibility = "visible";
            playbutton.style.width = "40px";
        };
        
        
    });

    if (fav.includes(element)) {  
            like.style.color = "red";
    } else {
            like.style.color = "white";
    }
    like.addEventListener("click", (event) => {
        event.stopPropagation();  // Prevent the parent song click event from triggering
    
        let isFavorite = !fav.includes(element);  // Determine whether we're adding or removing from favorites
        if (isFavorite) {  
            fav.push(element);  // Add to favorites
            like.style.color = "red";
        } else {
            fav.splice(fav.indexOf(element), 1);  // Remove from favorites
            like.style.color = "white";
        }
    
        // Send AJAX request to update the database
        fetch("/Spotify/updateFavorite", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                songno: element.songno,
                isFavorite: isFavorite
            })
        }).then(response => response.json())
          .then(data => {
              console.log(element.songno);  // Handle success response
          }).catch(error => {
              console.error("Error updating favorites:", error);  // Handle error
          });
    });
    

    addtoplay.addEventListener("click", (event) => {
        event.stopPropagation();  // Prevent the parent song click event from triggering
        let isFavorite = !playlist.includes(element); 

        if (isFavorite) {  // Prevent duplicates
            playlist.push(element);
            addtoplay.classList.remove("fa-plus");
            addtoplay.classList.add("fa-minus");
        } else {
            // Remove specific element from playlist
            playlist.splice(playlist.indexOf(element), 1);
            addtoplay.classList.remove("fa-minus");
            addtoplay.classList.add("fa-plus");
        }

         // Send AJAX request to update the database
         fetch("/Spotify/updatePlaylist", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                songno: element.songno,
                isFavorite: isFavorite
            })
        }).then(response => response.json())
          .then(data => {
              console.log(element.songno);  // Handle success response
          }).catch(error => {
              console.error("Error updating favorites:", error);  // Handle error
          });
    });

    


    var close = document.getElementById("close");
    close.addEventListener("click", () => {
        var mid = document.getElementById("mid");
        mid.style.width = "0%";
        mid.style.visibility = "hidden";
        cursong.pause();
    });
}
function fetchPlaylistSong() {
    fetch("http://localhost:8090/Spotify/PlaylistServlet", {
        method: "GET",
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        console.log(data); // Log the data to see its structure

        // Clear the previous favorite list if necessary

        // Loop through the songs in the fetched data
        data.songs.forEach(songId => {
            // Check if the songId exists in the list
            if (list[`song${songId}`]) {
                // Push the song object to the fav array if not already added
                if (!playlist.includes(list[`song${songId}`])) {
                    playlist.push(list[`song${songId}`]);
                }
            }
        });
        console.log('Updated playlist:', playlist.length);
        create();
    })
    .catch(error => {
        console.error('Error fetching favorite songs:', error);
    });
}


var viewfav = document.getElementById("viewfav");
function fetchFavoriteSong() {
    fetch("http://localhost:8090/Spotify/FavoriteSongsServlet", {
        method: "GET",
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        console.log(data); // Log the data to see its structure

        // Clear the previous favorite list if necessary
        fav.length = 0; // Resetting the fav array

        // Loop through the songs in the fetched data
        data.songs.forEach(songId => {
            // Check if the songId exists in the list
            if (list[`song${songId}`]) {
                // Push the song object to the fav array if not already added
                if (!fav.includes(list[`song${songId}`])) {
                    fav.push(list[`song${songId}`]);
                }
            }
        });

        if (fav.length > 0) {
            console.log(fav);
        } else {
            console.log("empty");
        }

        // Call createList to display the favorite songs
        createList(fav,"Favorite albums");
    })
    .catch(error => {
        console.error('Error fetching favorite songs:', error);
    });
}

// Event listener for click events on the button
function fetchFavoriteSongs() {
	fetchPlaylistSong();
    fetch("http://localhost:8090/Spotify/FavoriteSongsServlet", {
        method: "GET",
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        console.log(data); // Log the data to see its structure

        // Clear the previous favorite list if necessary
        fav.length = 0; // Resetting the fav array

        // Loop through the songs in the fetched data
        data.songs.forEach(songId => {
            // Check if the songId exists in the list
            if (list[`song${songId}`]) {
                // Push the song object to the fav array if not already added
                if (!fav.includes(list[`song${songId}`])) {
                    fav.push(list[`song${songId}`]);
                }
            }
        });

        if (fav.length > 0) {
            console.log(fav);
        } else {
            console.log("empty");
        }

        // Call createList to display the favorite songs
        createList(list);
    })
    .catch(error => {
        console.error('Error fetching favorite songs:', error);
    });
}

// Call the function when the page is reloaded
// If you still need the button event to re-fetch
viewfav.addEventListener("click", fetchFavoriteSong);

function create() {
    console.log('Updated playlist:', playlist.length);
    document.getElementById("fa-plus").style.visibility = "hidden";
    var createplay = document.getElementById("createplay");
    if(playlist.length == 0){

        createplay.innerHTML = "";
        
    var createpara = document.createElement("p");
    createpara.innerText = "Enter Your Playlist Name";

    var newplay = document.createElement("input");
    newplay.type = "text";
    newplay.classList.add("newplay");
    
    var createbtn = document.createElement("button");
    createbtn.innerText = "Create PlayList";

    createplay.classList.add("createplay");
    createplay.append(createpara, newplay, createbtn);
    
    createbtn.addEventListener("click", ()=> {
        // Clear the display area and reset playlist creation
        
        createplay.innerHTML = "";
        
        // Create the container div for displaying the playlist
        var displaylist = document.createElement("div");
        displaylist.classList.add("displaylist");
        
        var musiclogo1 = document.createElement("i");
        musiclogo1.classList.add("fa-solid", "fa-music");
        
        // Create a div for the new playlist and set its content
        var newplaylist = document.createElement("div");
        newplaylist.classList.add("newplaylist");
        newplaylist.innerText = newplay.value;
        newplaylist.style.color = "black";
        const playlistName = newplay.value;
        console.log("Playlist name entered:", playlistName);
        // Use AJAX or fetch to send the playlistName to your servlet
        fetch("http://localhost:8090/Spotify/createPlaylist", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({ playlistname: playlistName }) // Assuming userEmail is defined
        })
        .then(response => response.text())
        .then(data => {
            console.log("Server Response:", data);
        })
        .catch(error => console.error("Error:", error));
            
        
        
        
        // Append the new playlist to the display list
        displaylist.append(musiclogo1, newplaylist);
        
        // Append the display list to the createplay element
        createplay.append(displaylist);
        
        displaylist.addEventListener("click", () => {
            fetch("http://localhost:8090/Spotify/PlaylistServlet", {
                method: "GET",
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log(data); // Log the data to see its structure
                
                // Clear the previous favorite list if necessary
                // playlist.length = 0; // Resetting the fav array
                
                // Loop through the songs in the fetched data
                data.songs.forEach(songId => {
                    // Check if the songId exists in the list
                    if (list[`song${songId}`]) {
                        // Push the song object to the fav array if not already added
                        if (!playlist.includes(list[`song${songId}`])) {
                            playlist.push(list[`song${songId}`]);
                        }
                    }
                });        
                // Call createList to display the favorite songs
                createList(playlist, newplay.value + " Playlist");
            })
            .catch(error => {
                console.error('Error fetching favorite songs:', error);
            });
        });
    });
}else{
    fetch('http://localhost:8090/Spotify/fetchPlaylistname')
    .then(response => response.json())
    .then(data => {
        if (data.playlistName) {
            var newplay =  data.playlistName;
            console.log(newplay);
            playlistcontent(newplay );
        } else if (data.error) {
            console.error(data.error);
        }
    })
    .catch(error => console.error('Error:', error));


    
    function playlistcontent(newplay ) {
        // Clear the display area and reset playlist creation
        
        createplay.innerHTML = "";
    
        // Create the container div for displaying the playlist
        var displaylist = document.createElement("div");
        displaylist.classList.add("displaylist");
    
        var musiclogo1 = document.createElement("i");
        musiclogo1.classList.add("fa-solid", "fa-music");
        
        // Create a div for the new playlist and set its content
        var newplaylist = document.createElement("div");
        newplaylist.classList.add("newplaylist");
        newplaylist.innerText = newplay;
        newplaylist.style.color = "black";
    
        
        // Append the new playlist to the display list
        displaylist.append(musiclogo1, newplaylist);
        
        // Append the display list to the createplay element
        createplay.append(displaylist);
        
        displaylist.addEventListener("click", () => {
            fetch("http://localhost:8090/Spotify/PlaylistServlet", {
                method: "GET",
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log(data); // Log the data to see its structure
                
                // Clear the previous favorite list if necessary
                
                // Loop through the songs in the fetched data
                data.songs.forEach(songId => {
                    // Check if the songId exists in the list
                    if (list[`song${songId}`]) {
                        // Push the song object to the fav array if not already added
                        if (!playlist.includes(list[`song${songId}`])) {
                            playlist.push(list[`song${songId}`]);
                        }
                    }
                });        
                // Call createList to display the favorite songs
                createList(playlist, newplay + " Playlist");
            })
            .catch(error => {
                console.error('Error fetching favorite songs:', error);
            });
        });
    }
}
}


function findSong(element){
    
    if(element == 'song1') return 1;
    else if(element == 'song2') return 2;
    else if(element == 'song3') return 3;
    else if(element == 'song4') return 4;
    else if(element == 'song5') return 5;
    else if(element == 'song6') return 6;
    else if(element == 'song7') return 7;
    else if(element == 'song8') return 8;
    else if(element == 'song9') return 9;
    else return 10;
}

window.onload = fetchFavoriteSongs;
// create();

function viewPassword() {
    const passwordInput = document.getElementById("password");
    const eyeClose = document.getElementById("eye-close");
    const eyeOpen = document.getElementById("eye-open");

    if (passwordInput.type === "password") {
        passwordInput.type = "text"; // Change input type to text
        eyeClose.style.visibility = "hidden"; // Hide the closed eye icon
        eyeOpen.style.visibility = "visible"; // Show the open eye icon
    } else {
        passwordInput.type = "password"; // Change input type to password
        eyeClose.style.visibility = "visible"; // Show the closed eye icon
        eyeOpen.style.visibility = "hidden"; // Hide the open eye icon
    }
}




function verifyPass() {
    var password = document.getElementById("password").value;
    var messageDiv = document.getElementById("message"); // Get the message div
    var cap = 0, num = 0, special = 0, low = 0, length = password.length;

    // Reset the message div content
    messageDiv.innerHTML = "";
    messageDiv.style.color = "#1ed760";
    messageDiv.style.fontSize = "13px";
    messageDiv.style.marginTop = "10px";
    // Check password
    for (var ch of password) {
        if (ch >= 'A' && ch <= 'Z') cap += 1;
        else if (ch >= '0' && ch <= '9') num += 1;
        else if (ch >= 'a' && ch <= 'z') low += 1;
        else special += 1;
    }

    if (cap < 1) {
        messageDiv.innerHTML = "Invalid Password: Must contain at least one uppercase letter";
        setTimeout(() => location.reload(), 2000); 
        return false;
    }
    if (num < 1) {
        messageDiv.innerHTML = "Invalid Password: Must contain at least one number";
        setTimeout(() => location.reload(), 2000); 
        return false;
    }
    if (special < 1) {
        messageDiv.innerHTML = "Invalid Password: Must contain at least one special character";
        setTimeout(() => location.reload(), 2000); 
        return false;
    }
    if (low < 1) {
        messageDiv.innerHTML = "Invalid Password: Must contain at least one lowercase letter";
        setTimeout(() => location.reload(), 2000); 
        return false;
    }
    if (length < 10 || length > 16) {
        messageDiv.innerHTML = "Invalid Password: Length must be between 10 and 16 characters";
        setTimeout(() => location.reload(), 2000); 
        return false;
    }

    // If the password is valid
    messageDiv.innerHTML = "Password is valid!";
    return true;
}



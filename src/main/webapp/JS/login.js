

function viewPassword() {
    var btns = document.querySelectorAll(".eye");
    var open = document.getElementById("eye-open");
    var close = document.getElementById("eye-close");
    btns.forEach(function(btn) {
        var input = btn.previousElementSibling; // Assuming the button is after the input field
        if(input.type === 'password') {
            close.style.visibility = "visible";
            open.style.visibility = "hidden";
            input.type = 'text';
        } else {
            open.style.visibility = "visible";
            close.style.visibility = "hidden";
            input.type = 'password';
        }
    });
}

const endpoint_base = "http://localhost:8080/api/v1/users";

function formatResponse(username, password) {
    let response = {
               method: 'POST',
               mode: 'cors',
               headers: {
                   'Content-Type': 'application/json'
               },
               body: JSON.stringify({
                   'username': username,
                   'password': password
               })
           };
    return response;
}

async function register(username, password) {
    if (username.length === 0 || password.length === 0) {
        alert("Username or password cannot be empty.");
        return;
    }
    let confirmPassword = document.getElementById("confirmPassword-text");
    confirmPassword.style.display = "";
    document.getElementById("login-button").style.display = "none";
    document.getElementById("register-button").style.flex = "0 0 100%";

    if (confirmPassword.value.length > 0 && password === confirmPassword.value) {
        await fetch('http://localhost:8080/api/v1/users/register', formatResponse(username, password))
        .then(response => {
            switch (response.status) {
                case 201:
                    alert("Account created successfully!");
                    // redirect back to login screen
                    window.location.replace('http://localhost:8080/login');
                    break;
                case 400:
                    alert("That username already exists!");
                    break;
            }
        });
    }
    else if (confirmPassword.value.length > 0 && password != confirmPassword.value)
        alert("Passwords don't match.");
}

async function login(username, password) {
    if (username.length === 0 || password.length === 0) {
        alert("Username or password cannot be empty.");
        return;
    }
    
    await fetch('http://localhost:8080/api/v1/users/login', formatResponse(username, password))
    .then(response => {
        switch (response.status) {
            case 200:
                alert("Login successful!");
                break;
            case 401:
                alert("Incorrect username or password.");
                break;
        }
    });
}
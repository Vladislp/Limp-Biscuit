function myFunction() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var response = JSON.parse(this.responseText);
            for (var i = 0; i < response.length; i++) {
                document.getElementById("email-" + response[i].id).innerHTML = response[i].email;
                console.log(response[i])
            }

        }
    };
    xhttp.open("GET", "/api/routines");
    xhttp.send();
}
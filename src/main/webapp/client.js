loadClientList();
function loadClientList() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let clientDataList = JSON.parse(this.responseText);
            let clientList = ' <ul class="w3-ul w3-card-4"> ';

            clientDataList.reverse();

            for (let index = 0; index < clientDataList.length; index++) {
                clientList +=
                    ' <li class="w3-bar"> ' +

                    ' <button id= ' + clientDataList[index].id + ' onclick="editClient(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Edit</button> ' +

                    ' <button id=' + clientDataList[index].id + ' onclick="removeClient(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Remove</button> ' +


                    ' <img src="Images/client.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    ' <span class="w3-large"> First Name: ' +  clientDataList[index].firstName  + ' </span><br> ' +
                    ' <span> Last Name: ' +  clientDataList[index].lastName  + ' </span> <br>' +
                    // ' <span> Birthday: ' +  clientDataList[index].dob  + ' </span> <br>' +
                    ' <span> Gender: ' +  clientDataList[index].gender  + ' </span> <br>' +
                    ' <span> Email: ' +  clientDataList[index].email  + ' </span> ' +
                    ' <span> Phone Number: ' +  clientDataList[index].phoneNumber  + ' </span> ' +
                    ' </div> ';
            }
            clientList += "</ul>";
            document.getElementById("clientData").innerHTML = clientList;
        }
    };
    xhttp.open("GET", "/javascript/api/client/list", true);
    xhttp.send();
}

function validateForm()
{
    let pass = true;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let gender = document.getElementById("gender").value;
    let email = document.getElementById("email").value;
    let phoneNumber = document.getElementById("phoneNumber").value;


    if (firstName == null || firstName == "",
    lastName == null || lastName == "",
    gender == null || gender == "",
    email == null || email == "",
    phoneNumber == null || phoneNumber == ""
    )
    {
        alert("Please fill in all the fields.");
        pass = false;
    }

    return pass;
}

function removeClient(clientId)
{
    if ( confirm("Are you sure you want to delete this client?") ) {
        deleteClient(clientId);
    }
}

function deleteClient(clientId)
{
    let clientDTO = { "id": clientId }
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/javascript/api/client/remove", true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==200) { loadClientList(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(clientDTO));
}

function addClient() {
    let client = {  "id" : null,
        "firstName" : document.getElementById("firstName").value,
        "lastName" : document.getElementById("lastName").value,
        // "dob" : new Date(document.getElementById("dob").value).toISOString().substring(0,10),
        "gender" : document.getElementById("gender").value,
        "email" : document.getElementById("email").value,
        "phoneNumber" : document.getElementById("phoneNumber").value }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/javascript/api/client/add", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) { loadClientList(); clearInputFields();}
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(client));
}

function updateClient()
{
    let client = {  "id" : document.getElementById("clientId").value,
        "firstName" : document.getElementById("firstName").value,
        "lastName" : document.getElementById("lastName").value,
        // "dob" : document.getElementById("dob").value,
        "gender" : document.getElementById("gender").value,
        "email" : document.getElementById("email").value,
        "phoneNumber" : document.getElementById("phoneNumber").value
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", "/javascript/api/client/update", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) { loadClientList(); clearInputFields(); document.getElementById("btnSaveClient").innerHTML = "Add Client";}
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(client));
}

function getClient(clientId)
{
    let client = {  "id" : clientId }

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState>3 && xhttp.status==200) {
            let foundClient = JSON.parse(this.responseText);
            document.getElementById("clientId").value = foundClient.id;
            document.getElementById("firstName").value = foundClient.firstName;
            document.getElementById("lastName").value = foundClient.lastName;
            // document.getElementById("dob").value = new Date(foundClient.dob);
            document.getElementById("gender").value = foundClient.gender;
            document.getElementById("email").value = foundClient.email;
            document.getElementById("phoneNumber").value = foundClient.phoneNumber;

            document.getElementById("btnSaveClient").innerHTML = "Update Client";
        }
    };
    xhttp.open("POST", "/javascript/api/client/getClient", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(client));
}

function editClient(clientId)
{
    getClient(clientId);
}

function saveClient()
{
    if(validateForm())
    {
        if(document.getElementById("btnSaveClient").innerHTML == 'Add Client')
        {
            addClient();
        }
        else
        {
            updateClient();
        }
    }
}

function clearInputFields()
{
    document.getElementById("firstName").value = "";
    document.getElementById("lastName").value = "";
    // document.getElementById("dob").value = "";
    document.getElementById("gender").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phoneNumber").value = "";
    document.getElementById("btnSaveClient").innerHTML = "Add Client";
}

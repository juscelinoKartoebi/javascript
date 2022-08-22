loadAccountList();
function loadAccountList() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let accountDataList = JSON.parse(this.responseText);
            let accountList = ' <ul class="w3-ul w3-card-4"> ';

            accountDataList.reverse();

            for (let index = 0; index < accountDataList.length; index++) {
                accountList +=
                    ' <li class="w3-bar"> ' +

                    ' <button id= ' + accountDataList[index].accountNumber + ' onclick="editAccount(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Edit</button> ' +

                    ' <button id=' + accountDataList[index].accountNumber + ' onclick="removeAccount(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Remove</button> ' +


                    ' <img src="Images/account.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    ' <span class="w3-large"> Room Type: ' +  accountDataList[index].type  + ' </span><br> ' +
                    ' <span> First Name: ' +  accountDataList[index].client.firstName  + ' </span> <br>' +
                    ' <span> Last Name: ' +  accountDataList[index].client.lastName  + ' </span> <br>' +
                    ' <span> Currency: ' +  accountDataList[index].currency  + ' </span> <br>' +
                    ' <span> Cost price: ' +  accountDataList[index].balance  + ' </span> <br>' +
                    ' <span> Adds : ' +  accountDataList[index].adds  + ' </span> ' +
                    ' </div> ';
            }
            accountList += "</ul>";
            document.getElementById("accountData").innerHTML = accountList;
        }
    };
    xhttp.open("GET", "/javascript/api/account/list", true);
    xhttp.send();
}

function validateForm()
{
    let pass = true;
    let name = document.getElementById("name").value;
    let author = document.getElementById("author").value;
    let isbn = document.getElementById("isbn").value;

    if (name == null || name == "", author == null || author == "", isbn == null || isbn == "")
    {
        alert("Please fill in all the fields.");
        pass = false;
    }

    return pass;
}

function removeAccount(accountId)
{
    if ( confirm("Are you sure you want to delete this book?") ) {
        deleteAccount(accountId);
    }
}

function deleteAccount(accountId)
{
    let accountDTO = { "id": accountId }
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/javascript/api/account/remove", true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==200) { loadAccountList(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(accountDTO));
}

function addAccount() {
    let account = {  "id" : null,
        "clientFirstName" : document.getElementById("client").value,
        "type" : document.getElementById("type").value,
        "currency" : document.getElementById("currency").value,
        "balance" : document.getElementById("balance").value,
        "Adds" : document.getElementById("adds").value }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/javascript/api/account/add", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) { loadAccountList(); clearInputFields();}
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(account));
}

function clearInputFields()
{
    document.getElementById("client").value = "";
    document.getElementById("type").value = "";
    document.getElementById("currency").value = "";
    document.getElementById("balance").value = "";
    document.getElementById("adds").value = "";
    document.getElementById("btnSaveBook").innerHTML = "Add Account";
}

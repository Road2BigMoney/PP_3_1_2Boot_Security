const url = 'http://localhost:8080/api/';

export function getHeaderData() {
    const headerData = document.getElementById("header-data")
    fetch(url + 'user')
        .then(response => response.json())
        .then(usr => {
            headerData.innerHTML = `
                <div>
                    <a class="font-weight-bold text-white ">${usr.username}</a>
                    <a class=" text-white"> with roles: </a>
                    <a class="text-white" >${usr.roles.map(role => role.name.substring(5))}</a>
                </div>
                `

        })
}

export function fillUsersTable() {
    const getAllUsersTableBody = document.getElementById('allUsersTableBody')


    fetch(url)
        .then(response => response.json())
        .then(data => {
            let content = '';
            console.log(data);
            data.forEach(usr => {
                let userRoles = usr.roles.map(role => role.name.substring(5));

                content += `<tr>
                    <td>${usr.id}</td>
                    <td>${usr.name}</td>
                    <td>${usr.username}</td>
                    <td>${usr.value}</td>
                    <td>${userRoles}</td>
                    <td>
                    <button type="button" class="btn btn-info" data-toggle="modal" id = "buttonEdit"
                     data-index ="${usr.id}" data-target="#modalEdit">
                     Edit
                    </button>
                    </td>
                    <td>
                    <button type="button" class="btn btn-danger delete" id="buttonDelete"
                    data-index="${usr.id}" data-target="#modalDelete" data-toggle="modal">
                    Delete
                    </button>
                    </td>
                </tr>
                `
            })
            getAllUsersTableBody.innerHTML = content;
        })
}


export function fillCurrentUserTable() {
    const getCurrentUserTableBody = document.getElementById("currentUserTableBody");
    // const getCurrentUsername = document.getElementById("currentUsername");
    // const getCurrentUserRoles = document.getElementById("currentUserRoles");
    fetch(url + 'user')
        .then(response => response.json())
        .then(usr => {

            let userRoles = usr.roles.map(role => role.name.substring(5))

            getCurrentUserTableBody.innerHTML = `
                <tr>
                    <td>${usr.id}</td>
                    <td>${usr.name}</td>
                    <td>${usr.username}</td>
                    <td>${usr.value}</td>
                    <td>${userRoles}</td>
                </tr>
                `
        })
}

export function getRolesForNewUser() {
    const selectRolesForNewUser = document.getElementById('selectRolesForNewUser')
    fetch(url + 'roles')
        .then(response => response.json())
        .then(data => {
            let resRoles = ''
            data.forEach(element => {
                resRoles += `
                    <option value='${element.id}' selected>
                    ${element.name.substring(5)}
                    </option>
                    `

            })
            selectRolesForNewUser.innerHTML = resRoles
        })
}

//функция заполнения форм (редактирования, удаления)
export function fillUserForm(id, formName, method) {
    fetch(url + 'user/' + id)
        .then(response => response.json())
        .then(data => {
            formName.id.value = data.id
            formName.name.value = data.name
            formName.username.value = data.username
            formName.value.value = data.value
            let rolesForEditedUser = document.getElementById('roles' + method)
            let userRolesId = []
            data.roles.forEach(role => {
                userRolesId.push(role.id)
            })
            fetch(url + 'roles')
                .then(response => response.json())
                .then(data => {
                    let resRoles = ''
                    data.forEach(element => {
                        resRoles += `
                    <option value='${element.id}' selected>
                    ${element.name.substring(5)}
                    </option>
                    `
                    })
                    rolesForEditedUser.innerHTML = resRoles
                })
        })
}
export function createNewUser(e) {
    e.preventDefault()
    const newUserForm = document.forms['newUserForm']
    let newUserRoles = []
    for (let option of document.getElementById('selectRolesForNewUser').options) {
        if (option.selected) {
            newUserRoles.push({
                id: option.value,
                name: 'ROLE_' + option.innerText
            })
        }
    }
    fetch(url + 'create',{
        method:'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: newUserForm.name.value,
            username: newUserForm.username.value,
            value: newUserForm.value.value,
            password: newUserForm.password.value,
            roles: newUserRoles
        })
    }).then(() =>{
        fillUsersTable()
       // getSuccessMessage('User has been created!')
        $('.nav-tabs a[href="#usersTable"]').tab('show')
    })



}

//функция редактирования юзера
export function updateCurrentUser(e) {
    e.preventDefault()
    let editUserRoles = []
    for (let option of document.getElementById('rolesEdit').options) {
        if (option.selected) {
            editUserRoles.push({
                id: option.value,
                name: 'ROLE_' + option.innerText
            })
        }
    }
    let userEditForm = document.forms['editUserModalForm']
    fetch(url + 'update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: userEditForm.id.value,
            name: userEditForm.name.value,
            username: userEditForm.username.value,
            password: userEditForm.password.value,
            value: userEditForm.value.value,
            roles: editUserRoles
        })
    }).then((response) => {
            if (response.ok) {
                fillUsersTable()
                userEditForm.password.value = ''
                document.getElementById('closeEditModalWindow').click()
              //  getSuccessMessage('User has been updated!')
                $('.nav-tabs a[href="#userTable"]').tab('show')
            } else {
                response.json()
                    .then((res) => {

                    })
            }
        }
    )
}

export function deleteUser(id) {
    fetch(url + 'delete/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(() => {
        fillUsersTable()
        document.getElementById('closeDeleteForm').click()
    })
}





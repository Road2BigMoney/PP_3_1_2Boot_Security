import {
    fillUsersTable,
    fillCurrentUserTable,
    getRolesForNewUser,
    fillUserForm,
    getHeaderData,
    updateCurrentUser,
    deleteUser,
    createNewUser
} from "./functions.js";

window.onload = () => {
    getHeaderData()
    fillCurrentUserTable()
    fillUsersTable()

}

$(document).ready(() => {
    //при активации вкладки Нового юзера зполняются роли
    $('.nav-tabs a[href="#newUser"]').on('show.bs.tab', () => {
        getRolesForNewUser()
        //при нажатии кноки создания нового юзера создаётся юзер
        document.getElementById('addNewUser').addEventListener('click', createNewUser)
    })

    //очистка формы нового юзера
    $('.nav-tabs a[href="#usersTable"]').on('show.bs.tab', () => {
        document.getElementById('newUserForm').reset()
    })

    //заполнение формы редактирования юзера
    $('#modalEdit').off().on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        fillUserForm(id, document.forms['editUserModalForm'], 'Edit')
        //при нажатии кнопки обновления юзер обновляется
        document.getElementById('updateUser').addEventListener('click', updateCurrentUser)

    })

    //заполнение формы удаления
    $('#modalDelete').on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        fillUserForm(id, document.forms['modalDeleteForm'], 'Delete')
        //при нажатии кнопки удаления юзер удаляется
        document.getElementById('delete').addEventListener('click', (event) => {
            deleteUser(id)
        })
    })
})
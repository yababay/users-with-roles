const tbody = document.querySelector('#all-users tbody')

function addUser(user){
    const tr = document.createElement('tr')
    tbody.appendChild(tr)
    for(const key of ['id', 'firstName', 'lastName', 'age', 'email', 'roles']){
        let td = document.createElement('td')
        tr.appendChild(td)
        const value = user[key]
        if(!value) continue
        td.textContent = typeof value == 'object' ? value.map(el => el.name).join(' ') : value
    }
    let td = document.createElement('td')
    tr.appendChild(td)
    td.innerHTML = '<button class="btn btn-success">Edit</button>'
    td = document.createElement('td')
    tr.appendChild(td)
    td.innerHTML = '<button class="btn btn-danger">Delete</button>'
}

async function fetchUsers(){
    const response = await fetch('/api/user')
    if(response.status > 299) throw 'Error while fetching users'
    const users = await response.json()
    users.forEach(addUser)
}

fetchUsers()

async function fetchRoles(){
    const select = document.querySelector('#add-user select')
    const response = await fetch('/api/user/roles')
    if(response.status > 299) throw 'Roles are not fetched.'
    const roles = await response.json()
    for(const role of roles){
        const option = document.createElement('option')
        select.appendChild(option)
        option.setAttribute('value', role.id)
        option.textContent = role.description
    }
}

fetchRoles()

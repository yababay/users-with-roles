(async function(){
    const response = await fetch('/api/user')
    if(response.status > 299) throw 'Error while fetching users'
    const users = await response.json()
    const tbody = document.querySelector('#all-users tbody')
    users.forEach(user => {
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
    })
})()
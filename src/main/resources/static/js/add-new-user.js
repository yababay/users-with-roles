const allUsersTab = document.querySelector('#all-users-tab')

document.querySelector('#add-user form').addEventListener('submit', async e => {
    e.preventDefault()
    const form = e.target
    const password = form.querySelector('#add-user-password').value
    const email = form.querySelector('#add-user-email').value
    const firstName = form.querySelector('#add-user-first-name').value
    const lastName = form.querySelector('#add-user-last-name').value
    const age = parseInt(form.querySelector('#add-user-age').value)
    const roleIds = Array.from(form.querySelector('#add-user-roles').selectedOptions).map(el => parseInt(el.value))
    const body = JSON.stringify({email, password, roleIds, firstName, lastName, age})
    const headers = {'Content-Type': 'application/json'}
    const method = 'post'
    const response = await fetch('/api/user', {method, body, headers})
    if(response.status > 299) throw 'Failure with adding new user.'
    const user = await response.json()
    if(!user) throw 'Failure with adding new user.'
    addUser(user)
    form.reset()
    allUsersTab.click()
})

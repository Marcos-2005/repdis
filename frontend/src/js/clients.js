async function loadClients() {
    try {
        const response = await fetch('http://localhost:8080/clients');
        if (!response.ok) throw new Error('Error al obtener los clientes');
        const clients = await response.json();

        renderClientsTable(clients);
    } catch (error) {
        console.error(error);
        document.getElementById('clients-body').innerHTML = '<tr><td colspan="7">Error al cargar clientes</td></tr>';
    }
}

function renderClientsTable(clients) {
    const tbody = document.getElementById('clients-body');
    tbody.innerHTML = '';

    clients.forEach(client => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.dni}</td>
            <td>${client.phone}</td>
            <td>${client.email}</td>
            <td>${client.address}</td>
        `;

        const actionsTd = document.createElement('td');

        const editBtn = document.createElement('button');
        editBtn.textContent = '✏️';
        editBtn.title = 'Editar';
        editBtn.onclick = () => editClient(client);

        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = '🗑️';
        deleteBtn.style.backgroundColor = 'red';
        deleteBtn.title = 'Eliminar';
        deleteBtn.onclick = () => deleteClient(client.id);

        actionsTd.appendChild(editBtn);
        actionsTd.appendChild(deleteBtn);
        row.appendChild(actionsTd);

        tbody.appendChild(row);
    });
}

async function deleteClient(clientId) {
    try {
        const confirmDelete = confirm(`¿Seguro que quieres eliminar el cliente con ID ${clientId}?`);
        if (!confirmDelete) return;

        const response = await fetch(`http://localhost:8080/clients/${clientId}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error("Error al eliminar el cliente");

        alert("Cliente eliminado correctamente");
        loadClients();
    } catch (error) {
        console.error("Error al eliminar cliente:", error);
        alert("No se pudo eliminar el cliente.");
    }
}

function editClient(client) {
    alert(`TODO: Me falta aún implementarlo: ${client.name}`);
}

document.addEventListener('DOMContentLoaded', loadClients);

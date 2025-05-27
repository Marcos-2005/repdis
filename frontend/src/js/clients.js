let selectedClientRow = null;

document.addEventListener('DOMContentLoaded', () => {
    loadClients();

    const cancelBtn = document.querySelector('#client-form-section .cancel-button');
    if (cancelBtn) {
        cancelBtn.addEventListener('click', hideClientForm);
    }
});

function highlightClientRow(rowElement) {
    if (selectedClientRow) {
        selectedClientRow.querySelectorAll('td').forEach(td => td.classList.remove('highlighted-cell'));
    }
    selectedClientRow = rowElement;
    rowElement.querySelectorAll('td:not(:last-child)').forEach(td => td.classList.add('highlighted-cell'));
}

function clearHighlightedClientRow() {
    if (selectedClientRow) {
        selectedClientRow.querySelectorAll('td').forEach(td => td.classList.remove('highlighted-cell'));
        selectedClientRow = null;
    }
}


async function loadClients() {
    try {
        const response = await fetch('http://localhost:8080/clients');
        if (!response.ok) throw new Error('Error al obtener los clientes');
        const clients = await response.json();
        renderClientsTable(clients);
    } catch (error) {
        console.error(error);
        document.getElementById('clients-body').innerHTML =
            '<tr><td colspan="7">Error al cargar clientes</td></tr>';
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
        editBtn.onclick = () => {
                    fillClientForm(client);
                    highlightClientRow(row);
                };

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

function fillClientForm(client) {
    const formSection = document.getElementById('client-form-section');

    formSection.classList.remove('hidden');
    formSection.classList.add('visible');

    document.getElementById('client-id').value = client.id;
    document.getElementById('client-name').value = client.name;
    document.getElementById('client-dni').value = client.dni;
    document.getElementById('client-phone').value = client.phone;
    document.getElementById('client-email').value = client.email;
    document.getElementById('client-address').value = client.address;
}

async function saveClient() {
    const id = document.getElementById('client-id').value;
    const name = document.getElementById('client-name').value.trim();
    const dni = document.getElementById('client-dni').value.trim();
    const phone = document.getElementById('client-phone').value.trim();
    const email = document.getElementById('client-email').value.trim();
    const address = document.getElementById('client-address').value.trim();

    if (!id || !name || !dni || !phone) {
        alert('Por favor, completa los campos obligatorios (nombre, DNI, teléfono).');
        return;
    }

    const client = { id: parseInt(id), name, dni, phone, email, address };

    hideClientForm();

    try {
        const response = await fetch('http://localhost:8080/clients', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(client)
        });

        if (!response.ok) throw new Error('Error al actualizar el cliente');
        alert('Cliente actualizado');
        loadClients();
    } catch (error) {
        console.error("Error actualizando cliente:", error);
        alert('No se pudo actualizar el cliente.');
    }
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

function hideClientForm() {
    const section = document.getElementById('client-form-section');
    section.classList.remove('visible');
    section.classList.add('hidden');
    document.getElementById('client-form').reset();
    clearHighlightedClientRow();
}

let highlightedRow = null;

async function loadOrders() {
    try {
        const response = await fetch('http://localhost:8080/orders');
        if (!response.ok) throw new Error('Error al obtener las órdenes de servicio');

        const orders = await response.json();
        const tbody = document.getElementById('orders-body');
        tbody.innerHTML = '';

        orders.forEach(order => {
            const hideCompleted = document.getElementById('hide-completed').checked;
            if (hideCompleted && order.status === 'COMPLETED') {
                return; // Saltar esta orden
            }

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${order.id}</td>
                <td>${order.client?.name || 'Sin nombre'}</td>
                <td>${order.device?.type || 'Sin tipo'}</td>
                <td>${order.status}</td>
                <td>${order.difficulty}</td>
                <td>${order.entryDate}</td>
                <td>${order.adminName || 'Sin asignar'}</td>
            `;

            const adminCell = row.children[6];
            adminCell.classList.add('admin-cell');
            adminCell.title = order.adminName || '';

            const viewBtn = document.createElement('button');
            viewBtn.textContent = '👁️';
            viewBtn.style.backgroundColor = 'green';
            viewBtn.onclick = () => {
                fillOrderForm(order);
                highlightRow(row);
            };

            const deleteBtn = document.createElement('button');
            deleteBtn.textContent = '🗑️';
            deleteBtn.style.backgroundColor = 'red';
            deleteBtn.onclick = () => deleteOrder(order.id);

            const actionCell = document.createElement('td');
            actionCell.appendChild(viewBtn);
            actionCell.appendChild(deleteBtn);
            row.appendChild(actionCell);

            tbody.appendChild(row);
        });

        function highlightRow(row) {
            if (highlightedRow) {
                highlightedRow.querySelectorAll('td').forEach(td => td.classList.remove('highlighted-cell'));
            }
            highlightedRow = row;
            const cells = row.querySelectorAll('td:not(:last-child)');
            cells.forEach(td => td.classList.add('highlighted-cell'));
        }

    } catch (error) {
        console.error('Error cargando órdenes:', error);
        document.getElementById('orders-body').innerHTML =
            '<tr><td colspan="7">Error al cargar las órdenes de servicio</td></tr>';
    }
}

function fillOrderForm(order) {
    document.getElementById('order-id').value = order.id || '';
    document.getElementById('entry-date').value = order.entryDate || '';
    document.getElementById('order-cost').value = order.cost || '';
    document.getElementById('order-difficulty').value = order.difficulty || 'MEDIUM';
    document.getElementById('order-status').value = order.status || 'CREATED';
    document.getElementById('order-admin').value = order.adminId || '';

    document.getElementById('client-name').value = order.client?.name || '';
    document.getElementById('client-id').value = order.client?.id || '';
    document.getElementById('client-dni').value = order.client?.dni || '';
    document.getElementById('client-phone').value = order.client?.phone || '';
    document.getElementById('client-email').value = order.client?.email || '';
    document.getElementById('client-address').value = order.client?.address || '';

    document.getElementById('device-type').value = order.device?.type || '';
    document.getElementById('device-id').value = order.device?.id || '';
    document.getElementById('device-brand').value = order.device?.brand || '';
    document.getElementById('device-model').value = order.device?.model || '';
    document.getElementById('device-serial').value = order.device?.serialNumber || '';
    document.getElementById('device-password').value = order.device?.password || '';
}

function showNewOrderModal() {
    document.getElementById("new-order-modal").classList.remove("hidden");
    document.getElementById("client-selection").classList.add("hidden");
}

function closeNewOrderModal() {
    document.getElementById("new-order-modal").classList.add("hidden");
}

function createEmptyOrder() {
    clearForm();
    closeNewOrderModal();

    if (highlightedRow) {
        highlightedRow.querySelectorAll('td').forEach(td => td.classList.remove('highlighted-cell'));
        highlightedRow = null;
    }
}

function showClientSelector() {
    document.getElementById("client-selection").classList.remove("hidden");

    fetch("http://localhost:8080/clients")
        .then(res => res.json())
        .then(clients => {
            const tbody = document.getElementById("client-selection-body");
            tbody.innerHTML = "";
            clients.forEach(c => {
                const row = document.createElement("tr");

                const nameCell = document.createElement("td");
                nameCell.textContent = c.name;

                const phoneCell = document.createElement("td");
                phoneCell.textContent = c.phone;

                row.appendChild(nameCell);
                row.appendChild(phoneCell);

                row.addEventListener("click", () => selectClient(c));
                tbody.appendChild(row);
            });
        });
}

function selectClient(client) {
    clearForm();
    closeNewOrderModal();
    showDeviceSelectionDialog(client);
}

function clearForm() {
    document.getElementById("order-id").value = "";
    document.getElementById("entry-date").value = "";

    document.getElementById("client-id").value = "";
    document.getElementById("client-name").value = "";
    document.getElementById("client-dni").value = "";
    document.getElementById("client-phone").value = "";
    document.getElementById("client-email").value = "";
    document.getElementById("client-address").value = "";

    document.getElementById("device-id").value = "";
    document.getElementById("device-type").value = "";
    document.getElementById("device-brand").value = "";
    document.getElementById("device-model").value = "";
    document.getElementById("device-serial").value = "";
    document.getElementById("device-password").value = "";

    document.getElementById("order-description").value = "";
    document.getElementById("order-cost").value = "";
    document.getElementById("order-difficulty").value = "MEDIUM";
    document.getElementById("order-status").value = "CREATED";
    document.getElementById("order-admin").selectedIndex = 0;
    document.getElementById("client-name").focus();
}

async function saveOrder() {

    const requiredFields = document.querySelectorAll('.required-field');
    let allValid = true;

    requiredFields.forEach(field => {
        if (!field.value.trim()) {
            field.classList.add('invalid');
            allValid = false;
        } else {
            field.classList.remove('invalid');
        }
    });

    const errorBox = document.getElementById('form-error-message');
    if (!allValid) {
        alert("Por favor, rellena todos los campos obligatorios.");
        return;
    } else {
      errorBox.style.display = 'none';
    }

    const id = document.getElementById('order-id').value;

    const clientName = document.getElementById('client-name').value;
    const deviceType = document.getElementById('device-type').value;
    if (!clientName || !deviceType) {
        alert("Nombre del cliente y tipo de dispositivo son obligatorios");
        return;
    }

    const order = {
        id: id ? parseInt(id) : null,
        entryDate: document.getElementById('entry-date').value || new Date().toISOString(),
        status: document.getElementById('order-status').value,
        difficulty: document.getElementById('order-difficulty').value,
        cost: parseFloat(document.getElementById('order-cost').value) || 0,
        adminId: parseInt(document.getElementById('order-admin').value) || null,

        client: {
            id: parseInt(document.getElementById('client-id').value) || null,
            name: clientName,
            dni: document.getElementById('client-dni').value,
            phone: document.getElementById('client-phone').value,
            email: document.getElementById('client-email').value,
            address: document.getElementById('client-address').value
        },

        device: {
            id: parseInt(document.getElementById('device-id').value) || null,
            type: deviceType,
            brand: document.getElementById('device-brand').value,
            model: document.getElementById('device-model').value,
            serialNumber: document.getElementById('device-serial').value,
            password: document.getElementById('device-password').value
        }
    };

    try {
        const response = await fetch('http://localhost:8080/orders', {
            method: id ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order)
        });

        if (!response.ok) throw new Error('Error al guardar la orden');
        alert(id ? 'Orden actualizada' : 'Orden creada con éxito');
        loadOrders();
        clearForm();
    } catch (error) {
        console.error('Error al guardar la orden:', error);
        alert('No se pudo guardar la orden.');
    }
}

async function loadAdmins() {
    try {
        const response = await fetch('http://localhost:8080/admins');
        if (!response.ok) throw new Error('No se pudo cargar la lista de administradores.');

        const admins = await response.json();
        const select = document.getElementById('order-admin');
        select.innerHTML = '<option value="">-- Selecciona un admin --</option>';

        admins.forEach(admin => {
            const option = document.createElement('option');
            option.value = admin.id;
            option.textContent = admin.name;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error cargando administradores:', error);
    }
}

async function deleteOrder(id) {
    try {
        const confirmDelete = confirm(`¿Eliminar la orden con ID ${id}?`);
        if (!confirmDelete) return;

        const response = await fetch(`http://localhost:8080/orders/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error("Error eliminando orden");
        alert("Orden eliminada con éxito");
        clearForm();
        loadOrders();
    } catch (error) {
        console.error("Error al eliminar orden:", error);
        alert("No se pudo eliminar la orden.");
    }
}

function showDeviceSelectionDialog(client) {
    const dialog = document.createElement("div");
    dialog.classList.add("custom-modal");

    dialog.innerHTML = `
        <div class="modal-content">
            <h3>¿Qué deseas hacer con el dispositivo?</h3>
            <button id="btn-new-device">Nuevo dispositivo</button>
            <button id="btn-existing-device">Seleccionar existente</button>
            <button id="btn-back-to-client">Atrás</button>
        </div>
    `;

    document.body.appendChild(dialog);

    document.getElementById("btn-new-device").onclick = () => {
        fillClientFields(client);
        clearDeviceFields();
        closeModal(dialog);
    };

    document.getElementById("btn-existing-device").onclick = () => {
        loadDevicesForClient(client.id, client);
        closeModal(dialog);
    };

    document.getElementById("btn-back-to-client").onclick = () => {
        closeModal(dialog);
        showNewOrderModal(); // vuelve a la pantalla de selección de cliente
    };
}

async function loadDevicesForClient(clientId, client) {
    try {
        const response = await fetch(`http://localhost:8080/devices/by-client/${clientId}`);
        if (!response.ok) throw new Error("No se pudieron cargar los dispositivos");

        const devices = await response.json();
        showDeviceTable(devices, client);
    } catch (error) {
        console.error("Error cargando dispositivos:", error);
        alert("No se pudo cargar la lista de dispositivos.");
    }
}

function showDeviceTable(devices, client) {
    const dialog = document.createElement("div");
    dialog.classList.add("custom-modal");

    let rows = devices.map(device => `
        <tr data-id="${device.id}">
            <td>${device.type}</td>
            <td>${device.brand}</td>
            <td>${device.model}</td>
            <td>${device.serialNumber}</td>
        </tr>
    `).join('');

    dialog.innerHTML = `
        <div class="modal-content">
            <h3>Selecciona un dispositivo</h3>
            <table class="modal-table">
                <thead>
                    <tr>
                        <th>Tipo</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Serie</th>
                    </tr>
                </thead>
                <tbody>
                    ${rows}
                </tbody>
            </table>
            <button onclick="closeModal(this.closest('.custom-modal'))">Cancelar</button>
        </div>
    `;

    document.body.appendChild(dialog);

    dialog.querySelectorAll("tbody tr").forEach(row => {
        row.addEventListener("click", () => {
            const id = row.getAttribute("data-id");
            const device = devices.find(d => d.id == id);
            fillClientFields(client);
            fillDeviceFields(device);
            closeModal(dialog);
        });
    });
}

function fillClientFields(client) {
    document.getElementById('client-id').value = client.id;
    document.getElementById('client-name').value = client.name;
    document.getElementById('client-dni').value = client.dni;
    document.getElementById('client-phone').value = client.phone;
    document.getElementById('client-email').value = client.email;
    document.getElementById('client-address').value = client.address;
}

function fillDeviceFields(device) {
    document.getElementById('device-id').value = device.id;
    document.getElementById('device-type').value = device.type;
    document.getElementById('device-brand').value = device.brand;
    document.getElementById('device-model').value = device.model;
    document.getElementById('device-serial').value = device.serialNumber;
    document.getElementById('device-password').value = device.password;
}

function clearDeviceFields() {
    document.getElementById("device-id").value = "";
    document.getElementById("device-type").value = "";
    document.getElementById("device-brand").value = "";
    document.getElementById("device-model").value = "";
    document.getElementById("device-serial").value = "";
    document.getElementById("device-password").value = "";
}

function closeModal(modal) {
    if (modal) {
        modal.remove();
    }
}

function highlightSelectedRow(rowElement) {
    document.querySelectorAll('table tbody tr').forEach(row => row.classList.remove('selected-row'));
    rowElement.classList.add('selected-row');
}

document.addEventListener('DOMContentLoaded', () => {
    loadOrders();
    loadAdmins();
});
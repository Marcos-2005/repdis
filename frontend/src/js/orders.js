async function loadOrders() {
    try {
        const response = await fetch('http://localhost:8080/orders');
        if (!response.ok) throw new Error('Error al obtener las órdenes de servicio');

        const orders = await response.json();
        const tbody = document.getElementById('orders-body');
        tbody.innerHTML = '';

        orders.forEach(order => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${order.id}</td>
                <td>${order.client?.name || 'Sin nombre'}</td>
                <td>${order.device?.type || 'Sin tipo'}</td>
                <td>${order.status}</td>
                <td>${order.difficulty}</td>
                <td>${order.entryDate}</td>
            `;

            const viewBtn = document.createElement('button');
            viewBtn.textContent = '👁️';
            viewBtn.style.backgroundColor = 'green';
            viewBtn.onclick = () => fillOrderForm(order);
            row.appendChild(viewBtn);

            const deleteBtn = document.createElement('button');
            deleteBtn.textContent = '🗑️';
            deleteBtn.style.backgroundColor = 'red';
            deleteBtn.onclick = () => deleteOrder(order.id);
            row.appendChild(deleteBtn);

            tbody.appendChild(row);
        });

    } catch (error) {
        console.error('Error cargando órdenes:', error);
        document.getElementById('orders-body').innerHTML =
            '<tr><td colspan="6">Error al cargar las órdenes de servicio</td></tr>';
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
                const cell = document.createElement("td");
                cell.textContent = c.name;
                row.appendChild(cell);
                row.addEventListener("click", () => selectClient(c));
                tbody.appendChild(row);
            });
        });
}

function selectClient(client) {
    clearForm();
    document.getElementById("client-name").value = client.name;
    document.getElementById("client-id").value = client.id;
    document.getElementById("client-dni").value = client.dni;
    document.getElementById("client-phone").value = client.phone;
    document.getElementById("client-email").value = client.email;
    document.getElementById("client-address").value = client.address;

    closeNewOrderModal();
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

document.addEventListener('DOMContentLoaded', () => {
    loadOrders();
    loadAdmins();
});

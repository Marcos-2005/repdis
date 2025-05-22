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
    document.getElementById('client-dni').value = '';
    document.getElementById('client-phone').value = '';
    document.getElementById('client-email').value = '';
    document.getElementById('client-address').value = '';

    document.getElementById('device-type').value = order.device?.type || '';
    document.getElementById('device-id').value = order.device?.id || '';
    document.getElementById('device-brand').value = '';
    document.getElementById('device-model').value = '';
    document.getElementById('device-serial').value = '';
    document.getElementById('device-password').value = '';
}

function newOrder() {
    document.getElementById('order-form').reset();
    document.getElementById('order-id').value = '';
    document.getElementById('entry-date').value = '';
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
        newOrder();
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
        newOrder();
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

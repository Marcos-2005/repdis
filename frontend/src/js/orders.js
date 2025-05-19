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
                <td>${order.clientName || 'Sin nombre'}</td>
                <td>${order.deviceType || 'Sin tipo'}</td>
                <td>${order.status}</td>
                <td>${order.difficulty}</td>
                <td>${order.entryDate}</td>
            `;

            row.addEventListener('click', () => {
                fillOrderForm(order);
            });

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
    document.getElementById('order-status').value = order.status || 'PENDING';
    document.getElementById('order-admin').value = order.adminId || '';

    document.getElementById('client-name').value = order.clientName || '';
    document.getElementById('client-id').value = order.clientId || '';
    document.getElementById('client-dni').value = '';
    document.getElementById('client-phone').value = '';
    document.getElementById('client-email').value = '';
    document.getElementById('client-address').value = '';

    document.getElementById('device-type').value = order.deviceType || '';
    document.getElementById('device-id').value = order.deviceId || '';
    document.getElementById('device-brand').value = '';
    document.getElementById('device-model').value = '';
    document.getElementById('device-serial').value = '';
    document.getElementById('device-password').value = '';
}

function newOrder() {
    document.getElementById('order-form').reset();
    document.getElementById('order-id').value = '';
    document.getElementById('entry-date').value = '';
    console.log('Formulario listo para nueva orden');
}

async function saveOrder() {
    const order = {
        clientId: parseInt(document.getElementById('client-id')?.value) || null,
        deviceId: parseInt(document.getElementById('device-id')?.value) || null,
        entryDate: new Date().toISOString(),
        status: document.getElementById('order-status').value,
        difficulty: document.getElementById('order-difficulty').value,
        cost: parseFloat(document.getElementById('order-cost').value) || 0,
        adminId: parseInt(document.getElementById('order-admin').value) || null,
        description: document.getElementById('order-description').value || ''
    };

    try {
        const response = await fetch('http://localhost:8080/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order)
        });

        if (!response.ok) throw new Error('Error al guardar la orden');

        alert('Orden creada con éxito');
        loadOrders(); // refresca la tabla
    } catch (error) {
        console.error('Error al guardar la orden:', error);
        alert('No se pudo crear la orden.');
    }
}

function updateOrder() {
    const id = document.getElementById('order-id').value;
    if (id) {
        console.log('Simulando actualización de orden:', collectFormData());
        alert('Simulación: orden actualizada (ver consola)');
    } else {
        alert('No hay ID, no se puede actualizar una orden que no existe.');
    }
}

function deleteOrder() {
    const id = document.getElementById('order-id').value;
    if (id) {
        const confirmDelete = confirm(`¿Seguro que quieres eliminar la orden con ID ${id}?`);
        if (confirmDelete) {
            console.log(`Simulando eliminación de orden con ID ${id}`);
            alert(`Simulación: orden ${id} eliminada (ver consola)`);
            newOrder(); // Limpieza de form tras la eliminación
        }
    } else {
        alert('No hay una orden seleccionada para eliminar.');
    }
}

function collectFormData() {
    return {
        id: document.getElementById('order-id').value,
        entryDate: document.getElementById('entry-date').value,
        cost: document.getElementById('order-cost').value,
        difficulty: document.getElementById('order-difficulty').value,
        status: document.getElementById('order-status').value,
        adminId: document.getElementById('order-admin').value,
    };
}

document.addEventListener('DOMContentLoaded', loadOrders);

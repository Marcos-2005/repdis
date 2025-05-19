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

    // Los datos reales del cliente/dispositivo aún no están implementados
    document.getElementById('client-name').value = order.clientName || '';
    document.getElementById('client-dni').value = '';
    document.getElementById('client-phone').value = '';
    document.getElementById('client-email').value = '';
    document.getElementById('client-address').value = '';

    document.getElementById('device-type').value = order.deviceType || '';
    document.getElementById('device-brand').value = '';
    document.getElementById('device-model').value = '';
    document.getElementById('device-serial').value = '';
    document.getElementById('device-password').value = '';
}

document.addEventListener('DOMContentLoaded', loadOrders);

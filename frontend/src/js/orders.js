async function loadOrders() {
    try {
        const response = await fetch('http://localhost:8080/orders');
        if (!response.ok) throw new Error('Error al obtener las órdenes de servicio');
        const orders = await response.json();

        const tableBody = document.querySelector('#orders-table tbody');
        tableBody.innerHTML = '';

        orders.forEach(order => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${order.id}</td>
                <td>${order.clientId || 'N/A'}</td>
                <td>${order.deviceId || 'N/A'}</td>
                <td>${order.status}</td>
                <td>${order.difficulty}</td>
                <td>${order.entryDate}</td>
            `;

            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error cargando órdenes:', error);
        const tableBody = document.querySelector('#orders-table tbody');
        tableBody.innerHTML = '<tr><td colspan="6">Error al cargar las órdenes de servicio</td></tr>';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    loadOrders();
});

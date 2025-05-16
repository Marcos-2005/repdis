async function loadOrders() {
    try {
        const response = await fetch('http://localhost:8080/orders');
        if (!response.ok) throw new Error('Error al obtener las órdenes de servicio');
        const orders = await response.json();

        const main = document.getElementById('main-content');
        main.innerHTML = `
            <h2>Órdenes de Servicio</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Fecha de Entrada</th>
                        <th>Estado</th>
                        <th>Dificultad</th>
                        <th>Coste</th>
                        <th>ID Dispositivo</th>
                        <th>ID Admin</th>
                    </tr>
                </thead>
                <tbody>
                    ${orders.map(order => `
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.entryDate}</td>
                            <td>${order.status}</td>
                            <td>${order.difficulty}</td>
                            <td>${order.cost} €</td>
                            <td>${order.deviceId}</td>
                            <td>${order.adminId}</td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;
    } catch (error) {
        console.error(error);
        document.getElementById('main-content').innerHTML = '<p>Error cargando las órdenes de servicio</p>';
    }
}

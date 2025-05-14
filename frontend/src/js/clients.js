async function loadClients() {
    try {
        const response = await fetch('http://localhost:8080/clients');
        if (!response.ok) throw new Error('Error al obtener los clientes');
        const clients = await response.json();

        const main = document.getElementById('main-content');
        main.innerHTML = `
            <h2>Listado de Clientes</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>DNI</th>
                        <th>Teléfono</th>
                        <th>Email</th>
                        <th>Dirección</th>
                    </tr>
                </thead>
                <tbody>
                    ${clients.map(c => `
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>${c.dni}</td>
                            <td>${c.phone}</td>
                            <td>${c.email}</td>
                            <td>${c.address}</td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;
    } catch (error) {
        console.error(error);
        document.getElementById('main-content').innerHTML = '<p>Error cargando los clientes</p>';
    }
}

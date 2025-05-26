let selectedDeviceRow = null;

document.addEventListener('DOMContentLoaded', () => {
    loadDevices();

    const cancelBtn = document.querySelector('#device-form-section .cancel-button');
    if (cancelBtn) {
        cancelBtn.addEventListener('click', hideDeviceForm);
    }
});

async function loadDevices() {
    try {
        const response = await fetch('http://localhost:8080/devices');
        if (!response.ok) throw new Error('Error al obtener los dispositivos');
        const devices = await response.json();

        renderDevicesTable(devices);
    } catch (error) {
        console.error(error);
        document.getElementById('devices-body').innerHTML =
            '<tr><td colspan="7">Error al cargar dispositivos</td></tr>';
    }
}

function renderDevicesTable(devices) {
    const tbody = document.getElementById('devices-body');
    tbody.innerHTML = '';

    devices.forEach(device => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${device.id}</td>
            <td>${device.type}</td>
            <td>${device.brand}</td>
            <td>${device.model}</td>
            <td>${device.serialNumber}</td>
            <td>${device.clientName || ''}</td>
        `;

        const actionsTd = document.createElement('td');

        const editBtn = document.createElement('button');
        editBtn.textContent = '✏️';
        editBtn.title = 'Editar';
        editBtn.onclick = () => {
            fillDeviceForm(device);
            showDeviceForm();
        };

        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = '🗑️';
        deleteBtn.style.backgroundColor = 'red';
        deleteBtn.title = 'Eliminar';
        deleteBtn.onclick = () => deleteDevice(device.id);

        actionsTd.appendChild(editBtn);
        actionsTd.appendChild(deleteBtn);
        row.appendChild(actionsTd);

        tbody.appendChild(row);
    });
}

function fillDeviceForm(device) {
    document.getElementById('device-id').value = device.id;
    document.getElementById('device-type').value = device.type;
    document.getElementById('device-brand').value = device.brand;
    document.getElementById('device-model').value = device.model;
    document.getElementById('device-serial').value = device.serialNumber;
    document.getElementById('device-password').value = device.password;
    document.getElementById('device-client-id').value = device.clientId;
    document.getElementById('device-client-name').value = device.clientName || '';
}

function showDeviceForm() {
    const section = document.getElementById('device-form-section');
    section.classList.remove('hidden');
    section.classList.add('visible');
}

async function saveDevice() {
    const id = document.getElementById('device-id').value;
    const type = document.getElementById('device-type').value.trim();
    const serialNumber = document.getElementById('device-serial').value.trim();
    const clientId = document.getElementById('device-client-id').value.trim();

    const brand = document.getElementById('device-brand').value.trim();
    const model = document.getElementById('device-model').value.trim();
    const password = document.getElementById('device-password').value.trim();

    if (!id || !type || !serialNumber || !clientId) {
        alert('Por favor, completa los campos obligatorios (tipo, nº de serie, cliente).');
        return;
    }

    const device = {
        id: parseInt(id),
        type,
        brand,
        model,
        serialNumber,
        password,
        clientId: parseInt(clientId)
    };

    hideDeviceForm();

    try {
        const response = await fetch('http://localhost:8080/devices', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(device)
        });

        if (!response.ok) throw new Error('Error al actualizar el dispositivo');
        alert('Dispositivo actualizado');
        loadDevices();
    } catch (error) {
        console.error("Error actualizando dispositivo:", error);
        alert('No se pudo actualizar el dispositivo.');
    }
}

async function deleteDevice(id) {
    try {
        const confirmDelete = confirm(`¿Eliminar el dispositivo con ID ${id}?`);
        if (!confirmDelete) return;

        const response = await fetch(`http://localhost:8080/devices/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error('Error eliminando dispositivo');
        alert('Dispositivo eliminado correctamente');
        loadDevices();
    } catch (error) {
        console.error('Error al eliminar dispositivo:', error);
        alert('No se pudo eliminar el dispositivo.');
    }
}

function hideDeviceForm() {
    const section = document.getElementById('device-form-section');
    section.classList.remove('visible');
    section.classList.add('hidden');
    document.getElementById('device-form').reset();
}

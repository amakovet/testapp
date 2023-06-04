let page = 0;
let sortField = 'name';
let sortOrder = 'asc';

// Function to fetch cart items from the API
function fetchCartItems() {
    fetch(`http://localhost:8080/api/v1/carts?page=${page}&sort=${sortField},${sortOrder}`)
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('cart-items').getElementsByTagName('tbody')[0];
            tbody.innerHTML = ''; // Clear existing rows

            // Create a new row for each item
            data.content.forEach(item => {
                const row = tbody.insertRow();
                const nameCell = row.insertCell(0);
                const priceCell = row.insertCell(1);
                nameCell.textContent = item.name;
                priceCell.textContent = item.price;
            });
        });
}

// Event listeners for pagination buttons
document.getElementById('prevPage').addEventListener('click', () => {
    if (page > 0) {
        page--;
        fetchCartItems();
    }
});

document.getElementById('nextPage').addEventListener('click', () => {
    page++;
    fetchCartItems();
});

// Event listeners for sorting
document.getElementById('sortByName').addEventListener('click', () => {
    sortField = 'name';
    sortOrder = sortOrder === 'asc' ? 'desc' : 'asc';
    fetchCartItems();
});

document.getElementById('sortByPrice').addEventListener('click', () => {
    sortField = 'price';
    sortOrder = sortOrder === 'asc' ? 'desc' : 'asc';
    fetchCartItems();
});

// Fetch the initial data
fetchCartItems();

console.log("Hello from main.js")

        function searchBooks() {
            const title = document.getElementById('bookTitle').value;
            fetch(`/books/search?title=${encodeURIComponent(title)}`)
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('result');
                    resultDiv.innerHTML = '';
                    if (data.length > 0) {
                        const table = document.createElement('table');
                        const headerRow = table.insertRow();
                        const headers = ['ID', 'Title', 'Author', 'ISBN'];
                        headers.forEach(headerText => {
                            const headerCell = document.createElement('th');
                            headerCell.textContent = headerText;
                            headerRow.appendChild(headerCell);
                        });
                        data.forEach(book => {
                            const row = table.insertRow();
                            row.insertCell().textContent = book.id;
                            row.insertCell().textContent = book.title;
                            row.insertCell().textContent = book.author;
                            row.insertCell().textContent = book.isbn;
                        });
                        resultDiv.appendChild(table);
                    } else {
                        resultDiv.textContent = 'Không tìm thấy sách phù hợp';
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
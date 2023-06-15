// Function to fetch all students and display them

function getAllStudents() {
    fetch('http://localhost:8181/api/students')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('dtDynamicVerticalScrollExample');
            data.forEach(item => {
                const row = document.createElement('tr');
                const cell1 = document.createElement('td');
                cell1.textContent = item.id;
                row.appendChild(cell1);

                const cell2 = document.createElement('td');
                cell2.textContent = item.personalNo;
                row.appendChild(cell2);

                const cell3 = document.createElement('td');
                cell3.textContent = item.name;
                row.appendChild(cell3);

                const cell4 = document.createElement('td');
                cell4.textContent = item.lastName;
                row.appendChild(cell4);
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });

}



// Function to add a student
$('#addStudentForm').submit(function(e) {
    e.preventDefault();

    var name = $('#name').val();
    var lastname = $('#lastname').val();
    var personalno = $('#personalno').val();

    $.ajax({
        url: 'http://localhost:8181/api/add-student',
        type: 'POST',
        data: JSON.stringify({ name: name, lastName: lastname, personalNo: personalno  }),
        contentType: 'application/json',
        success: function() {
            $('#name').val('');
            $('#lastName').val('');
            $('#personalno').val('');
        }
    });


});

// Function to delete a student
$('#studentList').on('click', '.deleteBtn', function() {

    var id = $(this).data('id');

    $.ajax({
        url: 'http://localhost:8181/api/delete-student/' + id,
        type: 'DELETE',
    });
});

getAllStudents();


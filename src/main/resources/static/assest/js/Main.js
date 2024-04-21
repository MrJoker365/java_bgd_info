let block1 = document.getElementById("Rectangle1");
let block1Width = block1.offsetWidth;


var block2 = document.getElementById("Rectangle2");
//block2.style.border = "2px solid black"; // Встановлення границі
block2.style.left = block1Width + "px"; // Встановлення ширини на основі block1Width


// let row_1 = document.getElementsByClassName("row_1");

// let table_row_2 = document.getElementsByClassName()


function addRow() {
    var table = document.getElementById("info_table");
    var newRow = table.insertRow(-1);

    for (var i = 0; i < 7; i++) {
        var cell = newRow.insertCell(i);
        cell.innerHTML = "Новий текст " + (i + 1);
    }
}


 function addNewRow() {
    // Знайдіть таблицю і тіло таблиці
    var table = document.getElementById('info_table');
    var tbody = table.getElementsByTagName('tbody')[0];

    // Створіть новий рядок та додайте його до тіла таблиці
    var newRow = tbody.insertRow(tbody.rows.length);
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    var cell5 = newRow.insertCell(4);
    var cell6 = newRow.insertCell(5);
    var cell7 = newRow.insertCell(6);

    // Додайте поля вводу для користувача
    cell1.innerHTML = '<input type="text" placeholder="Значення 1">';
    cell2.innerHTML = '<input type="text" placeholder="Значення 2">';
    cell3.innerHTML = '<input type="text" placeholder="Значення 3">';
    cell4.innerHTML = '<input type="text" placeholder="Значення 1">';
    cell5.innerHTML = '<input type="text" placeholder="Значення 2">';
    cell6.innerHTML = '<input type="text" placeholder="Значення 3">';
    cell7.innerHTML = '<input type="text" placeholder="Значення 3">';
  };



function editCell() {
    // Отримуємо посилання на таблицю за ID
    let table = document.getElementById('info_table');

    // Отримуємо всі рядки таблиці
    let rows = table.getElementsByTagName('tr');

    // Перебираємо всі рядки
    for (let a = 0; a < rows.length; a++) {
        let row = rows[a];
        
        // Отримуємо всі комірки в поточному рядку
        let cells = row.getElementsByTagName('td');

        // Перебираємо всі комірки поточного рядка
        for (let i = 0; i < cells.length; i++) {
            let cell = cells[i];
            console.log(cell.textContent);

            cell.setAttribute("ondblclick", "makeEditable(this)")
        }
    }
}

  function makeEditable(element) {
    element.contentEditable = true;
    element.focus();
  }



        

  // Отримуємо посилання на таблицю
  var table = document.getElementById("info_table");

  // Додаємо обробник події click до таблиці
  table.addEventListener("click", function(event) {
      var target = event.target;
      
      // Перевіряємо, чи була клікнута клітинка з атрибутом contenteditable="true"
      if (target && target.getAttribute("contenteditable") === "true") {
          // Тут можна виконати додаткові дії після кліку на редагований текст
          console.log("Клік на редагованому тексті: " + target.innerText);
      }
  });

  // Додаємо обробник події input для відстеження редагування тексту
  document.addEventListener("input", function(event) {
      var target = event.target;
      
      // Перевіряємо, чи була внесена зміна в текст редагованої клітинки
      if (target && target.getAttribute("contenteditable") === "true") {
          // Тут можна виконати додаткові дії після редагування тексту
          console.log("Текст було змінено: " + target.innerText);
      }
  });









//   .............






let getAllInfoUrl = "http://localhost:8081/api/getAll";

fetch(getAllInfoUrl)
    .then(function (responce) {
        if (!responce.ok) {
            throw new Error("Щось пішлотне так...")
        }

        return responce.json;
    })
    .then(function(data){
        console.log(data);
    })
    .then(function(error){
        console.log('Fetch error: ' + error);
    });
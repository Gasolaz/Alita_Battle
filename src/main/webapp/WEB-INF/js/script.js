var select = document.getElementById('genderid');
var input = document.getElementById('gendertxt');
select.onchange = function() {
    input.value = select.value;
}
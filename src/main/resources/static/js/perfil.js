
$(document).ready(function () {
  if(document.getElementById("user-info-edit-errors") !== null){
	$("#modal-edit-user-info").modal('show');
  }

  $("#email").inputmask("email");
  $("#cpf").inputmask("999.999.999-99");
  $("#data-nascimento").inputmask("99/99/9999");
  $("#telefone").inputmask("(99)\\9\.9999-9999");
  $("#datepicker").datepicker({
    format: "dd/mm/yyyy",
    language: "pt-BR",
    autoclose: true,
    todayHighlight: true,
    startDate: new Date(1900, 1, 1),
    endDate: "-18y",
    });
  $(".numero").inputmask("9{*}", {"placeholder": "0"});
  
  var emailTooltip = $('#email')
  var tooltip = new bootstrap.Tooltip(emailTooltip,
  	{trigger: 'hover focus'})
});
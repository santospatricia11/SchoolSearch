$(document).ready(function () {
  $("#email").inputmask("email");
  $("#cnpj").inputmask("99.999.999/9999-99");
  $("#data-nascimento").inputmask("99/99/9999");
  $("#telefone").inputmask("(99)\\9\.9999-9999");
  $(".numero").inputmask("9{*}", {"placeholder": "0"});
  $(".mensalidade").inputmask("9{*}.9{2}", {"placeholder": "00"});

  $('.btn-proximo').click(function(){
	$(".nav-tabs-form-escola .active").parent().next("li").find("button").click()
  });
  $('.btn-anterior').click(function(){
	$(".nav-tabs-form-escola .active").parent().prev("li").find("button").click()
  });
  
  var idiomaTooltip = $("#idioma")
  var tooltip = new bootstrap.Tooltip(idiomaTooltip)
  
  $(".error").each(function(i, el){
	var object = $("#"+$(this).parents(".tab-pane").attr("id")+"-tab")
	if(i == 0){
		object.click();
	}
	object.css("color", "red")
	});

});
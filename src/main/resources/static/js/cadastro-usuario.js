$(document).ready(function () {
        $("#email").inputmask("email");
        $("#cpf").inputmask("999.999.999-99");
        $("#data-nascimento").inputmask("99/99/9999");
        $("#datepicker").datepicker({
          format: "dd/mm/yyyy",
          language: "pt-BR",
          autoclose: true,
          todayHighlight: true,
          startDate: new Date(1900, 1, 1),
          endDate: "-18y",
        });

        $("#showPassword .input-group-text").on("click", function (event) {
          event.preventDefault();
          var passInput = $("#senha");
          if (passInput.attr("type") === "password") {
            passInput.attr("type", "text");
            $("#showPassword i").addClass("fa-eye-slash");
            $("#showPassword i").removeClass("fa-eye");
          } else if (passInput.attr("type") === "text") {
            passInput.attr("type", "password");
            $("#showPassword i").removeClass("fa-eye-slash");
            $("#showPassword i").addClass("fa-eye");
          }
        });

        $("#showPassword .input-group-text").click().click();

        $("#confirmar-senha").on("click", function () {
          $("#senha").attr("type", "password");
          $("#showPassword i").removeClass("fa-eye-slash");
          $("#showPassword i").addClass("fa-eye");
        });
      $(".numero").inputmask("9{*}");
      $("#feminino").click();
      }
);
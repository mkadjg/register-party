$(document).ready(function () {

     toastr.options = {
         "closeButton": true,
         "debug": false,
         "newestOnTop": true,
         "progressBar": false,
         "positionClass": "toast-bottom-right",
         "preventDuplicates": false,
         "onclick": null,
         "showDuration": "300",
         "hideDuration": "1000",
         "timeOut": "2000",
         "extendedTimeOut": "1000",
         "showEasing": "swing",
         "hideEasing": "linear",
         "showMethod": "fadeIn",
         "hideMethod": "fadeOut"
     };

    $("form").submit(function(event){
        event.preventDefault();

        var nik = $("input[name=nik]").val();
        const body = {
            "nik" : nik,
            "password" : $("input[name=password]").val()
        }

        $.post({
            url: "/api/login/",
            contentType: "application/json",
            data : JSON.stringify(body),
            success: function(response){
                if (response.rc == "00") {
                    sessionStorage.setItem("nik", nik)
                    window.location.replace("/profile-anggota");
                } else {
                    toastr.warning(response.message, "Peringatan");
                }

            },
            error : function (response){
                toastr.error(response.message, "Error");
            }
        });
    });

});
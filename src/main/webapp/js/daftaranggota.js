$(document).ready(function(){

     toastr.options = {
         "closeButton": true,
         "debug": false,
         "newestOnTop": true,
         "progressBar": false,
         "positionClass": "toast-bottom-right",
         "preventDuplicates": false,
         "onclick": null,
         "showDuration": "3000",
         "hideDuration": "1000",
         "timeOut": "2000",
         "extendedTimeOut": "1000",
         "showEasing": "swing",
         "hideEasing": "linear",
         "showMethod": "fadeIn",
         "hideMethod": "fadeOut"
     };

    $.get({
        url: "/jenis-kelamin/find-all",
        contentType: "application/json",
        success: function(response){
            var data = response.data;
            var length = data.length;
            var content = '';
            for (var i = 0; i < length; i++){
                content += '<option value="' + data[i].idJenisKelamin + '" >' + data[i].namaJenisKelamin + '</option>';
            }
            $('#jenisKelamin').html(content);
        }
    });

    $.get({
        url: "/pendidikan/find-all",
        contentType: "application/json",
        success: function(response){
            var data = response.data;
            var length = data.length;
            var content = '';
            for (var i = 0; i < length; i++){
                content += '<option value="' + data[i].idPendidikan + '" >' + data[i].namaPendidikan + '</option>';
            }
            $('#pendidikan').html(content);
        }
    });

    $.get({
        url: "/pekerjaan/find-all",
        contentType: "application/json",
        success: function(response){
            var data = response.data;
            var length = data.length;
            var content = '';
            for (var i = 0; i < length; i++){
                content += '<option value="' + data[i].idPekerjaan + '" >' + data[i].namaPekerjaan + '</option>';
            }
            $('#pekerjaan').html(content);
        }
    });

    $.get({
        url: "/status-perkawinan/find-all",
        contentType: "application/json",
        success: function(response){
            var data = response.data;
            var length = data.length;
            var content = '';
            for (var i = 0; i < length; i++){
                content += '<option value="' + data[i].idStatusPerkawinan + '" >' + data[i].namaStatusPerkawinan + '</option>';
            }
            $('#statusPerkawinan').html(content);
        }
    });

    $.get({
        url: "/kecamatan/find-all",
        contentType: "application/json",
        success: function(response){
            var data = response.data;
            var length = data.length;
            var content = '';
            for (var i = 0; i < length; i++){
                content += '<option value="' + data[i].idKecamatan + '" >' + data[i].namaKecamatan + '</option>';
            }
            $('#kecamatan').html(content);
        }
    });

    $("#kecamatan").change(function(){
        var idKecamatan = $('select[name=kecamatan]').val().toString();
        $.ajax({
            url : 'kelurahan/find-all/' + idKecamatan,
            type: 'GET',
            success : function(response) {
                var data = response.data;
                var length = data.length;
                var content = '';
                for (var i = 0; i < length; i++){
                    content += '<option value="' + data[i].idKelurahan + '" >' + data[i].namaKelurahan + '</option>';
                }
                $('#kelurahan').html(content);
            }
        });
    });

    $("#upload-ktp").change(function(){
        var file = $("#upload-ktp").get(0).files[0];
        if (file) {
            var reader = new FileReader();
            reader.onload = function(event){
                $("#ktp").attr('src', event.target.result);
            }
            reader.readAsDataURL(file);
            $("#filename-ktp").html(file.name);
        }
    });

    $("#upload-closeup").change(function(){
        var file = $("#upload-closeup").get(0).files[0];
        if (file) {
            var reader = new FileReader();
            reader.onload = function(event){
                $("#closeup").attr('src', event.target.result);
            }
            reader.readAsDataURL(file);
            $("#filename-closeup").html(file.name);
        }
    });

    $('#submit').click(function(e){
        e.preventDefault();

        $('#submit').last().addClass("is-loading");

        var nama = $('input[name=nama]').val();
        var nik = $('input[name=nik]').val();
        var email = $('input[name=email]').val();
        var password = $('input[name=password]').val();
        var newPassword = $('input[name=ulangiPassword]').val();
        var nomorHandphone = $('input[name=nomorHandphone]').val();
        var tempatLahir = $('input[name=tempatLahir]').val();
        var tanggalLahir = $('input[name=tanggalLahir]').val();
        var jenisKelamin = $('select[name=jenisKelamin]').val();
        var pendidikan = $('select[name=pendidikan]').val();
        var statusPerkawinan = $('select[name=statusPerkawinan]').val();
        var pekerjaan = $('select[name=pekerjaan]').val();
        var alamat = $('textarea[name=alamat]').val();
        var kecamatan = $('select[name=kecamatan]').val();
        var kelurahan = $('select[name=kelurahan]').val();

        if (!$('#pernyataan').is(":checked")) {
            toastr.warning("Harap setujui pernyataan terlebih dahulu!", "Peringatan");
            $('#submit').last().removeClass("is-loading");
            return;
        }

        if (password != newPassword) {
            toastr.warning("Password dan ulangi password tidak sama!", "Peringatan");
            $('#submit').last().removeClass("is-loading");
            return;
        }

        var body = {
            "namaAnggota": nama,
            "nik": nik,
            "email": email,
            "password": password,
            "noHandphone": nomorHandphone,
            "tempatLahir": tempatLahir,
            "tanggalLahir": tanggalLahir,
            "idJenisKelamin": jenisKelamin,
            "idPendidikan": pendidikan,
            "idStatusPerkawinan": statusPerkawinan,
            "idPekerjaan": pekerjaan,
            "alamat": alamat,
            "idKecamatan": kecamatan,
            "idKelurahan": kelurahan
        }

        $.post({
            url: "/api/anggota/save",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(body),
            success: function(e){
                if (e.rc == "00") {
                    var data = e.data;
                    var idAnggota = data.idAnggota;

                    var formDataKtp = new FormData();
                    formDataKtp.append('foto', $("#upload-ktp").get(0).files[0]);
                    $.ajax({
                        url: "/api/anggota/upload-ktp/" + idAnggota,
                        data: formDataKtp,
                        type: 'POST',
                        contentType: false,
                        processData: false,
                        success: function(response){
                            console.log(response);
                        }
                    });

                    var formDataCloseup = new FormData();
                    formDataCloseup.append('foto', $("#upload-closeup").get(0).files[0]);
                    $.ajax({
                        url: "/api/anggota/upload-closeup/" + idAnggota,
                        data: formDataCloseup,
                        type: 'POST',
                        contentType: false,
                        processData: false,
                        success: function(response){
                            console.log(response);
                        }
                    });
                }
                $('.input').val("");
                $('.select').val("");
                $('.textarea').val("");
               $('input:checkbox').removeAttr('checked');
                $('#upload-ktp').val('');
                $('#upload-closeup').val('');
                $("#filename-ktp").html("...");
                $("#filename-closeup").html("...");
                $("#ktp").attr('src', "#");
                $("#closeup").attr('src', "#");
                $('#submit').last().removeClass("is-loading");
                toastr.success("Pendaftaran Berhasil", "Sukses");
            }
        });

    });
});
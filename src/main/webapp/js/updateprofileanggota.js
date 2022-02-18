$(document).ready(function(){

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

    $.get({
        url: "/api/anggota/detail?nik=" + sessionStorage.getItem("nik"),
        contentType: "application/json",
        success: function(response){
            console.log(response);
            if (response.rc == "00") {
                $("input[name=idAnggota]").val(response.data.idAnggota)
                $("input[name=nama]").val(response.data.namaAnggota)
                $("input[name=nik").val(response.data.nik)
                $("input[name=email").val(response.data.email)
                $("input[name=nomorHandphone").val(response.data.noHandphone)
                $("input[name=tempatLahir").val(response.data.tempatLahir)
                $("input[name=tanggalLahir").val(new Date(response.data.tanggalLahir).toDateString())
                $("select[name=jenisKelamin").val(response.data.jenisKelamin.idJenisKelamin)
                $("select[name=pendidikan").val(response.data.pendidikan.idPendidikan)
                $("select[name=pekerjaan").val(response.data.pekerjaan.idPekerjaan)
                $("select[name=statusPerkawinan").val(response.data.statusPerkawinan.idStatusPerkawinan)
                $("textarea[name=alamat").val(response.data.alamat)
                $("select[name=kecamatan").val(response.data.kecamatan.idKecamatan)
                $.ajax({
                    url : 'kelurahan/find-all/' + response.data.kecamatan.idKecamatan,
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
                $("select[name=kelurahan").val(response.data.kelurahan.idKelurahan)
                $("#closeup").get(0).src = "data:image/png;base64," + response.data.fotoCloseup;
                $("#ktp").get(0).src = "data:image/png;base64," + response.data.fotoKtp;
            }

        }
    });

    $('#submit').click(function(e){
        e.preventDefault();

        $('#submit').last().addClass("is-loading");

        var idAnggota = $('input[name=idAnggota]').val();
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
            "idAnggota": idAnggota,
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

        console.log(body);

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
                window.location.replace("/profile-anggota");
                toastr.success("Pendaftaran Berhasil", "Sukses");
            }
        });

    });
});
$(document).ready(function(){

    var tableAnggota = $('#tableAnggota').DataTable({
        "scrollX": true,
        "initComplete": function(settings, json) {
            $('.dataTables_scrollBody thead tr').css({visibility:'collapse'});
        },
        columns: [
            { data: 'nomorUrutAnggota'},
            { data: 'namaAnggota'},
            { data: 'nik'},
            { data: 'nomorIndukAnggota'},
            { data: 'tanggalDaftar'},
            { data: 'email'},
            { data: 'tempatLahir'},
            { data: 'tanggalLahir'},
            { data: 'jenisKelamin.namaJenisKelamin'},
            { data: 'pendidikan.namaPendidikan'},
            { data: 'pekerjaan.namaPekerjaan'},
            { data: 'statusPerkawinan.namaStatusPerkawinan'},
            { data: 'kecamatan.namaKecamatan'},
            { data: 'kelurahan.namaKelurahan'}
        ]
    });

    var idKecamatan = $("select[name=kecamatan]").val() != null && $("select[name=kecamatan]").val() != undefined ? $("select[name=kecamatan]").val() : 0;
    var idKelurahan = $("selectt[name=kelurahan]").val() != null && $("select[name=kelurahan]").val() != undefined ? $("select[name=kelurahan]").val() : 0;

    $.get({
        url: "/api/anggota/find-all?nama=" + $("input[name=nama]").val() +
                                  "&nik=" +  $("input[name=nik]").val() +
                                  "&nomorIndukAnggota=" +  $("input[name=nomorIndukAnggota]").val() +
                                  "&email=" +  $("input[name=email]").val() +
                                  "&idKecamatan=" +  idKecamatan +
                                  "&idKelurahan=" +  idKelurahan
        ,
        contentType: "application/json",
        success: function(response){
            console.log(response);
            if (response.rc == "00") {
                tableAnggota.rows.add(response.data).draw();
            }
        }
    });

     $("#refresh").click(function(e){
        e.preventDefault();
        var idKecamatan = $("select[name=kecamatan]").val() != null && $("select[name=kecamatan]").val() != undefined ? $("select[name=kecamatan]").val() : 0;
        var idKelurahan = $("select[name=kelurahan]").val() != null && $("select[name=kelurahan]").val() != undefined ? $("select[name=kelurahan]").val() : 0;

        $.get({
            url: "/api/anggota/find-all?nama=" + $("input[name=nama]").val() +
                                      "&nik=" +  $("input[name=nik]").val() +
                                      "&nomorIndukAnggota=" +  $("input[name=nomorIndukAnggota]").val() +
                                      "&email=" +  $("input[name=email]").val() +
                                      "&idKecamatan=" +  idKecamatan +
                                      "&idKelurahan=" +  idKelurahan
            ,
            contentType: "application/json",
            success: function(response){
                console.log(response);
                if (response.rc == "00") {
                    tableAnggota.clear().draw();
                    tableAnggota.rows.add(response.data).draw();
                }
            }
        });
    });

    $("#generate").click(function(e){
        e.preventDefault();
        var idKecamatan = $("select[name=kecamatan]").val() != null && $("select[name=kecamatan]").val() != undefined ? $("select[name=kecamatan]").val() : 0;
        var idKelurahan = $("select[name=kelurahan]").val() != null && $("select[name=kelurahan]").val() != undefined ? $("select[name=kelurahan]").val() : 0;

//        $.get({
//            url: "/report/rekap-anggota?nama=" + $("input[name=nama]").val() +
//                                      "&nik=" +  $("input[name=nik]").val() +
//                                      "&nomorIndukAnggota=" +  $("input[name=nomorIndukAnggota]").val() +
//                                      "&email=" +  $("input[name=email]").val() +
//                                      "&idKecamatan=" +  idKecamatan +
//                                      "&idKelurahan=" +  idKelurahan
//            ,
//            contentType: "application/json",
//            responseType: 'blob',
//            success: function(response){
//                var blob = new Blob([response], {type: 'application/vnd.ms-excel'});
//                var downloadUrl = URL.createObjectURL(blob);
//                var a = document.createElement("a");
//                a.href = downloadUrl;
//                a.download = "data.xlsx";
//                document.body.appendChild(a);
//                a.click();
//            }
//        });

        var xhr = new XMLHttpRequest();
        xhr.open('GET', "/report/rekap-anggota?nama=" + $("input[name=nama]").val() +
                       "&nik=" +  $("input[name=nik]").val() +
                       "&nomorIndukAnggota=" +  $("input[name=nomorIndukAnggota]").val() +
                       "&email=" +  $("input[name=email]").val() +
                       "&idKecamatan=" +  idKecamatan +
                       "&idKelurahan=" +  idKelurahan, true);
        xhr.responseType = 'blob';
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function(e) {
            var blob = new Blob([this.response], {type: 'application/vnd.ms-excel'});
            var downloadUrl = URL.createObjectURL(blob);
            var a = document.createElement("a");
            a.href = downloadUrl;
            a.download = "rekap-anggota.xlsx";
            document.body.appendChild(a);
            a.click();
        };
        xhr.send();
    });

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
            var content = '<option value="0" >Semua Kecamatan</option>';
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
                var content = '<option value="0" >Semua Kelurahan</option>';
                for (var i = 0; i < length; i++){
                    content += '<option value="' + data[i].idKelurahan + '" >' + data[i].namaKelurahan + '</option>';
                }
                $('#kelurahan').html(content);
            }
        });
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

//        $.post({
//            url: "/api/anggota/save",
//            contentType: "application/json",
//            dataType: "json",
//            data: JSON.stringify(body),
//            success: function(e){
//                if (e.rc == "00") {
//                    var data = e.data;
//                    var idAnggota = data.idAnggota;
//
//                    var formDataKtp = new FormData();
//                    formDataKtp.append('foto', $("#upload-ktp").get(0).files[0]);
//                    $.ajax({
//                        url: "/api/anggota/upload-ktp/" + idAnggota,
//                        data: formDataKtp,
//                        type: 'POST',
//                        contentType: false,
//                        processData: false,
//                        success: function(response){
//                            console.log(response);
//                        }
//                    });
//
//                    var formDataCloseup = new FormData();
//                    formDataCloseup.append('foto', $("#upload-closeup").get(0).files[0]);
//                    $.ajax({
//                        url: "/api/anggota/upload-closeup/" + idAnggota,
//                        data: formDataCloseup,
//                        type: 'POST',
//                        contentType: false,
//                        processData: false,
//                        success: function(response){
//                            console.log(response);
//                        }
//                    });
//                }
//                $('.input').val("");
//                $('.select').val("");
//                $('.textarea').val("");
//               $('input:checkbox').removeAttr('checked');
//                $('#upload-ktp').val('');
//                $('#upload-closeup').val('');
//                $("#filename-ktp").html("...");
//                $("#filename-closeup").html("...");
//                $("#ktp").attr('src', "#");
//                $("#closeup").attr('src', "#");
//                $('#submit').last().removeClass("is-loading");
//                toastr.success("Pendaftaran Berhasil", "Sukses");
//            }
//        });

    });
});
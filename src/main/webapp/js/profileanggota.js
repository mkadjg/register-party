$(document).ready(function(){
    $.get({
        url: "/api/anggota/detail?nik=" + sessionStorage.getItem("nik"),
        contentType: "application/json",
        success: function(response){
            if (response.rc == "00") {
                $("#nama_lengkap").html(response.data.namaAnggota)
                $("#nik").html(response.data.nik)
                $("#nomor_induk_anggota").html(response.data.nomorIndukAnggota)
                $("#tanggal_daftar").html(new Date(response.data.tanggalDaftar).toDateString())
                $("#email").html(response.data.email)
                $("#no_handphone").html(response.data.noHandphone)
                $("#tempat_lahir").html(response.data.tempatLahir)
                $("#tanggal_lahir").html(new Date(response.data.tanggalLahir).toDateString())
                $("#jenis_kelamin").html(response.data.jenisKelamin.namaJenisKelamin)
                $("#pendidikan").html(response.data.pendidikan.namaPendidikan)
                $("#pekerjaan").html(response.data.pekerjaan.namaPekerjaan)
                $("#status_perkawinan").html(response.data.statusPerkawinan.namaStatusPerkawinan)
                $("#alamat").html(response.data.alamat)
                $("#kecamatan").html(response.data.kecamatan.namaKecamatan)
                $("#kelurahan").html(response.data.kelurahan.namaKelurahan)
                $("#foto_closeup").get(0).src = "data:image/png;base64," + response.data.fotoCloseup;
                $("#foto_ktp").get(0).src = "data:image/png;base64," + response.data.fotoKtp;
            }

        }
    });
});
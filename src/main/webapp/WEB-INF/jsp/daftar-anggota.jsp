<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html class="has-navbar-fixed-top">
    <head>
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Partai Berkarya </title>
        <link rel="shortcut icon" href="${contextPath}/assets/logo-berkarya-full.jpg" type="image/x-icon">
        <link href="${contextPath}/webjars/toastr/2.1.2/build/toastr.min.css" rel="stylesheet">
        <link href="${contextPath}/webjars/bulma/0.9.1/css/bulma.min.css" rel="stylesheet">
        <link href="${contextPath}/css/daftaranggota.css" rel="stylesheet">
    </head>

    <body>

        <nav class="navbar has-shadow is-fixed-top" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                <a class="navbar-item" href="https://berkarya.or.id/">
                    <img src="${contextPath}/assets/logo-berkarya.png">
                </a>

                <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                </a>
            </div>

            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-start">
                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">
                            Profile
                        </a>
                        <div class="navbar-dropdown">
                            <a class="navbar-item">
                                Sejarah
                            </a>
                            <a class="navbar-item">
                                Visi & Misi
                            </a>
                            <a class="navbar-item">
                                Tugas & Fungsi
                            </a>
                            <a class="navbar-item">
                                Logo
                            </a>
                        </div>
                    </div>

                    <a class="navbar-item">
                        Arsip
                    </a>

                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">
                            More
                        </a>

                        <div class="navbar-dropdown">
                            <a class="navbar-item">
                                About
                            </a>
                            <a class="navbar-item">
                                Jobs
                            </a>
                            <a class="navbar-item">
                                Contact
                            </a>
                            <hr class="navbar-divider">
                            <a class="navbar-item">
                                Report an issue
                            </a>
                        </div>
                    </div>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <a class="button is-warning">
                                <strong>Daftar Anggota</strong>
                            </a>
                            <a href="/login/" class="button is-light">
                                Log in
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <div class="columns offtop">
            <div class="column is-6">
                <form class="box">
                    <h3 class="subtitle is-3">Pendaftaran Kader Online</h3>
                    <div class="field">
                        <label class="label">Nama Lengkap</label>
                        <div class="control">
                            <input class="input" name="nama" type="text" placeholder="Nama Lengkap">
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">NIK / KTP</label>
                        <div class="control">
                            <input class="input" name="nik" type="text" placeholder="NIK / KTP">
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Email</label>
                        <div class="control">
                            <input class="input" name="email" type="email" placeholder="ex: example@gmail.com">
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Password</label>
                        <div class="control">
                            <input class="input" name="password" type="password" placeholder="Minimal 8 karakter">
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Ulangi Password</label>
                        <div class="control">
                            <input class="input" name="ulangiPassword" type="password" placeholder="Ulangi password, minimal 8 karakter">
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Nomor Handphone</label>
                        <div class="control">
                            <input class="input" name="nomorHandphone" type="number" placeholder="Nomor handphone">
                        </div>
                    </div>

                    <div class="columns">
                        <div class="column is-half">
                            <div class="field">
                                <label class="label">Tempat Lahir</label>
                                <div class="control">
                                    <input class="input" name="tempatLahir" type="text" placeholder="Tempat lahir">
                                </div>
                            </div>
                        </div>
                        <div class="column is-half">
                            <div class="field">
                                <label class="label">Tanggal Lahir</label>
                                <div class="control">
                                    <input class="input" name="tanggalLahir" type="date" placeholder="Tanggal Lahir">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="columns">
                        <div class="column is-half">
                            <label class="label">Jenis Kelamin</label>
                            <div class="select is-info">
                                <div class="control">
                                    <select name="jenisKelamin" id="jenisKelamin">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="column is-half">
                            <label class="label">Pendidikan Terakhir</label>
                            <div class="select is-info">
                                <div class="control">
                                    <select name="pendidikan" id="pendidikan">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="columns">
                        <div class="column is-half">
                            <label class="label">Status Perkawinan</label>
                            <div class="select is-info">
                                <div class="control">
                                    <select name="statusPerkawinan" id="statusPerkawinan">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="column is-half">
                            <label class="label">Pekerjaan</label>
                            <div class="select is-info">
                                <div class="control">
                                    <select name="pekerjaan" id="pekerjaan">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Alamat</label>
                        <textarea class="textarea" name="alamat" placeholder="Tuliskan alamat jalan / RT.RW / Dusun"></textarea>
                    </div>

                    <div class="field">
                        <label class="label">Kecamatan</label>
                        <div class="select is-info">
                            <div class="control">
                                <select name="kecamatan" id="kecamatan">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Kelurahan</label>
                        <div class="select is-info">
                            <div class="control">
                                <select name="kelurahan" id="kelurahan">
                                </select>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="column is-half">

                <form class="box">

                    <article class="message">
                        <div class="message-header">
                            <p>Pernyataan</p>
                            <button class="delete" aria-label="delete"></button>
                        </div>
                        <div class="message-body">
                            <strong>Dengan ini saya menyatakan :</strong>
                            <br/> <br/>
                            <ul class="list" style="list-style-type:disc; line-height:180%">
                            <li> Bersedia menjadi anggota PARTAI BERKARYA </li>
                            <li> Bersedia mentaati AD/ART PARTAI BERKARYA dan segala ketentuan yang berlaku </li>
                            <li> Menjunjung tinggi Kode PARTAI BERKARYA Indonesia, dan setia menjaga dan membela nama baik organisasi PARTAI BERKARYA  </li>
                            <li> Bersedia melaksanakan dan memenuhi segala kewajiban sebagaimana seharusnya. </li> <br/>
                            </ul>
                            Apabila saya melanggar pernyataan di atas, saya bersedia menerima sanksi berupa pencabutan status sebagai anggota PARTAI BERKARYA.
                            <br/><br/>
                            Demikianlah pernyataan ini saya buat dengan sesungguhnya, dan semua data yang saya cantumkan dalam formulir online ini adalah benar adanya.
                        </div>
                    </article>

                    <div class="field">
                        <label class="checkbox">
                            <input type="checkbox" name="pernyataan" id="pernyataan">
                            Saya sudah membaca pernyataan diatas dan saya setuju
                        </label>
                    </div>

                    <div class="field">
                        <div class="file is-warning has-name">
                            <label class="file-label">
                                <input class="file-input" type="file" name="upload-ktp" id="upload-ktp">
                                <span class="file-cta">
                                    <span class="file-icon">
                                        <i class="fas fa-upload"></i>
                                     </span>
                                    <span class="file-label" style="width:155px">
                                        Upload KTP
                                    </span>
                                </span>
                                <span class="file-name" id="filename-ktp">
                                     ...
                                </span>
                            </label>
                        </div>
                    </div>

                    <div class="field">
                        <img id="ktp" src="#" alt="Gambar KTP" style="height: 220px; width: 360px" />
                    </div>

                    <div class="field">
                        <div class="file is-warning has-name">
                            <label class="file-label">
                                <input class="file-input" type="file" name="upload-closeup" id="upload-closeup">
                                <span class="file-cta">
                                    <span class="file-icon">
                                        <i class="fas fa-upload"></i>
                                     </span>
                                    <span class="file-label">
                                        Upload Foto Close Up
                                    </span>
                                </span>
                                <span class="file-name" id="filename-closeup">
                                     ...
                                </span>
                            </label>
                        </div>
                    </div>

                    <div class="field">
                        <img id="closeup" src="#" alt="Gambar Closeup" style="height: 160px; width: 135px" />
                    </div>

                    <button type="submit" id="submit" class="button is-warning"><strong>Daftar Sekarang</strong></button>
                </form>
            </div>
        </div>

        <footer class="footer">
            <div class="footer-top">
                <div class="container">
                    <div class="columns">

                        <div class="column is-one-quarter">
                            <div class="footer-info">
                                <!-- <img src="https://berkarya.or.id/assets/upload/image/logob.png" alt="logo" /> -->
                                <h3 class="title is-4"style="color:#000">Partai Berkarya<br>(Beringin Karya)</h3>
                                <p>
                                  </p><p>Jl. Taman Margasatwa Raya No.11, RT.1/RW.1, Ragunan, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540</p><br>

                                <p></p>
                            </div>
                        </div>

                        <div class="column is-one-quarter">

                             <strong>Telepon:</strong> +6281218051606<br>
                             <strong>Email:</strong> info@berkarya.or.id<br>
                        </div>

                        <div class="column is-one-quarter">
                              <a href="https://berkarya.or.id/daftar" class="button is-warning" style="background:orange">Gabung Bersama Kami</a><br><br>
                              <a href="#"><img src="https://berkarya.or.id/assets/images/playstore.png" style="width:200px; border-radius:10px"></a><br><br>
                              <a href="#"><img src="https://berkarya.or.id/assets/images/applestore.png" style="width:200px; border-radius:10px"></a>
                        </div>

                        <div class="column is-one-quarter">
                              <h4>Maps</h4>
                              <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15863.139060243098!2d106.8230315!3d-6.2919944!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xe2788630e044bb99!2sDPP%20Partai%20Berkarya!5e0!3m2!1sid!2sid!4v1629124561928!5m2!1sid!2sid" width="300" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                        </div>
                    </div>
                </div>
            </footer>

    <script src="${contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/webjars/toastr/2.1.2/build/toastr.min.js"></script>
    <script src="${contextPath}/js/daftaranggota.js"></script>
    </body>
</html>
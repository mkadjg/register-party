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
        <link href="${contextPath}/css/profileanggota.css" rel="stylesheet">
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
                            <a href="/login" class="button is-light">
                                Logout
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <div class="columns offtop">
            <div class="column is-half">
                <div class="card">
                     <header class="card-header">
                        <p style="font-size: 20px" class="card-header-title">
                            Profile Anggota
                        </p>
                    </header>
                    <div class="card-content">
                        <article class="media">
                            <div class="media-content">
                                <div class="content">
                                    <table class="table is-fullwidth">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <img style="height: 160px; width: 135px; border-radius: 5px" id="foto_closeup" alt="Foto Closeup">
                                                </td>
                                                <td>
                                                    <img style="height: 160px; width: 280px; border-radius: 5px" id="foto_ktp" alt="KTP">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><strong>Nama Lengkap</strong></td>
                                                <td id="nama_lengkap"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>NIK / KTP</strong></td>
                                                 <td id="nik"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Nomor Induk Anggota</strong></td>
                                                <td id="nomor_induk_anggota"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Tanggal Daftar</strong></td>
                                                 <td id="tanggal_daftar"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Email</strong></td>
                                                <td id="email"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>No Handphone</strong></td>
                                                 <td id="no_handphone"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Tempat Lahir</strong></td>
                                                 <td id="tempat_lahir"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Tanggal Lahir</strong></td>
                                                 <td id="tanggal_lahir"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Jenis Kelamin</strong></td>
                                                 <td id="jenis_kelamin"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Pendidikan</strong></td>
                                                 <td id="pendidikan"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Pekerjaan</strong></td>
                                                <td id="pekerjaan"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Status Perkawinan</strong></td>
                                                <td id="status_perkawinan"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Alamat</strong></td>
                                                <td id="alamat"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Kecamatan</strong></td>
                                                 <td id="kecamatan"></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Kelurahan</strong></td>
                                                 <td id="kelurahan"></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </article>
                    </div>
                    <footer class="card-footer">
                        <a href="/update-profile-anggota" class="card-footer-item">Edit</a>
                    </footer>
                </div>
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
                            <input type="checkbox" checked disabled="true" name="pernyataan" id="pernyataan">
                            Saya sudah membaca pernyataan diatas dan saya setuju
                        </label>
                    </div>
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
    <script src="${contextPath}/js/profileanggota.js"></script>
    </body>
</html>
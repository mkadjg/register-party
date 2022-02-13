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
        <link href="${contextPath}/webjars/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="${contextPath}/css/admin.css" rel="stylesheet">
        <link href="${contextPath}/css/jquery_datatable.css" rel="stylesheet">
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
                            <a href="/login-admin" class="button is-light">
                                Logout
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <div class="columns offtop" style="margin: 10px">
            <div class="column is-10">
                <h4 class="subtitle is-4">Rekap Data Anggota</h4>
            </div>
        </div>

        <div class="columns offtop" style="margin: 10px">
            <div class="column is-12">
                <form class="box">
                    <h4 class="subtitle is-4">Filter</h4>
                    <div class="columns">
                        <div class="column is-4">
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
                        </div>
                        <div class="column is-4">
                            <div class="field">
                                <label class="label">Nomor Induk Anggota</label>
                                <div class="control">
                                    <input class="input" name="nomorIndukAnggota" type="text" placeholder="Nomor Induk Anggota">
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Email</label>
                                <div class="control">
                                    <input class="input" name="email" type="text" placeholder="Email">
                                </div>
                            </div>
                        </div>

                        <div class="column is-4">
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
                        </div>

                    </div>
                    <button id="refresh" class="button is-success">
                        <span class="icon is-small">
                          <i class="fas fa-refresh"></i>
                        </span>
                        <span>Refresh</span>
                    </button>
                    <button id="generate" class="button is-success">
                        <span class="icon is-small">
                          <i class="fas fa-file-excel"></i>
                        </span>
                        <span>Generate Excel</span>
                    </button>
                </form>
            </div>
        </div>

        <div class="columns offtop" style="margin: 20px">
            <div class="column is-12 box">
                <table id="tableAnggota" class="table display nowrap " style="width: 100%;">
                    <thead class="table-container">
                        <tr>
                            <th>Nomor Urut</th>
                            <th>Nama Anggota</th>
                            <th>NIK / KTP</th>
                            <th>Nomor Induk Anggota</th>
                            <th>Tanggal Daftar</th>
                            <th>Email</th>
                            <th>Tempat Lahir</th>
                            <th>Tanggal Lahir</th>
                            <th>Jenis Kelamin</th>
                            <th>Pendidikan</th>
                            <th>Pekerjaan</th>
                            <th>Status Perkawinan</th>
                            <th>Kecamatan</th>
                            <th>Kelurahan</th>
                        </tr>
                    </thead>
                </table>
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
    <script src="${contextPath}/js/admin.js"></script>
    <script src="${contextPath}/js/jquery_datatable.js"></script>
    <script src="http://cdn.jsdelivr.net/g/filesaver.js"></script>
    </body>
</html>
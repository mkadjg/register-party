package com.registerparty.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouteController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/daftar-anggota", method = RequestMethod.GET)
    public String daftarAnggota() {
        return "daftar-anggota";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/profile-anggota", method = RequestMethod.GET)
    public String profile() {
        return "profile-anggota";
    }

}

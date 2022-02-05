package com.registerparty.service;

import com.registerparty.model.Anggota;
import com.registerparty.payload.LoginPayload;
import com.registerparty.payload.Response;
import com.registerparty.repository.AnggotaRepository;
import com.registerparty.utility.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AnggotaRepository anggotaRepository;

    @Override
    public Response<Object> login(LoginPayload payload) {
        Response<Object> response = new Response<>();
        try {
            Anggota anggota = anggotaRepository.findByNik(payload.getNik()).orElse(null);
            if (anggota == null) {
                response.setError("01", "NIK tidak terdaftar!");
            } else {
                String hexPassword = HashUtil.SHA_256.digestAsHex(payload.getPassword());
                if (hexPassword.equals(anggota.getPassword())) {
                    response.setSuccess("Login Berhasil", anggota);
                } else {
                    response.setError("02", "Password Salah!");
                }
            }
            return response;
        } catch (Exception e) {
            response.setError("Internal Server Error!");
            return response;
        }
    }
}

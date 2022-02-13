package com.registerparty.service;

import com.registerparty.payload.LoginPayload;
import com.registerparty.payload.Response;

public interface LoginService {
    Response<Object> login(LoginPayload payload);
    Response<Object> loginAdmin(LoginPayload payload);
}

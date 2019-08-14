package com.liudao51.datacenter.core.controller;

import com.liudao51.datacenter.core.protocol.account.LoginReq;
import com.liudao51.datacenter.core.response.ApiResponseBody;

/**
 * class:
 * <p>
 * Created by jewel on 2019/8/5.
 */
public interface IAccountController extends IBaseController {
    ApiResponseBody login(LoginReq req) throws Exception;

    ApiResponseBody logout() throws Exception;

    ApiResponseBody notAuthorize() throws Exception;

    ApiResponseBody loginIndex() throws Exception;
}

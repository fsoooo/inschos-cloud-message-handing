package com.inschos.cloud.message.handing.access.rpc.client;

import com.inschos.cloud.message.handing.access.rpc.bean.AccountBean;
import com.inschos.cloud.message.handing.access.rpc.service.AccountService;
import com.inschos.cloud.message.handing.assist.kit.L;
import hprose.client.HproseHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountClient {

    @Value("${rpc.remote.account.host}")
    private String host;

    private final String uri = "/rpc/account";


    private AccountService getService(){
        return new HproseHttpClient(uri).useService(AccountService.class);
    }

    public AccountBean getAccount(String token){
        try {
            AccountService service = getService();
            return service!=null?service.getAccount(token):null;

        }catch (Exception e){
            L.log.error("remote fail {}",e.getMessage(),e);
            return null;
        }
    }

    public AccountBean findByUuid(String uuid){
        try {
            AccountService service = getService();
            return service!=null?service.findByUuid(uuid):null;

        }catch (Exception e){
            L.log.error("remote fail {}",e.getMessage(),e);
            return null;
        }
    }
}

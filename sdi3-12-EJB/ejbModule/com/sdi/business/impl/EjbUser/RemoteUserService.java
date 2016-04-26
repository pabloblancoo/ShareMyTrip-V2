package com.sdi.business.impl.EjbUser;

import javax.ejb.Remote;

import com.sdi.business.UserService;

@Remote
public interface RemoteUserService extends UserService {

}

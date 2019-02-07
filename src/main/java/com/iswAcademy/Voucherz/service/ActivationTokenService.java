package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.dao.IActivationTokenDao;
import com.iswAcademy.Voucherz.domain.ActivationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationTokenService implements IActivationTokenService{

    @Autowired
    IActivationTokenDao iActivationTokenDao;

    @Override
    public ActivationToken createActivationToken(ActivationToken activationToken) {
        return iActivationTokenDao.create(activationToken);
    }
}

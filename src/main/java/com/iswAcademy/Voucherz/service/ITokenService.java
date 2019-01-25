package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.domain.PasswordReset;
import com.iswAcademy.Voucherz.domain.PasswordResetToken;
import com.iswAcademy.Voucherz.domain.User;

public interface ITokenService {
    public PasswordResetToken createToken (PasswordResetToken token);
}

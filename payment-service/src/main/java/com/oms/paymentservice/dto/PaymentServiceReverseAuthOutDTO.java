package com.oms.paymentservice.dto;

import com.oms.dto.AuthorizationResponseDto;

public class PaymentServiceReverseAuthOutDTO {
    private AuthorizationResponseDto retVal;

    public AuthorizationResponseDto getRetVal() {
        return retVal;
    }

    public void setRetVal(AuthorizationResponseDto retVal) {
        this.retVal = retVal;
    }

}

package com.service.employee.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody<T> {

    private String resultMsg;

    private T resultData;

    private ErrorData errorData;

    public void setSuccessResponse(T resultData) {
        this.resultMsg = "Success";
        this.resultData = resultData;
    }

    public void setFailResponse(ErrorData errorData) {
        this.resultMsg = "Failure";
        this.errorData = errorData;
    }
}

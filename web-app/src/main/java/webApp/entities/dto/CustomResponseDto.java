package webApp.entities.dto;

import java.io.Serializable;

/**
 * Created by Sarim on 5/5/2020.
 */
public class CustomResponseDto implements Serializable {

    private String status;
    private String message;
    private String responseCode;
    private String outhToken;
    private Object entityClass;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Object entityClass) {
        this.entityClass = entityClass;
    }

    public String getOuthToken() {
        return outhToken;
    }

    public void setOuthToken(String outhToken) {
        this.outhToken = outhToken;
    }
}

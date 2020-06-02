package webApp.entities.dto;

/**
 * Created by Sarim on 5/31/2020.
 */
public class DocumentDto {

    private String userName;
    private String userEmail;
    private Object entity;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}

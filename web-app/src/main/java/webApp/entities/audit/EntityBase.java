package webApp.entities.audit;

/**
 * Created by Sarim on 5/3/2020.
 */
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase<T> {
    @CreatedBy
    @Column(name="CREATED_BY")
    protected T createdBy;
    @LastModifiedBy
    @Column(name="MODIFIED_BY")
    protected T modifiedBy;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_DATE")
    protected Date createdDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFIED_DATE")
    protected Date modifiedDate;
    public T getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }
    public T getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(T modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

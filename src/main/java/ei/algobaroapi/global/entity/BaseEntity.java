package ei.algobaroapi.global.entity;

import ei.algobaroapi.global.exception.GlobalEntityException;
import ei.algobaroapi.global.exception.common.GlobalErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", columnDefinition = "datetime", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    protected BaseEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    protected void update() {
        this.updatedAt = LocalDateTime.now();
    }

    protected void delete() {
        if (isDeleted()) {
            throw GlobalEntityException.of(GlobalErrorCode.ALREADY_DELETED_ENTITY);
        }
        this.deletedAt = LocalDateTime.now();
    }
}

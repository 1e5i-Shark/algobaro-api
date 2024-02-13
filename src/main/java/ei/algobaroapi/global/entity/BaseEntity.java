package ei.algobaroapi.global.entity;

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
            throw new IllegalArgumentException("이미 삭제된 엔티티 입니다.");
        }
        this.deletedAt = LocalDateTime.now();
    }
}

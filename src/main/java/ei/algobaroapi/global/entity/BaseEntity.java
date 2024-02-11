package ei.algobaroapi.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "datetime", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    protected void delete() {
        if (isDeleted()) throw new IllegalArgumentException("이미 삭제된 엔티티 입니다.");
        this.deletedAt = LocalDateTime.now();
    }
}

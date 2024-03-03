package ei.algobaroapi.domain.room_member.domain;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "room_members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomMember extends BaseEntity {

    @Id
    @Column(name = "room_members_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_members_role", nullable = false)
    private RoomMemberRole roomMemberRole;

    @Column(name = "is_ready")
    private boolean isReady;

    @Column(name = "submit_code")
    private String submitCode;

    @Builder
    public RoomMember(Room room, Member member, RoomMemberRole roomMemberRole, boolean isReady) {
        this.room = room;
        this.member = member;
        this.roomMemberRole = roomMemberRole;
        this.isReady = isReady;
        this.submitCode = null;
    }
}

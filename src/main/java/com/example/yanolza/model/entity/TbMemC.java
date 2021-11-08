package com.example.yanolza.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="member_seq_c",
        sequenceName = "member_seq_c",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_mem_c")
public class TbMemC {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_c")
    private Integer id;
    private String memHp;
    private String memEmail;
    private String memNkname;
    private String reason;
    @CreatedDate
    private LocalDateTime  cRegdate;

}

package com.example.yanolza.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name = "review_seq",
        sequenceName = "review_seq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TbReview {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    private Integer id;
    private String tbHostname;
    private String reNkname;
    private Integer reGrade;
    private String reTitle;
    private String reContent;
    private String reReply;
    private String ische;
    private Integer tbHostId;

    @CreatedDate
    private LocalDateTime reRpdate;
    @CreatedDate
    private LocalDateTime reRegdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tbReview",cascade = CascadeType.REMOVE  )
    private List<TbReviewImg> tbReviewImgList;

    @ManyToOne
    private TbRoom tbRoom;

    @ManyToOne
    private TbMem tbMem;
}

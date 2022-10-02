package kr.ac.sku.intothevegan.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Table(name = "likes")
@IdClass(LikeId.class)
public class Likes {
    @Id
    @Column(name = "productId")
    private Integer productId;

    @Id
    @Column(name = "memberId")
    private String memberId;

    public Likes(String memberId, Integer productId) {
        this.memberId = memberId;
        this.productId = productId;
    }

    public Likes() {

    }

}

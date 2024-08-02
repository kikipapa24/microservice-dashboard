package com.lcwd.rating.entites;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating ;
    private String feedback;


}

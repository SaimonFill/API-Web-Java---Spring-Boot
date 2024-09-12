package io.github.api.booking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnnouncementType announcementType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "realty_id")
    private Realty realty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advertiser_id")
    private User advertiser;

    private BigDecimal dayValue;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<PaymentTypes> paymentTypes;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isActive = false;

    private String description;

}

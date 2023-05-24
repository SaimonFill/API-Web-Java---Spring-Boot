package io.github.api.booking.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequesterInformationResponse {

    private Long id;
    private String name;
}

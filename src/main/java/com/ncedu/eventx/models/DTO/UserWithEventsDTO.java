package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithEventsDTO {

    private UserDTO user;
    private Set<EventDTO> events;
    private int showOrder;

}

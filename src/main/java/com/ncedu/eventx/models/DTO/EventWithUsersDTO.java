package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventWithUsersDTO {

    private EventDTO event;
    private UserDTO creator;
    private List<UserDTO> visitors;

}
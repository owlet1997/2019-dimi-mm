package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeWithEventsDTO {
    private int id;
    private String type;
    private Set<EventDTO> eventDTOSet;
}

package com.ncedu.eventx.models.DTO;

import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityWithEventsDTO {
    private int id;
    private String abbrev;
    private String name;
    private List<EventDTO> events;

}


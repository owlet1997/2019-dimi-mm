package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventWithItemsDTO {
    private int id;
    private CityDTO city;
    private String name;
    private EventTypeDTO eventType;
    private CoordinatesDTO coord;
    private Date timeStart;
    private Date timeEnd;
    private String description;
    private String address;
    private Set<EventItemDTO> items;
}

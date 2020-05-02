package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventForListDTO {
    private int id;
    private CityDTO city;
    private String name;
    private EventTypeDTO type;
    private CoordinatesDTO coord;
    private Date timeStart;
    private Date timeEnd;
    private String description;
    private String address;
    private int visitors;
    private List<EventItemWithoutParentDTO> itemsList;
}

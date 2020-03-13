package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private int id;
    private CityDTO city;
    private String name;
    private EventTypeDTO eventType;
    private CoordinatesDTO coord;
    private Date timeStart;
    private Date timeEnd;
    private String description;
    private String address;


}

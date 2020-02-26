package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.time.LocalDate;

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
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
    private float latitude;
    private float longtitude;

}

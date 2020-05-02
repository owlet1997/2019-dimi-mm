package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventWithUsersDTO {

    private int id;
    private CityDTO city;
    private String name;
    private EventTypeDTO type;
    private CoordinatesDTO coord;
    private Date timeStart;
    private Date timeEnd;
    private String description;
    private String address;
    private UserDTO creator;

}
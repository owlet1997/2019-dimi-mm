package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventItemDTO {

    private int id;
    private EventDTO parent;
    private String name;
    private String auditory;
    private Date timeStart;
    private String description;

}

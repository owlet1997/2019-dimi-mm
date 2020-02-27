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
    private EventDTO parentId;
    private String name;
    private Date time;
    private float latitude;
    private float longtitude;

}

package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventItemWithoutParentDTO {

    private int id;
    private String name;
    private String auditory;
    private Date timeStart;
    private String description;

}

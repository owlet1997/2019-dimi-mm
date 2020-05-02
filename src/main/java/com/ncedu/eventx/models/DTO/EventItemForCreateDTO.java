package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventItemForCreateDTO {

    private int parent;
    private int speaker;
    private String name;
    private String auditory;
    private String description;
    private String dateStart;
    private String timeStart;
}

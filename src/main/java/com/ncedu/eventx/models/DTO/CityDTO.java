package com.ncedu.eventx.models.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private int id;
    private String abbrev;
    private String name;

}

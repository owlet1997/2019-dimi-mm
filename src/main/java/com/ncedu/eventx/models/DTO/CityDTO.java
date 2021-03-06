package com.ncedu.eventx.models.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ncedu.eventx.serializers.JsonToPointDeserializer;
import com.ncedu.eventx.serializers.PointToJsonSerializer;
import lombok.*;
import org.locationtech.jts.geom.Point;

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

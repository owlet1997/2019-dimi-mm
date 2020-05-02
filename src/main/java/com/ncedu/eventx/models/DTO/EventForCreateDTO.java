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
public class EventForCreateDTO {

    private String city;
    private String name;
    private int type;

    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    private Point coord;

    private String dateStart;
    private String timeStart;
    private String dateEnd;
    private String timeEnd;
    private String description;
    private String address;
}

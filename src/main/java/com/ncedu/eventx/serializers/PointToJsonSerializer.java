package com.ncedu.eventx.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class PointToJsonSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String jsonValue = "null";
        try
        {
            if(point != null) {
                double lat = point.getY();
                double lon = point.getX();
                jsonValue = String.format("[%s,%s]", lat, lon);
            }
        }
        catch(Exception e) {}

        jsonGenerator.writeString(jsonValue);
    }
}

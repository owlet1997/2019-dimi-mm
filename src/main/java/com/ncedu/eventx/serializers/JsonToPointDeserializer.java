package com.ncedu.eventx.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.IOException;

public class JsonToPointDeserializer extends JsonDeserializer<Point> {

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Override
    public Point deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            String text = p.getText();
            if(text == null || text.length() <= 0)
                return null;

            String[] coordinates = text.replaceFirst("POINT ?\\(", "").replaceFirst("\\)", "").split(" ");
            double lat = Double.parseDouble(coordinates[0]);
            double lon = Double.parseDouble(coordinates[1]);

            Point point = geometryFactory.createPoint(new Coordinate(lat, lon));
            return point;
        }
        catch(Exception e){
            return null;
        }
    }

}

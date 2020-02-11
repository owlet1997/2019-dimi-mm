package com.ncedu.eventx.model.DTO;

public class CityDTO {

    private String cityId;
    private String name;

    public CityDTO() {
    }

    public CityDTO(String cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

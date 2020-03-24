package com.ncedu.eventx.controllers;

import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.*;
import com.ncedu.eventx.services.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainRestController {

    final UsersService usersService;
    final CoordinatesService coordinatesService;
    final EventsService eventsService;
    final EventItemService eventItemService;
    final CitiesService citiesService;
    final EventTypeService eventTypeService;
    final UserEventService userEventService;

    public MainRestController(UsersService usersService,
                              CoordinatesService coordinatesService,
                              EventsService eventsService,
                              EventItemService eventItemService, CitiesService citiesService, EventTypeService eventTypeService, UserEventService userEventService) {
        this.usersService = usersService;
        this.coordinatesService = coordinatesService;
        this.eventsService = eventsService;
        this.eventItemService = eventItemService;
        this.citiesService = citiesService;
        this.eventTypeService = eventTypeService;
        this.userEventService = userEventService;
    }
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @RequestMapping("/api")
    @ResponseBody
    public String welcome() {
        return "Welcome to EventX API!";
    }

    @GetMapping(value = "/api/users", //
            produces = { MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public List<UserDTO> getUsers() {
        return usersService.getAllUsers();
    }


    @GetMapping(value = "/api/users/{userNo}",
            produces = { MediaType.APPLICATION_JSON_VALUE
                    })
    @ResponseBody
    public UserForUpdateDTO getUser(@PathVariable("userNo") int userNo) {
        return usersMapper.toUserForCreateDTO(usersService.getUserById(userNo));
    }

    /////////////////////////////////////////////////

    @GetMapping(value = "/api/places", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public List<CoordinatesDTO> getPlaces() {
        return coordinatesService.getAllPlaces();
    }

    @GetMapping(value = "/api/cities", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public List<CityDTO> getCities() {
        return citiesService.getCitiesList();
    }

    /////////////////////////////////////////////////

    @GetMapping(value = "/api/events/{id}", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public EventWithItemsDTO getEventById(@PathVariable("id") int eventId) {
        return eventsService.getEventWithItemsById(eventId);
    }

    @GetMapping(value = "/api/events/{id}/items", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public List<EventItemWithUsersDTO> getEventItemsByParent(@PathVariable("id") int eventId) {
        return eventItemService.getEventItemsListByParent(eventId);
    }

    @GetMapping(value = "/api/event-types", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public List<EventTypeDTO> getEventTypes() {
        return eventTypeService.getAllEventTypes();
    }

    @GetMapping(value = "/api/events", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public List<EventWithItemsDTO> getEventBySearchParam(@RequestParam(name = "city") Optional<String> city,
                                                @RequestParam(name = "type") Optional<String> type,
                                                @RequestParam(name = "dateStart") Optional<String> dateStart) {

    return eventsService.getEventsBySearchParams(city.orElseGet(()->""),
                                                type.orElseGet(()->""),
                                                dateStart.orElseGet(()->""));
    }

    @GetMapping(value = "/api/events/{id}/creator", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public List<EventDTO> getEventsByCreator(@PathVariable("id") int id) {

        return eventsService.getLastEventsByCreator(id);
    }

    @PostMapping(value = "/api/event-visit", //
            produces = { MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public boolean visitEvent(@RequestParam(name = "eventId") int eventId,
                              @RequestParam(name = "userId") int userId) {

        return userEventService.visitEvent(userId,eventId);
    }






}

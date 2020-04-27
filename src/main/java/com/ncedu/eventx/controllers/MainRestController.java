package com.ncedu.eventx.controllers;

import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.enums.UserRoleItems;
import com.ncedu.eventx.models.DTO.*;
import com.ncedu.eventx.services.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.sql.Blob;
import java.util.Arrays;
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
    final UserEventItemService userEventItemService;

    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    public MainRestController(UsersService usersService,
                              CoordinatesService coordinatesService,
                              EventsService eventsService,
                              EventItemService eventItemService,
                              CitiesService citiesService,
                              EventTypeService eventTypeService,
                              UserEventService userEventService,
                              UserEventItemService userEventItemService) {
        this.usersService = usersService;
        this.coordinatesService = coordinatesService;
        this.eventsService = eventsService;
        this.eventItemService = eventItemService;
        this.citiesService = citiesService;
        this.eventTypeService = eventTypeService;
        this.userEventService = userEventService;
        this.userEventItemService = userEventItemService;
    }

        @RequestMapping("/api")
        @ResponseBody
        public String welcome() {
            return "Welcome to EventX API!";
        }

        @GetMapping(value = "/api/users", //
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<UserDTO> getUsers() {
            return usersService.getAllUsers();
        }


        @GetMapping(value = "/api/users/{userNo}",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public UserForUpdateDTO getUser(@PathVariable("userNo") int userNo) {
            return usersMapper.toUserForUpdateDTO(usersService.getUserById(userNo));
        }


        /////////////////////////////////////////////////

        @GetMapping(value = "/api/places", //
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<CoordinatesDTO> getPlaces () {
            return coordinatesService.getAllPlaces();
        }

        @GetMapping(value = "/api/cities", //
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<CityDTO> getCities () {
            return citiesService.getCitiesList();
        }

        @GetMapping(value = "/api/cities/{abbrev}", //
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<CityDTO> getCities(@PathVariable("abbrev") String abbrev) {
            return citiesService.getCitiesList(abbrev);
        }

        /////////////////////////////////////////////////

        @GetMapping(value = "/api/events/{eventId}",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public EventWithItemsDTO getEventById ( @PathVariable("eventId") int eventId){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return eventsService.getEventWithItemsById(eventId, username);
        }

        @DeleteMapping(value = "/api/events/{eventId}",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public boolean deleteEventById ( @PathVariable("eventId") int eventId){
            return eventsService.deleteEventById(eventId);
        }

        @DeleteMapping(value = "/api/items/{itemId}",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public boolean deleteItemById ( @PathVariable("itemId") int itemId){
            return eventItemService.deleteItemById(itemId);
        }

        @PutMapping(value = "/api/events/{eventId}/cancel",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public boolean cancelEventById ( @PathVariable("eventId") int eventId){
            return eventsService.cancelEventById(eventId);
        }

        @PutMapping(value = "/api/events")
        @ResponseBody
        public EventForUpdateDTO updateEventById (@RequestBody EventForUpdateDTO event){
            return eventsService.updateEventById(event);
        }

        @GetMapping(value = "/api/user/{userId}/events/guest",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventDTO> getGuestEventsByUserId(@PathVariable("userId") int userId){

            return eventsService.getEventsByUserId(userId, UserRoleItems.VISITOR.getDescription());
        }

        @GetMapping(value = "/api/user/{userId}/events/creator",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventDTO> getCreatorEventsByUserId(@PathVariable("userId") int userId){
            return eventsService.getEventsByUserId(userId, UserRoleItems.CREATOR.getDescription());
        }

        @GetMapping(value = "/api/user/{userId}/items",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventItemDTO> getItemsByUserId(@PathVariable("userId") int userId){
            return eventItemService.getItemsByUser(userId);
        }



        @GetMapping(value = "/api/event-types",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventTypeDTO> getEventTypes () {
            return eventTypeService.getAllEventTypes();
        }

        @GetMapping(value = "/api/event-types/{id}",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventTypeDTO> getEventTypesWithoutOne (@PathVariable("id") int id) {
            return eventTypeService.getAllEventTypes(id);
        }

        @GetMapping(value = "/api/events",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventWithItemsDTO> getEventBySearchParam(@RequestParam(name = "city") Optional <String> city,
                @RequestParam(name = "type") Optional <String> type,
                @RequestParam(name = "dateStart") Optional <String> dateStart){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println(username);
            return eventsService.getEventsBySearchParams(city.orElseGet(() -> ""),
                    type.orElseGet(() -> ""),
                    dateStart.orElseGet(() -> ""));
        }

        @GetMapping(value = "/api/events/{id}/creator",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public List<EventDTO> getEventsByCreator ( @PathVariable("id") int id){
            return eventsService.getLastEventsByCreator(id);
        }

        @PostMapping(value = "/api/event-visit",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public boolean visitEvent ( @RequestParam(name = "eventId") int eventId) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userEventService.visitEvent(username, eventId);
        }

        @PostMapping(value = "/api/item-visit",
                produces = {MediaType.APPLICATION_JSON_VALUE
                })
        @ResponseBody
        public boolean checkFeaturedEvent(@RequestParam(name = "itemId") int itemId){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userEventItemService.addToFeatured(itemId, username);
        }



}

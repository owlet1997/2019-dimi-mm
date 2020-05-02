package com.ncedu.eventx;

import com.ncedu.eventx.repositories.RolesRepository;
import com.ncedu.eventx.services.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventxApplicationTests {

    @Autowired
    CoordinatesService coordinatesService;

    @Autowired
    CitiesService citiesService;

    @Autowired
    EventTypeService eventTypeService;

    @Autowired
    EventsService eventsService;

    @Autowired
    EventItemService eventItemService;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserEventService userEventService;

    @Autowired
    UsersService usersService;


//    @Test
//    void addEvent() {
//        EventTypeDTO eventTypeDTO = eventTypeService.getEventTypeByName("Вебинар");
//        CityDTO cityDTO = citiesService.getCityByName("Тольятти");
//        UserDTO userDTO = usersService.getUserById();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_WEEK,3);
//        Date date = calendar.getTime();
//        CoordinatesDTO coordinatesDTO = coordinatesService.getPlaceByName("Русь");
//
//        EventDTO eventDTO = new EventDTO(1, cityDTO, "вебинар снова какой-то", eventTypeDTO,
//                coordinatesDTO, new Date(), date, "Там точно будет что-то происходить, неизвестно, интересно ли",
//                "Революционная, 56");
//
//
//        userEventService.createEvent(, );
//        System.out.println("true");
//    }


//    @Test
//    void addUser(){
//        UserForUpdateDTO userDTO = new UserForUpdateDTO(1,"owlet","alonewithl@yandex.ru",
//                "qwerty","Шаталова Анна", "ТГУ", "Студент");
//        usersService.createRegisteredUser(userDTO);
//    }


//    @Test
//    void findEvent(){
//        System.out.println(eventsService.getEventById(10));
//    }

//    @Test
//    void visitEvent(){
//        EventDTO eventDTO = eventsService.getEventById(10);
//        UserDTO userDTO = usersService.getUserById(21);
//
//        userEventService.visitEvent(, userDTO, , eventDTO);
//    }

//    @Test
//    @Transactional
//    void getEvent(){
//        System.out.println(eventsService.getEventWithUsers(10));
//
//    }





}

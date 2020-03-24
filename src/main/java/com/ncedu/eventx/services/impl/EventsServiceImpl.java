package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.*;
import com.ncedu.eventx.models.DTO.*;
import com.ncedu.eventx.models.entities.*;
import com.ncedu.eventx.repositories.*;
import com.ncedu.eventx.services.CoordinatesService;
import com.ncedu.eventx.services.EventItemService;
import com.ncedu.eventx.services.EventsService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.ncedu.eventx.enums.UserRoleItems.CREATOR;
import static com.ncedu.eventx.enums.UserRoleItems.VISITOR;

@Service
public class EventsServiceImpl implements EventsService {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat formatCreate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

    final EventRepository eventRepository;
    final CitiesRepository citiesRepository;
    final EventTypeRepository eventTypeRepository;
    final RolesRepository rolesRepository;
    final UserEventRepository userEventRepository;
    final CoordinatesService coordinatesService;
    final UserRepository userRepository;
    final EventItemService eventItemService;

    EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);


    public EventsServiceImpl(EventRepository eventRepository,
                             CitiesRepository citiesRepository,
                             EventTypeRepository eventTypeRepository,
                             RolesRepository rolesRepository,
                             UserEventRepository userEventRepository,
                             CoordinatesService coordinatesService,
                             UserRepository userRepository, EventItemService eventItemService) {
        this.eventRepository = eventRepository;
        this.citiesRepository = citiesRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.rolesRepository = rolesRepository;
        this.userEventRepository = userEventRepository;
        this.coordinatesService = coordinatesService;
        this.userRepository = userRepository;
        this.eventItemService = eventItemService;
    }

    @Override
    public EventEntity createEvent(EventForCreateDTO event) {
        EventEntity eventEntity = new EventEntity();
        CoordinatesEntity coordinatesEntity;
        CityEntity cityEntity = citiesRepository.findByAbbrev(event.getCity());
        if (coordinatesService.getPlaceByName(event.getAddress())==null){
            coordinatesEntity = coordinatesService.createPlace(event.getAddress(), event.getCoord());
        }
        else coordinatesEntity = coordinatesService.getPlaceByName(event.getAddress());
        EventTypeEntity eventTypeEntity = eventTypeRepository.findById(event.getType());

        eventEntity.setAddress(event.getAddress());
        eventEntity.setName(event.getName());
        eventEntity.setDescription(event.getDescription());

        String dateStart = event.getDateStart() + " " + event.getTimeStart();
        String dateEnd = event.getDateEnd() + " " + event.getTimeEnd();
        try {
            eventEntity.setTimeStart(formatCreate.parse(dateStart));
            eventEntity.setTimeEnd(formatCreate.parse(dateEnd));
        }catch (Exception e){
            e.printStackTrace();
        }

        eventEntity.setCoord(coordinatesEntity);
        eventEntity.setCity(cityEntity);
        eventEntity.setType(eventTypeEntity);

        eventRepository.save(eventEntity);

        return eventEntity;

    }

    @Override
    public List<EventDTO> getEventsList() {
        List<EventEntity> list = eventRepository.findAll();
        return eventMapper.toListDTO(list);
    }

    @Override
    public EventWithItemsDTO getEventWithItemsById(int id) {
        EventEntity eventEntity = eventRepository.findById(id);
        RoleEntity userRoleCreator = rolesRepository.findByName(CREATOR.getDescription());
        RoleEntity userRoleVisitor = rolesRepository.findByName(VISITOR.getDescription());
        List<UserEventEntity> userEventEntityList = userEventRepository.findAllByEvent(eventEntity);
        UserDTO creator = usersMapper.toDTO(userEventEntityList.stream()
                .filter(role -> role.getRole().equals(userRoleCreator))
                .map(UserEventEntity::getUser).findFirst().get());
        List<UserEntity> userEntityList = userEventEntityList.stream()
                .filter(role -> role.getRole().equals(userRoleVisitor))
                .map(UserEventEntity::getUser).collect(Collectors.toList());
        EventWithItemsDTO event = eventMapper.toEventWithItemsDTO(eventEntity);
        event.setCreator(creator);
        event.setItemsList(eventItemService.getEventItemsListByParent(eventEntity.getId()));
        event.setVisitors(usersMapper.toUserDTOList(userEntityList));
        return event;
    }

    @Override
    public EventDTO getEventById(int id) {
        EventEntity eventEntity = eventRepository.findById(id);
        return eventMapper.toDTO(eventEntity);
    }


    @Override
    public List<EventWithItemsDTO> getEventsBySearchParams(String city, String type, String dateStart) {

        List<EventEntity> eventEntityList = new ArrayList<>();

        boolean isCityEmpty = city.isEmpty();
        boolean isTypeEmpty = type.isEmpty();
        boolean isDateStartEmpty = dateStart.isEmpty();

        Date date = null;
        if (!isDateStartEmpty) {
            try {
                date = formatter.parse(dateStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int typeId = 0;
        if (!isTypeEmpty) typeId = Integer.parseInt(type);

        EventTypeEntity typeEntity;
        CityEntity cityEntity;

        StringBuilder builder = new StringBuilder("");
        builder.append(isCityEmpty ? "1":"0");
        builder.append(isTypeEmpty ? "1":"0");
        builder.append(isDateStartEmpty ? "1":"0");

        switch (builder.toString()){
            // все 3 параметра не пустые
            case "000": typeEntity = eventTypeRepository.findById(typeId);
                        cityEntity = citiesRepository.findByAbbrev(city);
                        eventEntityList = eventRepository.findAllByTypeAndCityAndTimeStart(typeEntity,cityEntity,date);
                        break;

                // пустая дата
            case "001": typeEntity = eventTypeRepository.findById(typeId);
                        cityEntity = citiesRepository.findByAbbrev(city);
                        eventEntityList = eventRepository.findAllByTypeAndCity(typeEntity,cityEntity);
                        break;

            // пустой тип
            case "010": cityEntity = citiesRepository.findByAbbrev(city);
                        eventEntityList = eventRepository.findAllByCityAndTimeStart(cityEntity, date);
                break;

            // пустой город
            case "100": typeEntity = eventTypeRepository.findById(typeId);
                        eventEntityList = eventRepository.findAllByTypeAndTimeStart(typeEntity,date);
                break;

            // пустой тип и пустая дата
            case "011": cityEntity = citiesRepository.findByAbbrev(city);
                        eventEntityList = eventRepository.findAllByCity(cityEntity);
                break;

            // пустой город и дата
            case "101": typeEntity = eventTypeRepository.findById(typeId);
                        eventEntityList = eventRepository.findAllByType(typeEntity);
                break;

            // пустой город и пустой тип
            case "110": eventEntityList = eventRepository.findAllByTimeStart(date);
                break;

            // все пустые
            case "111": eventEntityList = eventRepository.findAll();
                break;
        }

    return getEventsWithItemsList(eventEntityList);
    }

    @Override
    public List<EventWithItemsDTO> getEventsWithItemsList(List<EventEntity> eventEntityList) {

        RoleEntity userRoleVisit = rolesRepository.findByName(VISITOR.getDescription());

        List<UserEventEntity> userEventEntityList = userEventRepository.findAll();

        List<EventWithItemsDTO> withItemsDTOList = new ArrayList<>();
        for (EventEntity e: eventEntityList) {
            List<UserEntity> usersList = userEventEntityList.stream()
                    .filter(event -> event.getEvent().equals(e))
                    .filter(role -> role.getRole().equals(userRoleVisit))
                    .map(UserEventEntity::getUser).collect(Collectors.toList());
            List<UserDTO> userDTOList = usersMapper.toUserDTOList(usersList);
            EventWithItemsDTO event = eventMapper.toEventWithItemsDTO(e);
            event.setVisitors(userDTOList);
            event.setItemsList(eventItemService.getEventItemsListByParent(e.getId()));
            withItemsDTOList.add(event);
        }
        return withItemsDTOList;
    }

    @Override
    public EventWithUsersDTO getEventWithUsers(int id) {
        EventEntity eventEntity = eventRepository.findById(id);

        RoleEntity userRoleVisit = rolesRepository.findByName(VISITOR.getDescription());
        RoleEntity userRoleCreator = rolesRepository.findByName(CREATOR.getDescription());


        List<UserEventEntity> list = userEventRepository.findAllByEvent(eventEntity);

        List<UserEntity> usersList = list.stream()
                .filter(e -> e.getRole().equals(userRoleVisit))
                .map(UserEventEntity::getUser).collect(Collectors.toList());

        UserEntity creator = list.stream()
                .filter(e -> e.getRole().equals(userRoleCreator))
                .map(UserEventEntity::getUser).findFirst().get();

        return new EventWithUsersDTO(eventMapper.toDTO(eventEntity),
                usersMapper.toDTO(creator),usersMapper.toUserDTOList(usersList));
    }

    @Override
    public List<EventDTO> getLastEventsByCreator(int userId) {
        UserEntity userEntity = userRepository.findById(userId);

        RoleEntity userRoleCreator = rolesRepository.findByName(CREATOR.getDescription());

        List<UserEventEntity> list = userEventRepository.findAllByUser(userEntity);

        List<EventEntity> eventEntityList = list.stream().filter(role -> role.getRole().equals(userRoleCreator))
                                                         .map(UserEventEntity::getEvent).collect(Collectors.toList());

        Collections.reverse(eventEntityList);
        List<EventEntity> result = eventEntityList.stream().limit(3).collect(Collectors.toList());

        return eventMapper.toListDTO(result);
    }
}

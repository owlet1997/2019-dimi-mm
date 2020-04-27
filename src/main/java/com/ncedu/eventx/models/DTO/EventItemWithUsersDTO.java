package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventItemWithUsersDTO {

    EventItemDTO eventItem;
    UserDTO speaker;
    boolean isFeatured;
    List<UserDTO> users;
}

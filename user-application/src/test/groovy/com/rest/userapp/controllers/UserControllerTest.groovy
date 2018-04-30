package com.rest.userapp.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.rest.userapp.dto.UserDto
import com.rest.userapp.enums.UserResponseStatus
import com.rest.userapp.service.UserService
import com.rest.userapp.wrapper.UserResponseWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest(UserController.class)
class UserControllerTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    UserService userService

    def setup() {
    }

    def "save user"() {
        given:"mock userDto"
        UserDto userDto = populateUserDtoDetails()

        and:"mocked userService to return userDto"
        userService.save(_ as UserDto) >> userDto

        when:"calling rest api to save user details"
        def response = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                        .andReturn()

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})

        then:
        userResponseWrapper != null
        userResponseWrapper.status == UserResponseStatus.SUCCESS
        userResponseWrapper.data.age == 34
    }

    /*def "update user"() {
        given:"mock userDto"
        UserDto userDto = populateUserDtoDetails()

        and:"mocked userService to return userDto"
        userService.save(_ as UserDto) >> userDto

        when:"calling rest api to save user details"
        *//*def response = mockMvc.perform(put("/users/{id}", userDto.userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto))
                        .andReturn()*//*

        *//*def response = mockMvc.perform(put("/users/{id}", userDto.getUserId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto))
                        .andReturn()*//*

        def response = mockMvc.perform(
                putAt("/users/{id}", "1L")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})


        then:
        userResponseWrapper != null
        userResponseWrapper.status == UserResponseStatus.SUCCESS
        userResponseWrapper.data.age == 34
    }*/

    def "get By Id"() {
        given:"mock userDto"
        UserDto userDto = populateUserDtoDetails()

        and:"mocked userService to return userDto"
        userService.findById(_ as Long) >> userDto

        when:"calling rest api to get user details"
        def response = mockMvc.perform(get("/users/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})

        then:"getting success response"
        userResponseWrapper != null
        userResponseWrapper.status == UserResponseStatus.SUCCESS
        userResponseWrapper.data.age == 34
    }

    def "get By Id | failure"() {
        given:"mock userDto"
        UserDto userDto = populateUserDtoDetails()

        and:"mocked userService to return null"
        userService.findById(_ as Long) >> null

        when:"calling rest api to get user details"
        def response = mockMvc.perform(get("/users/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})


        then:"getting null data as the user is not present"
        userResponseWrapper != null
        userResponseWrapper.data == null
    }

    def "get All"() {
        given:"mock userDtoList"
        List<UserDto> userDtoList = populateUserDetailsDtoList()

        and:"mocked userService to return userDtoList"
        userService.findAll() >> userDtoList

        when:"calling rest api to get user details"
        def response = mockMvc.perform(get("/users/")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})

        then:"getting success response"
        userResponseWrapper != null
        userResponseWrapper.status == UserResponseStatus.SUCCESS
        userResponseWrapper.data.size == 3
    }

    def "get All | failure"() {
        given:"mock userDto"

        and:"mocked userService to return null"
        userService.findAll() >> null

        when:"calling rest api to get user details"
        def response = mockMvc.perform(get("/users/")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})

        then:"getting success response"
        userResponseWrapper != null
        userResponseWrapper.status == UserResponseStatus.SUCCESS
        userResponseWrapper.data == null
    }

    /*def "delete user"() {
        given:"mock userDto"
        UserDto userDto = populateUserDtoDetails()

        and:"mocked userService to return userDto"
        userService.findById(_ as Long) >> userDto
        userService.delete(_ as Long) >> null

        when:"calling rest api to get user details"
        def response = mockMvc.perform(delete("/users/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        UserResponseWrapper userResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<UserResponseWrapper>(){})

        then:"getting success response"
        userResponseWrapper != null
        userResponseWrapper.status == UserResponseStatus.SUCCESS
        userResponseWrapper.data == null
    }*/

    private static UserDto populateUserDtoDetails( ){
        return new UserDto(1L,"userName", "firstName", "lastName", 34)
    }

    private static List<UserDto> populateUserDetailsDtoList( ){
        return Arrays.asList(
                new UserDto(1L,"userName 1", "firstName1", "lastName1", 31),
                new UserDto(2L,"userName 2", "firstName2", "lastName2", 32),
                new UserDto(3L,"userName 3", "firstName3", "lastName3", 33),
        )
    }

    private static String asJsonString(final Object obj) {
        try {
            String str = new ObjectMapper().writeValueAsString(obj)
            return str
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    static class IntegrationTestMockingConfig {
        private DetachedMockFactory factory = new DetachedMockFactory()

        @Bean
        UserService userService() {
            factory.Mock(UserService.class,name:"userService")
        }
    }
}



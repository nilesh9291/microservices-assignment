package com.rest.userapp.service.impl

import com.rest.userapp.dto.UserDto
import com.rest.userapp.entity.User
import com.rest.userapp.exception.UserAlreadyExistsException
import com.rest.userapp.exception.UserNotFoundException
import com.rest.userapp.mapper.UserMapper
import com.rest.userapp.repository.UserRepository
import spock.lang.Specification

class UserServiceImplTest extends Specification {
    //class to be tested
    private UserServiceImpl serviceImpl

    void setup() {
        serviceImpl = new UserServiceImpl(userRepository: Mock(UserRepository), userMapper: Spy(UserMapper))
    }

    def "Save user - Success scenario"() {
        given:"The userDto is passed as an argument"
        UserDto inputUserDto = populateUserDtoDetails()
        User user = populateUserDetails()

        and:"The userName is not already present in the database so the findByUserName api is returning null"
        serviceImpl.userRepository.findByUserName(_ as String) >> null
        serviceImpl.userRepository.save(_ as User) >> user

        when:"The userDto is passed as an argument to the save api of serviceImpl"
        UserDto expectedUserDto = serviceImpl.save(inputUserDto)

        then:"The userDto is not null and is returned back to the controller from serviceImpl"
        expectedUserDto != null
        expectedUserDto.firstName == inputUserDto.firstName
    }

    def "save user - User Already Present Exception scenario"() {
        given:"The userDto is passed as an argument"
        UserDto inputUserDto = populateUserDtoDetails()
        User user = populateUserDetails()

        and:"The userName is already present in the database so findByUserName api is returning user"
        serviceImpl.userRepository.findByUserName(_ as String) >> user

        when:"The userDto is passed as an argument to the save api of serviceImpl"
        serviceImpl.save(inputUserDto)

        then:"The userName already present so UserAlreadyExistsException is thrown"
        def e = thrown(UserAlreadyExistsException)
        e != null
    }

    def "Update user - Success scenario"() {
        given:"The userDto is passed as an argument"
        UserDto inputUserDto = populateUserDtoDetails()
        User user = populateUserDetails()

        and:
        serviceImpl.userRepository.save(_ as User) >> user

        when:"The userDto is passed as an argument"
        UserDto expectedUserDto = serviceImpl.save(inputUserDto)

        then:"The userDto is not null and is returned back to the controller from serviceImpl"
        expectedUserDto != null
        expectedUserDto.firstName == inputUserDto.firstName
    }

    def "Find user By Id - Success scenario"() {
        given:"The userId is passed as an argument"
        User user = populateUserDetails()
        Long userId = 1L

        and:"The user is present in the database so returning user"
        serviceImpl.userRepository.findOne(_ as Long) >> user

        when:"The userId is passed as an argument"
        UserDto expectedUserDto = serviceImpl.findById(userId)

        then:"The userDto is not null and is returned back to the controller from serviceImpl"
        expectedUserDto != null
        expectedUserDto.userId == userId
    }

    def "Find user By Id - Not Found scenario"() {
        given:"The userId is passed as an argument"
        Long userId = 1L

        and:"The user is not present in the database so returning null"
        serviceImpl.userRepository.findOne(_ as Long) >> null

        when:"The userId is passed as an argument"
        UserDto expectedUserDto = serviceImpl.findById(userId)

        then:"The user is not present so UserNotFoundException is thrown"
        def e = thrown(UserNotFoundException)
        e != null
    }

    def "Find All Users - Success scenario"() {
        given:
        List<UserDto> userDtoList = populateUserDetailsDtoList()
        List<User> userList = populateUserDetailsList()

        and:"The users are present in the database so returning list of users"
        serviceImpl.userRepository.findAll(*_) >> userList

        when:
        List<UserDto> expectedUserDtoList = serviceImpl.findAll()

        then:"The userDto list is not null and is returned back to the controller from serviceImpl"
        expectedUserDtoList != null
        expectedUserDtoList.size() == userList.size()
    }

    def "Find All Users - Not Found scenario"() {
        given:"The users are not present in the database so returning null"
        serviceImpl.userRepository.findAll(*_) >> null

        when:
        List<UserDto> expectedUserDtoList = serviceImpl.findAll()

        then:"The users are not present so UserNotFoundException is thrown"
        def e = thrown(UserNotFoundException)
        e != null
    }

    def "Delete User - Success scenario"() {
        given:"The userId is passed as an argument"
        User user = populateUserDetails()
        Long userId = 1L

        and:"The user is present in the database so returning user"
        serviceImpl.userRepository.findOne(_ as Long) >> user
        serviceImpl.userRepository.delete(_ as Long) >> null

        when:"The userId is passed as an argument to delete"
        serviceImpl.delete(userId)

        then:"The user is present in the database so deleting the user"
        1 * serviceImpl.userRepository.delete(_)
    }

    def "Delete User - Not Found scenario"() {
        given:"The userId is passed as an argument"
        Long userId = 1L

        and:"The user is not present in the database so returning null"
        serviceImpl.userRepository.findOne(_ as Long) >> null
        serviceImpl.userRepository.delete(_ as Long) >> null

        when:"The userId is passed as an argument to delete"
        serviceImpl.delete(userId)

        then:"The user is not present so UserNotFoundException is thrown"
        def e = thrown(UserNotFoundException)
        e != null
    }

    def "Delete All Users - Success scenario"() {
        given:
        serviceImpl.userRepository.deleteAll() >> null

        when:
        serviceImpl.deleteAll()

        then:"The user is present in the database so deleting all the user"
        1 * serviceImpl.userRepository.deleteAll()
    }

    def "Find user by UserName - Success scenario"() {
        given:"The userName is passed as an argument"
        User user = populateUserDetails()
        String userName = "userName"

        and:"The user is present in the database so returning user"
        serviceImpl.userRepository.findByUserName(_ as String) >> user

        when:"The userName is passed as an argument"
        UserDto expectedUserDto = serviceImpl.findByUserName(userName)

        then:"The userDto is not null and is returned back to the controller from serviceImpl"
        expectedUserDto != null
        expectedUserDto.userName == userName
    }

    def "Find user by UserName - Not Found scenario"() {
        given:"The userName is passed as an argument"
        String userName = "userName"

        and:"The user is not present in the database so returning null"
        serviceImpl.userRepository.findByUserName(_ as String) >> null

        when:"The userName is passed as an argument"
        UserDto expectedUserDto = serviceImpl.findByUserName(userName)

        then:"The user is not present so UserNotFoundException is thrown"
        def e = thrown(UserNotFoundException)
        e != null
    }

    private static UserDto populateUserDtoDetails( ){
        return new UserDto(1L,"userName", "firstName", "lastName", 34)
    }

    private static User populateUserDetails( ){
        return new User(1L,"userName", "firstName", "lastName", 34)
    }

    private static List<User> populateUserDetailsList( ){
        return Arrays.asList(
                new User(1L,"userName 1", "firstName1", "lastName1", 31),
                new User(2L,"userName 2", "firstName2", "lastName2", 32),
                new User(3L,"userName 3", "firstName3", "lastName3", 33),
        )
    }

    private static List<UserDto> populateUserDetailsDtoList( ){
        return Arrays.asList(
                new UserDto(1L,"userName 1", "firstName1", "lastName1", 31),
                new UserDto(2L,"userName 2", "firstName2", "lastName2", 32),
                new UserDto(3L,"userName 3", "firstName3", "lastName3", 33),
        )
    }
}

package com.rest.accountapp.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.rest.accountapp.dto.AccountDto
import com.rest.accountapp.enums.AccountResponseStatus
import com.rest.accountapp.service.AccountService
import com.rest.accountapp.wrapper.AccountResponseWrapper
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

@WebMvcTest(AccountController.class)
class AccountControllerTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    AccountService accountService

    def setup() {
    }

    def "save account"() {
        given:"mock accountDto"
        AccountDto accountDto = populateAccountDtoDetails()

        and:"mocked accountService to return accountDto"
        accountService.save(_ as AccountDto) >> accountDto

        when:"calling rest api to save account details"
        def response = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountDto)))
                .andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})

        then:
        accountResponseWrapper != null
        accountResponseWrapper.status == AccountResponseStatus.SUCCESS
        accountResponseWrapper.data.type == "Current"
    }

    /*def "update account"() {
        given:"mock accountDto"
        AccountDto accountDto = populateAccountDtoDetails()

        and:"mocked accountService to return accountDto"
        accountService.save(_ as AccountDto) >> accountDto

        when:"calling rest api to update account details"
        def response = mockMvc.perform(put("/accounts/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})


        then:"getting success response"
        accountResponseWrapper != null
        accountResponseWrapper.status == AccountResponseStatus.SUCCESS
        accountResponseWrapper.data.age == 34
    }*/

    def "get By Id"() {
        given:"mock accountDto"
        AccountDto accountDto = populateAccountDtoDetails()

        and:"mocked accountService to return accountDto"
        accountService.findById(_ as Long) >> accountDto

        when:"calling rest api to get account details"
        def response = mockMvc.perform(get("/accounts/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})

        then:"getting success response"
        accountResponseWrapper != null
        accountResponseWrapper.status == AccountResponseStatus.SUCCESS
        accountResponseWrapper.data.type == "Current"
    }

    def "get By Id | failure"() {
        given:"mock accountDto"
        AccountDto accountDto = populateAccountDtoDetails()

        and:"mocked accountService to return null"
        accountService.findById(_ as Long) >> null

        when:"calling rest api to get account details"
        def response = mockMvc.perform(get("/accounts/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})


        then:"getting null data as the account is not present"
        accountResponseWrapper != null
        accountResponseWrapper.data == null
    }

    def "get All"() {
        given:"mock accountDtoList"
        List<AccountDto> accountDtoList = populateAccountDetailsDtoList()

        and:"mocked accountService to return accountDtoList"
        accountService.findAll() >> accountDtoList

        when:"calling rest api to get account details"
        def response = mockMvc.perform(get("/accounts/")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})

        then:"getting success response"
        accountResponseWrapper != null
        accountResponseWrapper.status == AccountResponseStatus.SUCCESS
        accountResponseWrapper.data.size == 3
    }

    def "get All | failure"() {
        given:"mock accountDto"

        and:"mocked accountService to return null"
        accountService.findAll() >> null

        when:"calling rest api to get account details"
        def response = mockMvc.perform(get("/accounts/")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})

        then:"getting success response"
        accountResponseWrapper != null
        accountResponseWrapper.status == AccountResponseStatus.SUCCESS
        accountResponseWrapper.data == null
    }

    /*def "delete account"() {
        given:"mock accountDto"
        AccountDto accountDto = populateAccountDtoDetails()

        and:"mocked accountService to return accountDto"
        accountService.findById(_ as Long) >> accountDto
        accountService.delete(_ as Long) >> null

        when:"calling rest api to get account details"
        def response = mockMvc.perform(delete("/accounts/1")).andReturn()

        ObjectMapper mapper = new ObjectMapper();
        AccountResponseWrapper accountResponseWrapper = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<AccountResponseWrapper>(){})

        then:"getting success response"
        accountResponseWrapper != null
        accountResponseWrapper.status == AccountResponseStatus.SUCCESS
        accountResponseWrapper.data == null
    }*/

    private static AccountDto populateAccountDtoDetails( ){
        return new AccountDto(1L,"Current", "1111111111111", "Current Account", 1L)
    }

    private static List<AccountDto> populateAccountDetailsDtoList( ){
        return Arrays.asList(
                new AccountDto(1L,"Current", "1111111111111", "Current Account", 1L),
                new AccountDto(2L,"Current", "2222222222222", "Current Account", 2L),
                new AccountDto(3L,"Current", "3333333333333", "Current Account", 3L)
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
        AccountService accountService() {
            factory.Mock(AccountService.class,name:"accountService")
        }
    }
}



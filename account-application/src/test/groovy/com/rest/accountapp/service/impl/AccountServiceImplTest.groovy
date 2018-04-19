package com.rest.accountapp.service.impl

import com.rest.accountapp.dto.AccountDto
import com.rest.accountapp.entity.Account
import com.rest.accountapp.exceptions.AccountAlreadyExistsException
import com.rest.accountapp.exceptions.AccountNotFoundException
import com.rest.accountapp.mapper.AccountMapper
import com.rest.accountapp.repository.AccountRepository
import spock.lang.Specification

class AccountServiceImplTest extends Specification {
    //class to be tested
    private AccountServiceImpl serviceImpl

    void setup() {
        serviceImpl = new AccountServiceImpl(accountRepository: Mock(AccountRepository), accountMapper: Spy(AccountMapper))
    }

    def "Save account - Success scenario"() {
        given:"The accountDto is passed as an argument"
        AccountDto inputAccountDto = populateAccountDtoDetails()
        Account account = populateAccountDetails()

        and:"The account with the combination of userId and account type is not already present in the database so returning null"
        serviceImpl.accountRepository.findByTypeAndUserId(_ as AccountDto) >> null
        serviceImpl.accountRepository.save(_ as Account) >> account

        when:"The accountDto is passed as an argument to the save api of serviceImpl"
        AccountDto expectedAccountDto = serviceImpl.save(inputAccountDto)

        then:"The accountDto is not null and is returned back to the controller from serviceImpl"
        expectedAccountDto != null
        expectedAccountDto.type == inputAccountDto.type
    }

    def "Save account - Account Already Present Exception scenario"() {
        given:"The accountDto is passed as an argument"
        AccountDto inputAccountDto = populateAccountDtoDetails()
        Account account = populateAccountDetails()
        int count = 1

        and:"The accountName is already present in the database so findByAccountName api is returning account"
        serviceImpl.accountRepository.findByTypeAndUserId(_ as String, _ as Long) >> count

        when:"The accountDto is passed as an argument to the save api of serviceImpl"
        serviceImpl.save(inputAccountDto)

        then:"The accountName already present so AccountAlreadyExistsException is thrown"
        def e = thrown(AccountAlreadyExistsException)
        e != null
    }

    def "Update account - Success scenario"() {
        given:"The accountDto is passed as an argument"
        AccountDto inputAccountDto = populateAccountDtoDetails()
        Account account = populateAccountDetails()

        and:
        serviceImpl.accountRepository.save(_ as Account) >> account

        when:"The accountDto is passed as an argument"
        AccountDto expectedAccountDto = serviceImpl.save(inputAccountDto)

        then:"The accountDto is not null and is returned back to the controller from serviceImpl"
        expectedAccountDto != null
        expectedAccountDto.type == inputAccountDto.type
    }

    def "Find account By Id - Success scenario"() {
        given:"The accountId is passed as an argument"
        Account account = populateAccountDetails()
        Long accountId = 1L

        and:"The account is present in the database so returning account"
        serviceImpl.accountRepository.findOne(_ as Long) >> account

        when:"The accountId is passed as an argument"
        AccountDto expectedAccountDto = serviceImpl.findById(accountId)

        then:"The accountDto is not null and is returned back to the controller from serviceImpl"
        expectedAccountDto != null
        expectedAccountDto.accountId == accountId
    }

    def "Find account By Id - Not Found scenario"() {
        given:"The accountId is passed as an argument"
        Long accountId = 1L

        and:"The account is not present in the database so returning null"
        serviceImpl.accountRepository.findOne(_ as Long) >> null

        when:"The accountId is passed as an argument"
        AccountDto expectedAccountDto = serviceImpl.findById(accountId)

        then:"The account is not present so AccountNotFoundException is thrown"
        def e = thrown(AccountNotFoundException)
        e != null
    }

    def "Find All Accounts - Success scenario"() {
        given:
        List<AccountDto> accountDtoList = populateAccountDetailsDtoList()
        List<Account> accountList = populateAccountDetailsList()

        and:"The accounts are present in the database so returning list of accounts"
        serviceImpl.accountRepository.findAll(*_) >> accountList

        when:
        List<AccountDto> expectedAccountDtoList = serviceImpl.findAll()

        then:"The accountDto list is not null and is returned back to the controller from serviceImpl"
        expectedAccountDtoList != null
        expectedAccountDtoList.size() == accountList.size()
    }

    def "Find All Accounts - Not Found scenario"() {
        given:"The accounts are not present in the database so returning null"
        serviceImpl.accountRepository.findAll(*_) >> null

        when:
        List<AccountDto> expectedAccountDtoList = serviceImpl.findAll()

        then:"The accounts are not present so AccountNotFoundException is thrown"
        def e = thrown(AccountNotFoundException)
        e != null
    }

    def "Delete Account - Success scenario"() {
        given:"The accountId is passed as an argument"
        Account account = populateAccountDetails()
        Long accountId = 1L

        and:"The account is present in the database so returning account"
        serviceImpl.accountRepository.findOne(_ as Long) >> account
        serviceImpl.accountRepository.delete(_ as Long) >> null

        when:"The accountId is passed as an argument to delete"
        serviceImpl.delete(accountId)

        then:"The account is present in the database so deleting the account"
        1 * serviceImpl.accountRepository.delete(_)
    }

    def "Delete Account - Not Found scenario"() {
        given:"The accountId is passed as an argument"
        Long accountId = 1L

        and:"The account is not present in the database so returning null"
        serviceImpl.accountRepository.findOne(_ as Long) >> null
        serviceImpl.accountRepository.delete(_ as Long) >> null

        when:"The accountId is passed as an argument to delete"
        serviceImpl.delete(accountId)

        then:"The account is not present so AccountNotFoundException is thrown"
        def e = thrown(AccountNotFoundException)
        e != null
    }

    def "Delete All Accounts - Success scenario"() {
        given:
        serviceImpl.accountRepository.deleteAll() >> null

        when:
        serviceImpl.deleteAll()

        then:"The account is present in the database so deleting all the account"
        1 * serviceImpl.accountRepository.deleteAll()
    }

    def "Find accounts by userId - Success scenario"() {
        given:"The userId is passed as an argument"
        List<Account> accountList = populateAccountDetailsList()
        Long userId = 1L

        and:"The accounts are present in the database so returning account list"
        serviceImpl.accountRepository.findByUserId(_ as Long) >> accountList

        when:"The userId is passed as an argument"
        List<AccountDto> expectedAccountDtoList = serviceImpl.findByUserId(userId)

        then:"The accountDto is not null and is returned back to the controller from serviceImpl"
        expectedAccountDtoList != null
        expectedAccountDtoList.size() == 3
    }

    /*def "Find account by userId - Not Found scenario"() {
        given:"The userId is passed as an argument"
        Long userId = 1L

        and:"The accounts are present in the database so returning account list"
        serviceImpl.accountRepository.findByUserId(_ as Long) >> null

        when:"The userId is passed as an argument"
        List<AccountDto> expectedAccountDtoList = serviceImpl.findByUserId(userId)

        then:"The account is not present so AccountNotFoundException is thrown"
        def e = thrown(AccountNotFoundException)
        e != null
    }*/

    private static AccountDto populateAccountDtoDetails( ){
        return new AccountDto(1L,"Current", "1111111111111", "Current Account", 1L)
    }

    private static Account populateAccountDetails( ){
        return new Account(1L,"Current", "1111111111111", "Current Account", 1L)
    }

    private static List<Account> populateAccountDetailsList( ){
        return Arrays.asList(
                new Account(1L,"Current", "1111111111111", "Current Account", 1L),
                new Account(2L,"Current", "2222222222222", "Current Account", 2L),
                new Account(3L,"Current", "3333333333333", "Current Account", 3L)
        )
    }

    private static List<AccountDto> populateAccountDetailsDtoList( ){
        return Arrays.asList(
                new AccountDto(1L,"Current", "1111111111111", "Current Account", 1L),
                new AccountDto(2L,"Current", "2222222222222", "Current Account", 2L),
                new AccountDto(3L,"Current", "3333333333333", "Current Account", 3L)
        )
    }
}

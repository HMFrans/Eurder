package com.switchfully.eurder.service.customers;

import com.switchfully.eurder.domain.members.Customer;
import com.switchfully.eurder.service.customers.dto.NewMemberDto;
import com.switchfully.eurder.domain.members.MemberRepository;
import com.switchfully.eurder.service.customers.dto.ReturnMemberDto;
import com.switchfully.eurder.service.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final MemberRepository memberRepository;
    private final CustomerMapper customerMapper;
    private final Validator validator;

    public CustomerService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.validator = new Validator();
        this.customerMapper = new CustomerMapper();
    }

    public ReturnMemberDto addNewMember(NewMemberDto newMemberDto) {
        validator.checkRequiredFieldsForNewMember(newMemberDto);
        Customer newCustomer = customerMapper.DtoToMember(newMemberDto);
        return customerMapper.memberToDto(memberRepository.addMember(newCustomer));
    }

    public void makeAdmin(String userId) {
        memberRepository.makeAdmin(userId);
    }

    public List<ReturnMemberDto> getAllMembers() {
        return memberRepository.getMembers()
                .stream()
                .map(customer -> customerMapper.memberToDto(customer))
                .toList();

    }

    public ReturnMemberDto getMember(String memberId) {
        return customerMapper.memberToDto(memberRepository.getMember(memberId));
    }
}

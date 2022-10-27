package com.switchfully.eurder.users.service;

import com.switchfully.eurder.users.domain.Address;
import com.switchfully.eurder.users.domain.Member;
import com.switchfully.eurder.users.domain.NewMemberDto;
import com.switchfully.eurder.users.domain.ReturnMemberDto;


public class MemberMapper {
    public Member DtoToMember(NewMemberDto newMemberDto) {
        return new Member(newMemberDto.getFirstName(), newMemberDto.getLastName(), newMemberDto.getEmailAddress(), newMemberDto.getPhoneNumber(),
                new Address(newMemberDto.getCity(), newMemberDto.getPostalCode(), newMemberDto.getStreetName(), newMemberDto.getHouseNumber(), newMemberDto.getAdditionalInfo()));
    }

    public ReturnMemberDto memberToDto(Member member) {
        return new ReturnMemberDto(member.getId(), member.getFirstName(), member.getLastName(), member.getEmailAddress(), member.getAddress(), member.getPhoneNumber());
    }
}

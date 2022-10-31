package com.switchfully.eurder.service.members;

import com.switchfully.eurder.domain.members.Address;
import com.switchfully.eurder.domain.members.Member;
import com.switchfully.eurder.domain.members.NewMemberDto;
import com.switchfully.eurder.domain.members.ReturnMemberDto;


public class MemberMapper {
    public Member DtoToMember(NewMemberDto newMemberDto) {
        return new Member(newMemberDto.getPassword(), newMemberDto.getFirstName(), newMemberDto.getLastName(), newMemberDto.getEmailAddress(), newMemberDto.getPhoneNumber(),
                new Address(newMemberDto.getCity(), newMemberDto.getPostalCode(), newMemberDto.getStreetName(), newMemberDto.getHouseNumber(), newMemberDto.getAdditionalInfo()));
    }

    public ReturnMemberDto memberToDto(Member member) {
        return new ReturnMemberDto(member.getId(), member.getFirstName(), member.getLastName(), member.getEmailAddress(), member.getAddress(), member.getPhoneNumber());
    }
}

package com.switchfully.eurder.users.service;

import com.switchfully.eurder.users.domain.Member;
import com.switchfully.eurder.users.domain.NewMemberDto;
import com.switchfully.eurder.users.domain.MemberRepository;
import com.switchfully.eurder.users.domain.ReturnMemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private MemberMapper memberMapper;
    private Validator validator;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.validator = new Validator();
        this.memberMapper = new MemberMapper();
    }

    public ReturnMemberDto addNewMember(NewMemberDto newMemberDto) {
        validator.CheckRequiredFieldsFilledInForNewMember(newMemberDto);
        Member newMember = memberMapper.DtoToMember(newMemberDto);
        return memberMapper.memberToDto(memberRepository.addMember(newMember));
    }
}
